package movierater;

import java.nio.file.Path;

import movie.Movie;
import util.Publisher;
import util.Subscriber;

public interface MovieRater extends Publisher<Movie> /*Publisher<Lifecycle> // Dam type erasure */ {
    
    public void start(Path directory);
    public void stop();    
    public boolean addLifecycleSubscriber(Subscriber<Lifecycle> subscriber);
}
