package gui.moviepanel;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import movie.Movie;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;


public class SimpleMoviePanel extends MoviePanel 
{
	private Movie movie;
	
	public SimpleMoviePanel(Movie movie) 
	{
		this.movie = movie;
		
		setLayout(new MigLayout("", "[grow,left][grow,center][grow,right]", "[]"));
		
		JLabel titleLabel = new JLabel(movie.getTitle());
		add(titleLabel);
		
		JLabel ratingLabel = new JLabel(Float.toString(movie.getRating()));
		add(ratingLabel);
		
		BufferedImage picture = movie.getPicture();		
		JLabel pictureLabel = new JLabel(new ImageIcon(picture));
		add(pictureLabel);
		//setPreferredSize(getMinimumSize());
	}

	@Override
	public Movie getMovie()
	{
		return movie;
	}
}
