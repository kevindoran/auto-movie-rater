package movierater;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import movie.Movie;
import movie.RottenTomatoeMovie;
import util.FileRetriever;
import util.FileRetrieverImp;
import util.Subscriber;

public class MovieRaterImp implements MovieRater {

    Collection<Subscriber<Movie>> movieSubscribers = new HashSet<>();
    Collection<Subscriber<Lifecycle>> lifecycleSubsccribers = new HashSet<>();
    public static final Collection<String> movieFileTypes = new ArrayList<>();
    
    public MovieRaterImp() {
        movieFileTypes.add("avi");
        movieFileTypes.add("mkv");
    }
    
    private final AtomicBoolean shouldRun = new AtomicBoolean();
    
    @Override
    public boolean addSubscriber(Subscriber<Movie> subscriber) {
        boolean added = movieSubscribers.add(subscriber);
        return added;
    }

    @Override
    public boolean removeSubscriber(Subscriber<Movie> subscriber) {
        boolean removed = movieSubscribers.remove(subscriber);
        return removed;
    }


    @Override
    public void stop() {
        shouldRun.set(false);
    }
    
    private void notifyMovieSubscribers(Movie newMovie) {
        for(Subscriber<Movie> subscriber : movieSubscribers) {
            subscriber.update(newMovie);
        }
    }
    
    private void notifyLifecycleSubscribers(Lifecycle stage) {
        for(Subscriber<Lifecycle> subscriber : lifecycleSubsccribers) {
            subscriber.update(stage);
        }
    }
    
    // if called from multiple thread = carnage due to lifecycle events being wrong.
    @Override
    public void start(final Path directory) {
        shouldRun.set(true);
        Runnable movieRetriever = new SingleMovieRetriever(directory);
        Thread thread = new Thread(movieRetriever);
        thread.start();
        notifyLifecycleSubscribers(Lifecycle.RUNNING);
    }
    

    private class SingleMovieRetriever implements Runnable {
        
       private final Path directory;
        
        public SingleMovieRetriever(Path directory) {
            this.directory = directory;
        }
        
        @Override
        public void run() {
            FileRetriever fileRetriever = new FileRetrieverImp();
            Collection<Path> movieFiles = fileRetriever.getAllFilesInDirectoryTree(directory);
            Iterator<Path> movieIt = movieFiles.iterator();
            while(shouldRun.get() && movieIt.hasNext()) {
                String unProcessedMovieName = movieIt.next().getFileName().toString();
                if(!isMovieFileType(unProcessedMovieName)) {
                    continue;
                }
                String movieName = trimName(unProcessedMovieName);
                Collection<RottenTomatoeMovie> matchingMovies = Collections.emptySet();
                try {
                    matchingMovies = RottenTomatoeMovie.findMovies(movieName);
                } catch (IOException e) {
                    e.printStackTrace(); // TODO
                }
                if(matchingMovies.size() > 0) {
                    Movie firstMatch = matchingMovies.iterator().next();
                    notifyMovieSubscribers(firstMatch);
                }
            }
            notifyLifecycleSubscribers(Lifecycle.FINISHED);
        }
    }
    
    private boolean isMovieFileType(String moviePath) {
        String fileExtension = "";
        if(moviePath.lastIndexOf('.') != -1) {
            // The path contains a '.'
            if(moviePath.lastIndexOf('.') < moviePath.length()-1) {
                // If '.' is not the last character.
                fileExtension = moviePath.substring(moviePath.lastIndexOf('.')+1, moviePath.length());
            }
        }
        boolean isMovieFileType = fileExtension.equals("") || movieFileTypes.contains(fileExtension);
        return isMovieFileType;
    }
    
    private String trimName(String movieName) {
        // Remove File extension
        if(movieName.lastIndexOf('.') != -1) {
            movieName = movieName.substring(0, movieName.lastIndexOf('.'));
        }
        
        // Regex Patterns
        String year = "[\\(\\[]?(19|20)\\d\\d[\\)\\]]?";
        // Movies with 720 and 1080 will not be matched :(
        String format = "(720)|(1080)|(hdtv)|(hd)|(dts)|(dvd)|(bLUrAY)|(X264)|(ac3)|(xvid)|(rip)|(scr)"; 
        String bracketedInfo = "([\\(\\[].*[\\)\\]])";

        String regexString = year + "|" + format + "|" + bracketedInfo;

        Pattern regexPattern = Pattern.compile(regexString,
                Pattern.CASE_INSENSITIVE);

        Matcher matcher = regexPattern.matcher(movieName);
        if (matcher.find() == true) {
            movieName = movieName.substring(0, matcher.start());
        }

        // Convert punctuation to spaces. Although '-' and other characters might
        // be valid, they potentially separate keywords from metadata. Removing they
        // may allow keywords to be recognised, and may not detriment the searching
        // of words with '-' in it anyway.
        regexPattern = Pattern.compile("\\p{Punct}");
        matcher = regexPattern.matcher(movieName);
        movieName = matcher.replaceAll(" ");

        return movieName;
    }

    @Override
    public boolean addLifecycleSubscriber(Subscriber<Lifecycle> subscriber) {
        boolean added = lifecycleSubsccribers.add(subscriber);        
        return added;
    }
}
