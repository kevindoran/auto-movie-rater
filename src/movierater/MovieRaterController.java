package movierater;

import gui.MovieRaterUI;

public interface MovieRaterController {
        void start(String directory);
        void stop();
        void reset();
        boolean addUI(MovieRaterUI ui);
}
