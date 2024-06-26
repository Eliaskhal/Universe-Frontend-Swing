package gui;

import connectors.Connector;
import data.Data;
import gui.Toaster.Toaster;
import gui.Utils.ButtonCustom;
import gui.Utils.LabelCustom;
import gui.Utils.TextAreaCustom;
import gui.Utils.UIUtils;
import model.User;
import parser.Parser;

import javax.swing.*;
import java.awt.*;

public class NewPostPanel extends JPanel {
    private LabelCustom PostLabel;
    private TextAreaCustom postArea;
    private ButtonCustom postButton;
    private Toaster toaster;
    private FeedUI feedUI;

    public NewPostPanel(FeedUI feedUI){
        User user = User.loadUser();
        int userID = user.getId();
        toaster = new Toaster(this);
        this.feedUI = feedUI;

        addPostLabel();
        addPostArea();
        addPostButton(userID);

        setBackground(new Color(101, 92, 86));
        setLayout(null);

        Dimension size = new Dimension(280, 300);
        setSize(size);
        setPreferredSize(size);
    }

    private void addPostLabel(){
        PostLabel = new LabelCustom("New Post", UIUtils.OFFWHITE);
        UIUtils.increaseFontSize(PostLabel, 36);
        PostLabel.setBounds(20, 20, 240, 40);
        add(PostLabel);
    }

    private void addPostArea(){
        postArea = new TextAreaCustom();
        postArea.setBounds(20, 80, 240, 150);
        JScrollPane jScrollPane = new JScrollPane(postArea);
        jScrollPane.setBounds(20, 80, 240, 150);
        jScrollPane.setBorder(null);
        jScrollPane.setViewportBorder(postArea.getBorder());
        add(jScrollPane);
    }

    private void addPostButton(int id){
        postButton = new ButtonCustom("Post", unused -> addPost(id));
        postButton.setBounds(150, 240, 80, 30);
        add(postButton);
    }

    private void addPost(int id){
        String content = postArea.getText();

        if(content.length() > 256){
            toaster.error("Post is long");
            postArea.warn();
            return;
        }

        String json = Connector.addPost(content, id);
        System.out.println(json);
        Data data = Parser.parseJson(json);

        if(data.getSuccess()){
            toaster.success("Post added");
            feedUI.refresh();
        } else{
            toaster.error("Could not add post");
            toaster.error("Unknown error");
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
