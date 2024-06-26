package gui.Utils;

import javax.swing.*;
import java.awt.*;

public class ScrollPaneCustom extends JScrollPane {
    private int arcWidth = 30;
    private int arcHeight = 30;

    public ScrollPaneCustom(Component view) {
        super(view);
        setBorder(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Dimension arcs = new Dimension(arcWidth, arcHeight);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics.setColor(getBackground());
        graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height); // Paint background
        graphics.setColor(getForeground());
        graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height); // Paint border
    }
}
