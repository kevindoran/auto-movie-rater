package movie;
import java.awt.image.BufferedImage;



public interface Movie extends Comparable<Movie>
{
	public float getRating();	
	
	public String getTitle();
	
	public BufferedImage getPicture();
}

