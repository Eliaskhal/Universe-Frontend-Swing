package gui;

import gui.Utils.LabelCustom;
import gui.Utils.UIUtils;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfilePanel extends JPanel {
    private LabelCustom lastNameLabel;
    private LabelCustom firstNameLabel;
    private LabelCustom usernameLabel;
    private LabelCustom roleLabel;

    public ProfilePanel(){
        innit();

        setBackground(new Color(218, 192, 163));
        setLayout(null);

        Dimension size = new Dimension(280, 200);
        setSize(size);
        setPreferredSize(size);
    }

    public void innit(){
        User user = User.loadProfileUser();
        System.out.println(user.getUsername());
        String fullname = UIUtils.capitalizeFirstLetterOfEachWord(user.getFullName());
        String username = user.getUsername();
        String role = UIUtils.capitalizeFirstLetterOfEachWord(user.getRole());

        addFullName(fullname);
        addUserName(username);
        addRole(role);
    }

    public void refresh(){
        removeAll();
        innit();
        repaint();
    }

    private void addFullName(String fullname){
        String[] name = fullname.split("\\s+");
        lastNameLabel = new LabelCustom(name[0], UIUtils.COLOR_BACKGROUND);
        UIUtils.increaseFontSize(lastNameLabel, 36);
        lastNameLabel.setBounds(20, 20, 240, 50);
        add(lastNameLabel);
        firstNameLabel = new LabelCustom(name[1], UIUtils.COLOR_BACKGROUND);
        UIUtils.increaseFontSize(firstNameLabel, 36);
        firstNameLabel.setBounds(20, 70, 240, 50);
        add(firstNameLabel);
    }

    private void addUserName(String username){
        usernameLabel = new LabelCustom(username, Color.DARK_GRAY);
        usernameLabel.setBounds(20, 120, 240, 30);
        UIUtils.increaseFontSize(usernameLabel, 16);
        add(usernameLabel);
    }

    private void addRole(String role){
        roleLabel = new LabelCustom(role, Color.DARK_GRAY);
        roleLabel.setBounds(20, 150, 240, 20);
        UIUtils.increaseFontSize(roleLabel, 16);
        add(roleLabel);
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
