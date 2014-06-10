package gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.UIManager;

import movierater.MovieRater;
import movierater.MovieRaterController;
import movierater.MovieRaterControllerImp;
import movierater.MovieRaterImp;
import gui.util.WrappingFrame;

public class MovieRaterFacade {

    private JFrame gui;
    
    public MovieRaterFacade() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Put up with the standard look-and-feel.
        }
        
        MovieRater rater = new MovieRaterImp();
        MovieRaterController controller = new MovieRaterControllerImp(rater);
        MovieRaterUI ui = new MovieRaterUIImp(controller);
        ui.getPanel().setPreferredSize(new Dimension(450,450));
        controller.addUI(ui);
        gui = new WrappingFrame(ui.getPanel(), "Movie Rater").getFrame();
    }
    
    public void start() {
        gui.setVisible(true);
    }
    
    
    public static void main(String[] args) {
        (new MovieRaterFacade()).start();
    }
    
}
