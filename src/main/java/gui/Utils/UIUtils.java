package gui.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class UIUtils {
    public static final Font FONT_GENERAL_UI = new Font("Segoe UI", Font.PLAIN, 20);
    public static final Font FONT_UNIVERSE = new Font("Bitter", Font.PLAIN, 44);
    public static final Font FONT_FORGOT_PASSWORD = new Font("Segoe UI", Font.PLAIN, 12);

    public static final Color COLOR_OUTLINE = new Color(103, 112, 120);
    public static final Color COLOR_BACKGROUND = new Color(37, 51, 61);
    public static final Color COLOR_POST_BACKGROUND = new Color(60, 90, 100);
    public static final Color COLOR_INTERACTIVE = new Color(108, 216, 158);
    public static final Color COLOR_INTERACTIVE_DARKER = new Color(87, 171, 127);
    public static final Color OFFWHITE = new Color(229, 229, 229);

    public static final String BUTTON_TEXT_LOGIN = "Login";
    public static final String BUTTON_TEXT_FORGOT_PASS = "Forgot your password?";
    public static final String BUTTON_TEXT_REGISTER = "Register";

    public static final String PLACEHOLDER_TEXT_USERNAME = "Username/email";

    public static final int ROUNDNESS = 8;

    public static Graphics2D get2dGraphics(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.addRenderingHints(new HashMap<RenderingHints.Key, Object>() {{
            put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        }});
        return g2;
    }

    public static void increaseFontSize(JLabel label, int newSize) {
        Font originalFont = label.getFont();
        label.setFont(originalFont.deriveFont((float)newSize));
    }

    public static String capitalizeFirstLetterOfEachWord(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        String[] words = str.split("\\s+");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            String capitalizedWord = word.substring(0, 1).toUpperCase() + word.substring(1);
            result.append(capitalizedWord).append(" ");
        }

        return result.toString().trim();
    }
}