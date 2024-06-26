package gui.Utils;

import javax.swing.*;
import java.awt.*;

public class ContentLabelCustom extends LabelCustom{
    private String text;

    public ContentLabelCustom(String text, Color textColor) {
        super(text, textColor);
        this.text = text;
    }

    @Override
    public Dimension getPreferredSize() {
        FontMetrics metrics = getFontMetrics(getFont());
        String[] lines = text.split("\n");
        int maxWidth = 0;
        for (String line : lines) {
            int width = metrics.stringWidth(line);
            maxWidth = Math.max(maxWidth, width);
        }
        int lineHeight = metrics.getHeight();
        int height = lineHeight * lines.length;
        return new Dimension(maxWidth, height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        FontMetrics metrics = g2.getFontMetrics(getFont());
        String[] lines = super.getText().split("\n");
        int lineHeight = metrics.getHeight();
        int y = lineHeight;
        for (String line : lines) {
            g2.drawString(line, 0, y);
            y += lineHeight;
        }

        g2.dispose();
    }
}
