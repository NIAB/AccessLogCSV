package com.niab.accesslogcsv;

import it.unimi.dsi.fastutil.objects.Object2LongAVLTreeMap;
import it.unimi.dsi.fastutil.objects.Object2LongMap;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

final class App {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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
                    System.out.println("Version: 1.2.0");
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
        try (final BufferedWriter writer = Files.newBufferedWriter(csvPath)) {
            writer.write("Access time,Size,Full path\n");
            final Object2LongAVLTreeMap<Path> latestAccessTimes = new Object2LongAVLTreeMap<>(FileUtils.PathComparator.INSTANCE);
            try (final Stream<Path> files = Files.walk(folderPath)) {
                latestAccessTimes.clear();
                final var pathList = files.collect(Collectors.toList());
                for (final Path file : pathList) {
                    if (Files.isDirectory(file))
                        continue;

                    try {
                        final BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);
                        final long fileAccessTime = attr.lastAccessTime().toMillis();
                        final Path dirPath = file.getParent().toAbsolutePath();

                        // if the file is newer than the latest access time for the directory, update the latest access time for the directory
                        if (latestAccessTimes.containsKey(dirPath)) {
                            if (fileAccessTime > latestAccessTimes.getLong(dirPath)) {
                                latestAccessTimes.put(dirPath, fileAccessTime);
                            }
                        } else {
                            latestAccessTimes.put(dirPath, fileAccessTime);
                        }
                    } catch (final IOException ignored) {}
                }

                writeEntriesToCsv(writer, latestAccessTimes);
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeEntriesToCsv(final Writer writer, final Object2LongAVLTreeMap<Path> latestAccessTimes) throws IOException {
        for (final Object2LongMap.Entry<Path> entry : latestAccessTimes.object2LongEntrySet()) {
            final Path path = entry.getKey();
            writer.write(DATE_FORMAT.format(new Date(entry.getLongValue())) + "," + FileUtils.humanReadableSize(path) + "," + path + "\n");
        }
    }
}
