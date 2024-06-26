package gui;

import gui.Utils.ScrollPaneCustom;
import gui.Utils.UIUtils;
import model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class FeedUI extends JFrame {
    private PostContainerPanel postsPanel;
    private ProfilePanel profilePanel;
    private NewPostPanel newPostPanel;

    public FeedUI(){
        addLogo();
        addProfilePanel();
        addPostPanel();
        addNewPostPanel();
        addLogoutButton();

        setLayout(null);
        setTitle("Feed");
        setSize(1000, 700);
        setResizable(false);
        getContentPane().setBackground(UIUtils.COLOR_BACKGROUND.darker());
        getRootPane().setBackground(UIUtils.COLOR_BACKGROUND.darker());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void addLogo() {
        JLabel label1 = new JLabel();
        label1.setFocusable(false);
        label1.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("logo.png")).getFile()));
        label1.setBounds(400, 20, 200, 50);
        add(label1);
    }

    private void addProfilePanel(){
        profilePanel = new ProfilePanel();
        profilePanel.setBounds(50, 100, 280, 200);
        add(profilePanel);
    }

    private void addPostPanel(){
        postsPanel = new PostContainerPanel(this);
        ScrollPaneCustom jScrollPane = new ScrollPaneCustom(postsPanel);
        jScrollPane.setSize(postsPanel.getWidth(), 560);
        jScrollPane.setBounds(380, 100, postsPanel.getWidth() + 10, 600);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setBorder(null);
        jScrollPane.setViewportBorder(null);
        add(jScrollPane);
    }

    private void addNewPostPanel(){
        newPostPanel = new NewPostPanel(this);
        newPostPanel.setBounds(50, 320, 280, 300);
        add(newPostPanel);
    }

    private void addLogoutButton(){
        JButton logoutButton = new JButton();
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("logout.png")));
        logoutButton.setContentAreaFilled(false);
        logoutButton.setIcon(icon);
        logoutButton.setBorder(null);
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(FeedUI.this, "Are you sure you want to log out?", "Logout", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    User.resetUser();
                    LoginUI loginUI = new LoginUI();
                    loginUI.setVisible(true);
                    dispose();
                }
            }
        });
        logoutButton.setBounds(900, 20, 64, 64);
        add(logoutButton);
    }

    public void refresh(){
        postsPanel.refresh();
        repaint();
    }

    public void refreshProfile(){
        profilePanel.refresh();
        repaint();
    }

    public static void main(String[] args) {
        FeedUI i = new FeedUI();
        i.setVisible(true);
    }
}
