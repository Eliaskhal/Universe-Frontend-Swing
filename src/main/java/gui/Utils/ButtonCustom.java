package gui.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;

public class ButtonCustom extends JLabel {

    private Color[] buttonColors = {UIUtils.COLOR_INTERACTIVE, Color.white};
    private Consumer<Void> buttonPressedCallback;

    public ButtonCustom (String text, Consumer<Void> buttonPressedCallback) {
        super(text, SwingConstants.CENTER);
        setOpaque(false);
        setFont(UIUtils.FONT_GENERAL_UI);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.buttonPressedCallback = buttonPressedCallback;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                buttonPressed();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setButtonColors(UIUtils.COLOR_INTERACTIVE_DARKER, UIUtils.OFFWHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setButtonColors(UIUtils.COLOR_INTERACTIVE, Color.white);
            }
        });
    }

    private void setButtonColors(Color bgColor, Color textColor) {
        buttonColors[0] = bgColor;
        buttonColors[1] = textColor;
        repaint();
    }

    private void buttonPressed() {
        if (buttonPressedCallback != null) {
            buttonPressedCallback.accept(null);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = UIUtils.get2dGraphics(g);
        super.paintComponent(g2);

        Insets insets = getInsets();
        int w = getWidth() - insets.left - insets.right;
        int h = getHeight() - insets.top - insets.bottom;
        g2.setColor(buttonColors[0]);
        g2.fillRoundRect(insets.left, insets.top, w, h, UIUtils.ROUNDNESS, UIUtils.ROUNDNESS);

        FontMetrics metrics = g2.getFontMetrics(UIUtils.FONT_GENERAL_UI);
        int x2 = (getWidth() - metrics.stringWidth(getText())) / 2;
        int y2 = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        g2.setFont(UIUtils.FONT_GENERAL_UI);
        g2.setColor(buttonColors[1]);
        g2.drawString(getText(), x2, y2);
    }
}
