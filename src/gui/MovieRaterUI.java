package gui;

import javax.swing.JPanel;

import movie.Movie;
import util.Subscriber;

public interface MovieRaterUI extends Subscriber<Movie> {
    void setStopped();
    void setStarted();
    void setResetted();  
    JPanel getPanel();
    
}
