package gui;

import java.awt.*;
import java.awt.event.*;
import java.util.Objects;
import javax.swing.*;
import gui.Toaster.Toaster;
import gui.Utils.*;
import gui.Utils.TextFieldCustom;
import connectors.Connector;
import data.UserData;
import model.User;
import parser.*;

public class LoginUI extends JFrame {

    private final Toaster toaster;
    private TextFieldCustom usernameField;
    private TextFieldPassword passwordField;

    public static void main(String[] args) {
        new LoginUI();
    }

    public LoginUI() {
        JPanel mainJPanel = getMainJPanel();

        addLogo(mainJPanel);
        addSeparator(mainJPanel);
        addUsernameTextField(mainJPanel);
        addPasswordTextField(mainJPanel);
        addLoginButton(mainJPanel);
        addRegisterButton(mainJPanel);

        this.setResizable(false);
        this.add(mainJPanel);
        this.pack();
        this.setVisible(true);
        this.toFront();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width / 2 - getWidth() / 2, screenSize.height / 2 - getHeight() / 2);

        toaster = new Toaster(mainJPanel);
    }

    private JPanel getMainJPanel() {
        this.setUndecorated(false);

        Dimension size = new Dimension(800, 400);

        JPanel panel1 = new JPanel();
        panel1.setSize(size);
        panel1.setPreferredSize(size);
        panel1.setBackground(UIUtils.COLOR_BACKGROUND);
        panel1.setLayout(null);

        return panel1;
    }

    private void addLogo(JPanel panel1) {
        JLabel label1 = new JLabel();
        label1.setFocusable(false);
        label1.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("logo.png")).getFile()));
        panel1.add(label1);
        label1.setBounds(55, 146, 200, 110);
    }

    private void addSeparator(JPanel panel1) {
        JSeparator separator1 = new JSeparator();
        separator1.setOrientation(SwingConstants.VERTICAL);
        separator1.setForeground(UIUtils.COLOR_OUTLINE);
        panel1.add(separator1);
        separator1.setBounds(310, 80, 1, 240);
    }

    private void addUsernameTextField(JPanel panel1) {
        LabelCustom usernameLabel = new LabelCustom("Username or Email", UIUtils.OFFWHITE);
        usernameLabel.setBounds(423, 70, 250, 44);
        panel1.add(usernameLabel);

        usernameField = new TextFieldCustom();
        usernameField.setBounds(423, 120, 250, 44);
        panel1.add(usernameField);
    }

    private void addPasswordTextField(JPanel panel1) {
        LabelCustom passwordLabel = new LabelCustom("Password", UIUtils.OFFWHITE);
        passwordLabel.setBounds(423, 170, 250, 44);
        panel1.add(passwordLabel);

        passwordField = new TextFieldPassword();

        passwordField.setBounds(423, 220, 250, 44);
        passwordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                passwordField.setForeground(Color.white);
                passwordField.setBorderColor(UIUtils.COLOR_INTERACTIVE);
            }

            @Override
            public void focusLost(FocusEvent e) {
                passwordField.setForeground(UIUtils.COLOR_OUTLINE);
                passwordField.setBorderColor(UIUtils.COLOR_OUTLINE);
            }
        });

        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER)
                    loginEventHandler();
            }
        });
        panel1.add(passwordField);
    }

    private void addLoginButton(JPanel panel1) {
        ButtonCustom loginButton = new ButtonCustom(UIUtils.BUTTON_TEXT_LOGIN, unused -> loginEventHandler());

        loginButton.setBackground(UIUtils.COLOR_BACKGROUND);
        loginButton.setBounds(423, 287, 250, 44);
        panel1.add(loginButton);
    }

    private void addRegisterButton(JPanel panel1) {
        panel1.add(new HyperlinkText(UIUtils.BUTTON_TEXT_REGISTER, 631, 340, () -> {
            RegisterUI registerUI = new RegisterUI();
            registerUI.setVisible(true);
            this.dispose();
        }));
    }

    private void loginEventHandler() {
        try {
            String username = usernameField.getText().strip();
            String password = new String(passwordField.getPassword()).strip();
            String response = Connector.login(username, password);
            UserData userData = Parser.parseUserJson(response);

            if (userData.getSuccess()) {
                toaster.success("Logged in successfully");
                User.saveUser(userData.getUser());
                User.resetProfileUser();
                FeedUI feedUI = new FeedUI();
                feedUI.setVisible(true);
                dispose();
            } else {
                String message = userData.getMessage();
                switch (message) {
                    case "Username not found":
                        toaster.error("Username not found");
                        usernameField.warn();
                        break;
                    case "Incorrect Password":
                        toaster.error("Incorrect Password");
                        passwordField.warn();
                        break;
                    default:
                        toaster.error("Unknown error occurred");
                        break;
                }
            }
        } catch (Exception e){
            toaster.error("Cannot connect to the server");
            System.out.println(e.getMessage());
        }
    }
}

