package gui.movielist;
import javax.swing.JComponent;

import movie.Movie;

import exception.MovieNotFoundException;


public abstract class MovieList extends JComponent
{
	public abstract void addMovie(Movie moiveName) throws MovieNotFoundException;

    public abstract void removeAllMovies();
}
