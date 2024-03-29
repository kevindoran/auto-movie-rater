package gui.errordisplay;

import java.awt.Component;

import javax.swing.JOptionPane;

/**
 * An error display class that displays error in a pop-up window.
 * 
 * @author Kevin Doran
 */
public class PopupErrorDisplay implements ErrorDisplay {
    private Component parentComponent;

    /**
     * Sets the swing component over which the pop-up window will appear over.
     * 
     * @param the
     *            swing component over which to appear.
     */
    @Override
    public void setParentComponent(Component parentComponent) {
        this.parentComponent = parentComponent;
    }

    @Override
    public void showErrorMessage(String message) {
        showErrorMessage(parentComponent, message);
    }

    @Override
    public void showErrorMessage(Component parentComponent, String message) {
        final ScrollTextArea textArea = new ScrollTextArea(message);
        JOptionPane.showMessageDialog(parentComponent, textArea, "Error",
                JOptionPane.WARNING_MESSAGE);
    }
}
