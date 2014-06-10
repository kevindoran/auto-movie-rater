package movie;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

public class RottenTomatoeMovie implements Movie {
    MovieData movieData;

    private static final String apiKey = "mrkmcjwt439c2ghg8cwe8g3n";
    private static final String api = "http://api.rottentomatoes.com/api/public/v1.0/movies.json?";

    protected RottenTomatoeMovie(MovieData movieData) {
        this.movieData = movieData;
    }
    
    public static Collection<RottenTomatoeMovie> findMovies(String movieName) throws IOException {
        movieName = movieName.trim();
        Pattern regex = Pattern.compile(" ");
        Matcher matcher = regex.matcher(movieName);
        movieName = matcher.replaceAll("+");

        String apiRequest = api + "apiKey=" + apiKey + "&q=" + movieName + "&page_limit=1";
        String jsonString = getJson(apiRequest);
        MovieData[] movieData = parseJson(jsonString);
        Collection<RottenTomatoeMovie> movies = new ArrayList<>();
        for(MovieData md : movieData) {
            movies.add(new RottenTomatoeMovie(md));
        }
        return movies;
    }

    private static String getJson(String apiRequest) throws IOException {
        URL apiRequestURL = null;
        try {
            apiRequestURL = new URL(apiRequest);
        } catch (MalformedURLException ex) {
            // The URL is formed by this class, and it must be correct.
            assert false;
        }
        assert apiRequestURL != null;

        BufferedReader reader = new BufferedReader(new InputStreamReader(
                    apiRequestURL.openStream()));

        String JsonString = "";
        String inputLine;
        while ((inputLine = reader.readLine()) != null) {
            JsonString += inputLine;
        }

        assert JsonString != "";
        return JsonString;
    }

    private static MovieData[] parseJson(String jsonString) {
        Gson gson = new GsonBuilder().create();
        MovieData[] movieData;
        JsonData jsonData;
        try {
            jsonData = gson.fromJson(jsonString, JsonData.class);
        } catch(JsonSyntaxException e) {
            // log
            movieData = new MovieData[0];
            return movieData;
        }
        if (jsonData.movies != null) {
            // When can this occur?
            movieData = jsonData.movies;
        }
        else {
            movieData = new MovieData[0];
        }
        return movieData;
    }

    @Override
    public float getRating() {
        return movieData.ratings.audience_score;
    }

    @Override
    public String getTitle() {
        return movieData.title;
    }

    @Override
    public BufferedImage getPicture() {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new URL(movieData.posters.thumbnail));
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        return image;
    }

    private class JsonData {
        public int total;
        public MovieData[] movies;
    }

    private class MovieData {
        public String title;
        public int year;
        public int runtime;
        public ReleaseDates release_dates;
        public Ratings ratings;
        public String synopsis;
        public Posters posters;

        public MovieData() {
        }

        private class Ratings {
            public int critics_score;
            public int audience_score;

            public Ratings() {
            };
        }

        private class ReleaseDates {
            public String theater;

            public ReleaseDates() {
            };
        }

        private class Posters {
            public String thumbnail;
            public String profile;
            public String detailed;
            public String original;
        }
    }

    @Override
    public int compareTo(Movie movie) {
        return (int) (getRating() - movie.getRating());
    }

}