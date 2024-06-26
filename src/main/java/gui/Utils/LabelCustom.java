package gui.Utils;

import javax.swing.*;
import java.awt.*;

public class LabelCustom extends JLabel {
    public LabelCustom(String text) {
        super(text, SwingConstants.LEFT);
        setFont(UIUtils.FONT_GENERAL_UI);
    }

    public LabelCustom(String text, Color textColor) {
        super(text, SwingConstants.LEFT);
        setColor(textColor);
        setFont(UIUtils.FONT_GENERAL_UI);
    }

    public void setColor(Color textColor) {
        setForeground(textColor);
    }

    public void setSize(int width, int height) {
        setPreferredSize(new Dimension(width, height));
    }

    public void setToCenter(){
        setHorizontalAlignment(0);
    }

    @Override
    public void setHorizontalAlignment(int alignment) {
        super.setHorizontalAlignment(alignment);
    }
}
