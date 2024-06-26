package gui.Utils;

import javax.swing.*;

import java.awt.*;

import static gui.Utils.UIUtils.COLOR_OUTLINE;
import static gui.Utils.UIUtils.FONT_FORGOT_PASSWORD;

public class SubLabelCustom extends JLabel {
    public SubLabelCustom(String text, int xPos, int yPos){
        super(text);
        setForeground(COLOR_OUTLINE);
        setFont(FONT_FORGOT_PASSWORD);

        Dimension prefSize = getPreferredSize();
        setBounds(xPos, yPos, prefSize.width, prefSize.height);
    }

}
