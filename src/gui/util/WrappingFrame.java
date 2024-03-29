package gui.util;

import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * A {@code JFrame} which wraps a {@code JPanel}.
 * 
 * @author Kevin Doran
 */
public class WrappingFrame {

    JFrame frame;

    /**
     * Wraps a {@code JPanel} in a {@code JFrame}. For reusability, most GUI
     * classes use JPanels to contain and layout components. This allows them to
     * be combined into other JPanels or JFrames. When these JPanels need to be
     * displayed, they can be wrapped in a {@code WrappingFrame}.
     * 
     * @param panel
     *            the panel to wrap.
     */
    public WrappingFrame(Component panel, String title) {
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.add(panel);
        frame.setMinimumSize(frame.getPreferredSize());
        frame.addComponentListener(new MinSizeComponentBehaviour());
        frame.pack();
    }

    /**
     * Returns the wrapping frame's {@code JFrame}.
     * 
     * @return the wrapping frame's {@code JFrame}.
     */
    public JFrame getFrame() {
        return frame;
    }
}
