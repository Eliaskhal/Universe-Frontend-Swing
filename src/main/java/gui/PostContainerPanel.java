package gui;

import connectors.Connector;
import gui.Utils.UIUtils;
import model.Post;
import parser.Parser;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PostContainerPanel extends JPanel {
    private List<PostPanel> postPanels;
    private FeedUI feedUI;

    public PostContainerPanel(FeedUI feedUI) {
        this.feedUI = feedUI;
        innit();

        setLayout(null);
        setBackground(new Color(72, 56, 56));

        int height = 50;

        for(PostPanel postPanel: postPanels) height += postPanel.getHeight();
        height += (postPanels.size() - 1) * 10;

        Dimension size = new Dimension(570, height);
        setSize(size);
        setPreferredSize(size);
    }

    private void innit(){
        this.postPanels = getPostPanels();
        addPostPanels();
    }

    public void refresh(){
        removeAll();
        innit();
        repaint();
    }

    private List<PostPanel> getPostPanels() {
        List<PostPanel> postPanels = new ArrayList<>();

        String json = Connector.getAllPosts();
        List<Post> posts = Parser.parsePostsJson(json);

        for (Post post: posts) postPanels.add(new PostPanel(post, feedUI));
        return postPanels;
    }

    private void addPostPanels(){
        int y = 25;

        for(PostPanel postPanel: postPanels){
            postPanel.setBounds(30, y, postPanel.getWidth(), postPanel.getHeight());
            add(postPanel);
            y += postPanel.getHeight() + 10;
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        Dimension arcs = new Dimension(30,30);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


        graphics.setColor(getBackground());
        graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
        graphics.setColor(getForeground());
        graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
    }
}
