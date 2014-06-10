package movierater;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;

import util.Subscriber;

import gui.MovieRaterUI;
import gui.errordisplay.ErrorDisplay;
import gui.errordisplay.PopupErrorDisplay;

public class MovieRaterControllerImp implements MovieRaterController, Subscriber<Lifecycle> {

    private MovieRater movieRater;
    private final ErrorDisplay errorDisplay = new PopupErrorDisplay();
    private Collection<MovieRaterUI> UIs = new ArrayList<MovieRaterUI>();
    
    /**
     * could take in multiple MovieRaterGui s and sync them.
     * @param movieRater
     * @param gui
     */
    public MovieRaterControllerImp(MovieRater movieRater) {
        this.movieRater = movieRater;
        movieRater.addLifecycleSubscriber(this); // err in constructor...
    }
    
    @Override
    public void start(String directoryString) {
        Path directory = Paths.get(directoryString);
        if(Files.isDirectory(directory)) {
            movieRater.start(directory);
            setUIsToStarted();
        }
        else {
            // Does it have to be a directory?
            errorDisplay.showErrorMessage("The given path is not a directory or does not exist.");
        }
    }
    

    @Override
    public void stop() {
        movieRater.stop();
        setUIsToStopped();
    }
    
    @Override
    public void reset() {
        setUIsToResetted();
    }
    
    private void setUIsToStopped() {
        for(MovieRaterUI UI : UIs) {
            UI.setStopped();
        }
    }
    
    private void setUIsToStarted() {
        for(MovieRaterUI UI : UIs) {
            UI.setStarted();
        }
    }
    
    private void setUIsToResetted() {
        for(MovieRaterUI UI : UIs) {
            UI.setResetted();
        }
    }

    @Override
    public boolean addUI(MovieRaterUI ui) {
        boolean addedToRater = movieRater.addSubscriber(ui);
        if(addedToRater) {
            UIs.add(ui);
        }
        return addedToRater;
    }

    @Override
    public void update(Lifecycle stage) {
        if(stage == Lifecycle.FINISHED) {
            setUIsToStopped();
        }
    }
}

