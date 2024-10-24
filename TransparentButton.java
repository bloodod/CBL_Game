import java.awt.*;
import javax.swing.*;
import java.awt.AlphaComposite;

public class TransparentButton extends JButton {

    /**
     * Creating variable for the background colours.
     */
    private Color backgroundColor;

    /**
     * This function constructs a transparent button
     * with the specified text and background colour.
     * @param text
     * @param bgColor
     */
    public TransparentButton(String text, Color bgColor) {
        super(text);
        this.backgroundColor = bgColor;
        setOpaque(false); // Make the button non-opaque
        setContentAreaFilled(false); // Remove the default filled background
        setBorderPainted(true); // Optionally paint the border
    }

    @Override
    protected void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g.create();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f)); // Set transparency level
        g2.setColor(backgroundColor); // Use the custom background color
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10); // Draw rounded rectangle as background
        g2.dispose();
        super.paintComponent(g);
    }
}
