package com.niab.accesslogcsv;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;

public class App {
    public static void main(final String[] args) throws IOException {
        final Path folderPath;
        final Path csvPath;
        switch (args.length) {
            case 0:
                System.err.println("No arguments provided. Please run me with the first arg being the path of the folder to scan and optionally the second arg being the path of the output CSV file.");
                System.exit(1);
                return;
            case 1:
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

        // create the csv file if it doesn't already exist
        Files.createFile(csvPath);

        final var dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try (final BufferedWriter writer = Files.newBufferedWriter(csvPath)) {
            writer.write("Access time,Full path including filename\n");
            try (final Stream<Path> files = Files.walk(folderPath)) {
                files.forEach(filePath -> {
                    try {
                        final BasicFileAttributes attr = Files.readAttributes(filePath, BasicFileAttributes.class);
                        final String accessTime = dateFormat.format(new Date(attr.lastAccessTime().toMillis()));
                        final String fullPath = filePath.toAbsolutePath().toString();
                        writer.write(accessTime + "," + fullPath + "\n");
                    } catch (final IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}
