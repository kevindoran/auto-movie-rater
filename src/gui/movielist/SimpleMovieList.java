package gui.movielist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JLabel;
import javax.swing.JPanel;

import exception.MovieNotFoundException;
import gui.moviepanel.MoviePanel;
import gui.moviepanel.SimpleMoviePanel;

import movie.Movie;
import movie.RottenTomatoeMovie;
import net.miginfocom.swing.MigLayout;

// Factory Pattern
public class SimpleMovieList extends MovieList {
    /**
     * 
     */
    private static final long serialVersionUID = 5692309707730429781L;
    
    ArrayList<MoviePanel> moviePanels = new ArrayList<MoviePanel>();

    public SimpleMovieList() {
        setLayout(new MigLayout("", "grow"));
        addHeadings();
    }

    private void addHeadings() {
        add(new JLabel("<html><B>Name</b></html>"), "split, grow, left");
        add(new JLabel("<html><b>Rating</b></html>"), "grow, center");
        add(new JLabel("<html><b>Icon</b></html>"), "right, wrap");
    }

    @Override
    public void addMovie(Movie movieToAdd) {
        MoviePanel panelToAdd = createMoviePanel(movieToAdd);
        add(panelToAdd, "span");
        revalidate();
        moviePanels.add(panelToAdd);
        // Is the below called to often? ie every new movie?
        orderMovies();
    }
    
    @Override
    public void removeAllMovies() {
        //could also do a removeAll().
        for(MoviePanel moviePanel : moviePanels) {
            remove(moviePanel);
            moviePanels = new ArrayList<>();
        }
        revalidate();
        repaint();
    }

    public void orderMovies() {
        Collections.sort(moviePanels, new MovieOrderer());
        removeAll();
        addHeadings();
        for (int i = moviePanels.size() - 1; i >= 0; i--) {
            add(moviePanels.get(i), "grow, wrap");
        }
        revalidate();
    }

    private class MovieOrderer implements Comparator<MoviePanel> {
        @Override
        public int compare(MoviePanel one, MoviePanel two) {
            return one.getMovie().compareTo(two.getMovie());
        }
    }

    /**
     * Factory method pattern
     * 
     * @param movie
     * @return
     */
    protected MoviePanel createMoviePanel(Movie movie) {
        return new SimpleMoviePanel(movie);
    }
}
