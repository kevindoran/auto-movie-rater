package util;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collection;

public class FileRetrieverImp implements FileRetriever {

    
    @Override
    public Collection<Path> getAllFilesInDirectoryTree(Path directory) {
    Collection<Path> files = new ArrayList<>();
        try {
            Files.walkFileTree(directory, new FileCollector(files));
        } catch (IOException e) {
            // No visitor methods throw an IOException.
            assert false;
        }
        return files;
    }
    
    private class FileCollector implements FileVisitor<Path> {
        
        private Collection<Path> files = new ArrayList<>();
        public FileCollector(Collection<Path> files) {
            this.files = files;
        }
        
        @Override
        public FileVisitResult preVisitDirectory(Path dir,
                BasicFileAttributes attrs) throws IOException {
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                throws IOException {
            files.add(file);
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc)
                throws IOException {
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc)
                throws IOException {
            return FileVisitResult.CONTINUE;
        }
    }

}
