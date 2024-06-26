package gui.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class RadioButtonCustom extends JRadioButton {

    public RadioButtonCustom(String text) {
        super(text);
        setOpaque(false);
        setFocusable(false);
        setBackground(UIUtils.COLOR_BACKGROUND);
        setForeground(Color.white);
        setFont(UIUtils.FONT_GENERAL_UI);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        setSelectedIcon(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("radio-button-selected.png"))));
        setIcon(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("radio-button-unselected.png"))));

    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2 = UIUtils.get2dGraphics(g);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, UIUtils.ROUNDNESS, UIUtils.ROUNDNESS);
        super.paintComponent(g2);
    }

}
