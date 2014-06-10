package gui;

import gui.movielist.SimpleMovieList;
import gui.util.LabelledFileChooser;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import movie.Movie;
import movierater.MovieRaterController;
import net.miginfocom.swing.MigLayout;

public class MovieRaterUIImp extends JPanel implements MovieRaterUI {
    /**
     * 
     */
    private static final long serialVersionUID = -4029861560443874978L;

    SimpleMovieList movieListPanel;
    JScrollPane scrollPane;
    LabelledFileChooser fileChooser;
    private MovieRaterController controller;
    private JButton runButton;
    private JButton stopButton;
    private JButton resetButton;

    /**
     * Create the panel.
     */
    public MovieRaterUIImp(MovieRaterController controller) {
        this.controller = controller;
        
        createComponents();
        layoutComponents();
        addListeners();
    }

    private void createComponents() {
        runButton = new JButton("Start");
        stopButton = new JButton("Stop");
        resetButton = new JButton("Reset");

        fileChooser = new LabelledFileChooser("Movie Directory or File");
        fileChooser.getFileChooser().setFileSelectionMode(
                JFileChooser.FILES_AND_DIRECTORIES);

        movieListPanel = new SimpleMovieList();
        scrollPane = new JScrollPane(movieListPanel);
        int arbitaryButLarge = 16;
        scrollPane.getVerticalScrollBar().setUnitIncrement(arbitaryButLarge);
    }

    private void layoutComponents() {
        setLayout(new MigLayout("", "[grow]", "[][][grow]"));
        add(fileChooser, "wrap, grow");
        add(runButton, "alignx right, split");
        add(stopButton, "");
        add(resetButton, "wrap");
        add(scrollPane, "grow");
    }

    private void addListeners() {
        runButton.addActionListener(new StartButtonListener());
        stopButton.addActionListener(new StopButtonListener());
        resetButton.addActionListener(new ResetButtonListener());
    }

    private class StartButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            String directory = fileChooser.getTextField().getText();
            controller.start(directory);
        }
    }

    private class StopButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            controller.stop();
        }
    }

    private class ResetButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            // see below for reasoning about delegating the seemingly trivial
            // reset.
            controller.reset();
        }
    }

    @Override
    public void setStarted() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                runButton.setText("Running");
                runButton.setEnabled(false);
                resetButton.setEnabled(false);
                stopButton.setEnabled(true);
            }
        });
    }

    @Override
    public void setStopped() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                runButton.setText("Start");
                runButton.setEnabled(true);
                resetButton.setEnabled(true);
                stopButton.setEnabled(false);
            }
        });
    }

    /**
     * Why is this public, given that no action is required by the controller
     * when the gui wipes the movie list? If there are more than one gui
     * controlling the same thing, they will need to be synched. Thus, the gui
     * should only have control over its most trivial state.
     */
    @Override
    public void setResetted() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                movieListPanel.removeAllMovies();
            }
        });
    }


    @Override
    public void update(final Movie newMovie) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                movieListPanel.addMovie(newMovie);
            }
        });
    }

    @Override
    public JPanel getPanel() {
        return this;     
    }
}
