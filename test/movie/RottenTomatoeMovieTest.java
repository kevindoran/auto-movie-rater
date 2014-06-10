package movie;


import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.Collection;

import org.junit.Test;


public class RottenTomatoeMovieTest
{

	@Test
	public void testRottenTomatoeMovie() throws IOException
	{
		Collection<RottenTomatoeMovie> movie = RottenTomatoeMovie.findMovies("Batman");
		assertNotNull(movie);
	}
}
