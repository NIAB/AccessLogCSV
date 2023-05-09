package com.niab.accesslogcsv;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicLong;

final class FileUtils {
    /**
     * Attempts to calculate the size (in megabytes) of a file or directory.
     *
     * <p>Since the operation is non-atomic, the returned value may be inaccurate.
     * However, this method is quick and does its best.</p>
     *
     * Adapted from this <a href="https://stackoverflow.com/a/19877372">StackOverflow answer</a>.
     */
    static long size(final Path path) throws IOException {
        final AtomicLong size = new AtomicLong(0);

        Files.walkFileTree(path, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs) {
                size.addAndGet(attrs.size());
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(final Path file, final IOException exc) {
                // Skip folders that can't be traversed
                System.out.println("[Warning] Skipped: " + file + " (" + exc + ")");
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(final Path dir, final IOException exc) {
                // Ignore errors traversing a folder
                if (exc != null)
                    System.out.println("[Warning] had trouble traversing: " + dir + " (" + exc + ")");

                return FileVisitResult.CONTINUE;
            }
        });

        return size.get();
    }

    static String humanReadableSize(final Path path) throws IOException {
        return toHumanReadable(size(path));
    }

    static String toHumanReadable(final long bytes) {
        final long kiloBytes = bytes / 1024;
        if (kiloBytes == 0) return bytes + "B";

        final long megaBytes = kiloBytes / 1024;
        if (megaBytes == 0) return kiloBytes + "KB";

        final long gigaBytes = megaBytes / 1024;
        if (gigaBytes == 0) return megaBytes + "MB";
        else return gigaBytes + "GB";
    }

    static final class PathComparator implements Comparator<Path> {
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

    private FileUtils() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }
}
