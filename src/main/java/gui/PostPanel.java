package gui;

import connectors.Connector;
import data.UserData;
import gui.Utils.ContentLabelCustom;
import gui.Utils.LabelCustom;
import gui.Utils.SubLabelCustom;
import gui.Utils.UIUtils;
import model.Post;
import model.User;
import parser.Parser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PostPanel extends JPanel {
    private LabelCustom username;
    private SubLabelCustom postingDate;
    private ContentLabelCustom content;
    private FeedUI feedUI;

    public PostPanel(Post post, FeedUI feedUI){
        int userID = post.getUserID();
        String usernameText = User.getUser(userID).getUsername();
        String postingDateText = post.getPostingDate();
        String contentText = post.getContent();
        this.feedUI = feedUI;

        contentText = addLineBreaks(contentText, 460);

        setLayout(null);
        setOpaque(true);

        int x = getContentWidth(contentText, usernameText);
        int y = getY(contentText);
        Dimension size = new Dimension(x, getY(contentText));
        setSize(size);
        setPreferredSize(size);
        setBackground(UIUtils.COLOR_POST_BACKGROUND);

        getUsernameLabel(usernameText);
        getPostingDateSubLabel(postingDateText);
        getContentLabel(contentText, x, y);

        setVisible(true);
    }

    private void getUsernameLabel(String usernameText){
        username = new LabelCustom(usernameText, Color.BLACK);
        username.setBounds(20, 10, 300, 40);
        username.setCursor(new Cursor(Cursor.HAND_CURSOR));
        username.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String json = Connector.getUser(usernameText);
                UserData userData = Parser.parseUserJson(json);
                User.saveProfileUser(userData.getUser());
                feedUI.refreshProfile();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                username.setColor(Color.DARK_GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                username.setColor(Color.BLACK);
            }
        });
        this.add(username);
    }

    private void getPostingDateSubLabel(String postingDateText){
        postingDate = new SubLabelCustom(postingDateText, 20, 50);
        this.add(postingDate);
    }

    private void getContentLabel(String contentText, int x, int y){
        content = new ContentLabelCustom(contentText, Color.lightGray);
        content.setBounds(20, 70, x - 40, y - 20);
        this.add(content);
    }


    private static int getY(String content) {
        String[] lines = content.split("\\n");

        JLabel tempLabel = new LabelCustom(content);
        FontMetrics fm = tempLabel.getFontMetrics(tempLabel.getFont());

        int lineHeight = fm.getHeight();

        int totalHeight = lines.length * lineHeight + 10;

        int padding = 90;
        int panelHeight = totalHeight + padding;

        return panelHeight;
    }

    private int getContentWidth(String contentText, String username) {
        FontMetrics metrics = getFontMetrics(UIUtils.FONT_GENERAL_UI);
        int minWidth = metrics.stringWidth(username);
        String[] lines = contentText.split("\n");
        for (String line : lines) {
            int width = metrics.stringWidth(line);
            minWidth = Math.max(minWidth, width);
        }
        return Math.min(500, minWidth + 40);
    }

    public String addLineBreaks(String text, int maxWidth) {
        StringBuilder result = new StringBuilder();
        StringBuilder currentLine = new StringBuilder();
        FontMetrics fm = getFontMetrics(UIUtils.FONT_GENERAL_UI);
        for (char c : text.toCharArray()) {
            currentLine.append(c);
            int lineWidth = fm.stringWidth(currentLine.toString());
            if (lineWidth > maxWidth) {
                int lastSpaceIndex = currentLine.lastIndexOf(" ");
                if (lastSpaceIndex != -1) {
                    result.append(currentLine.substring(0, lastSpaceIndex)).append("\n");
                    currentLine = new StringBuilder(currentLine.substring(lastSpaceIndex + 1));
                } else {
                    result.append(currentLine).append("\n");
                    currentLine = new StringBuilder();
                }
            }
        }
        result.append(currentLine);
        return result.toString();
    }


    @Override
    protected void paintComponent(Graphics g) {
        Dimension arcs = new Dimension(30,30);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


        //Draws the rounded panel with borders.
        graphics.setColor(getBackground());
        graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
        graphics.setColor(getForeground());
        graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
    }


}
