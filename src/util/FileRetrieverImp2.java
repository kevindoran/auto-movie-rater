package util;

import java.io.File;
import java.nio.file.Path;
import java.util.Collection;

public class FileRetrieverImp2 implements FileRetriever {

    @Override
    public Collection<Path> getAllFilesInDirectoryTree(Path directory) {
        // TODO Auto-generated method stub
        return null;
    }
//    
//    public void retrieveMovieNames(File movieFileOrDirectory) {
//        if (movieFileOrDirectory.exists() == false) {
//            // throw new
//        }
//
//        if (movieFileOrDirectory.isFile() == true) {
//            String movieName = movieFileOrDirectory.getName();
//            movieName = trimName(movieName);
//
//            if (movieName.equals("") == false) {
//                movieNames.add(movieName);
//            }
//        } else if (movieFileOrDirectory.isDirectory() == true) {
//            File[] filesInDirectory = movieFileOrDirectory.listFiles();
//            for (File file : filesInDirectory) {
//                retrieveMovieNames(file);
//            }
//        }
//    }

}
