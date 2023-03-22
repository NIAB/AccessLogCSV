package com.niab.accesslogcsv;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

final class App {
    
    private static long fileCount = 0;
    
    public static void main(final String[] args) throws IOException {
        final Path folderPath;
        final Path csvPath;
        switch (args.length) {
            case 0:
                System.err.println("No arguments provided. Please run me with the first arg being the path of the folder to scan and optionally the second arg being the path of the output CSV file.");
                System.exit(1);
                return;
            case 1:
                if (args[0].equalsIgnoreCase("version")) {
                    System.out.println("Version: 1.1.1");
                    System.exit(0);
                    return;
                }
                folderPath = Paths.get(args[0]);
                csvPath = Paths.get(args[0], "output.csv");
                break;
            case 2:
                folderPath = Paths.get(args[0]);
                csvPath = Paths.get(args[1]);
                break;
            default:
                System.err.println("Too many arguments provided. Please run me with the first arg being the path of the folder to scan and optionally the second arg being the path of the output CSV file.");
                System.exit(1);
                return;
        }

        // rename the csv to .csv.bak if it already exists, overwriting any existing .csv.bak
        if (Files.exists(csvPath))
            Files.move(csvPath, csvPath.resolveSibling(csvPath.getFileName() + ".bak"), java.nio.file.StandardCopyOption.REPLACE_EXISTING);

        // create the csv file
        Files.createFile(csvPath);

        makeCsv(folderPath, csvPath);
    }

    private static void makeCsv(final Path folderPath, final Path csvPath) {
        final var dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try (final BufferedWriter writer = Files.newBufferedWriter(csvPath)) {
            writer.write("Access time,Full path\n");
            final Map<Path, Long> latestAccessTimes = new HashMap<>();
            try (final Stream<Path> files = Files.walk(folderPath)) {
                files.forEach(file -> {
                    if (!Files.isDirectory(file)) {
                        try {
                            final BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);
                            final long fileAccessTime = attr.lastAccessTime().toMillis();
                            final Path dirPath = file.getParent().toAbsolutePath();
                            final Long latestAccessTime = latestAccessTimes.get(dirPath);
                            if (latestAccessTime == null || fileAccessTime > latestAccessTime) {
                                latestAccessTimes.put(dirPath, fileAccessTime);
                            }
                        } catch (final IOException ignored) {
                        }
                    }

                    if (fileCount % 1000 == 0) {
                        System.out.println(fileCount + " files");
                    }
                    fileCount++;
                });

                System.out.println("Writing access times to the log file");
                for (final Map.Entry<Path, Long> entry : latestAccessTimes.entrySet()) {
                    writer.write(dateFormat.format(new Date(entry.getValue())) + "," + entry.getKey() + "\n");
                }
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    private static final class PathComparator implements Comparator<Path> {
        static final PathComparator INSTANCE = new PathComparator();

        @Override
        public int compare(final Path path1, final Path path2) {
            final int depth1 = path1.getNameCount();
            final int depth2 = path2.getNameCount();
            return depth1 == depth2
                    ? path1.compareTo(path2)
                    : Integer.compare(depth1, depth2);
        }
    }
}
