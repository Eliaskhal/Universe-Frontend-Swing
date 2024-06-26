package gui;

import javax.swing.*;
import parser.Parser;
import java.awt.*;

import gui.Toaster.Toaster;
import gui.Utils.*;
import connectors.Connector;
import data.Data;

public class RegisterUI extends JFrame {
    private final Toaster toaster;
    private TextFieldCustom firstNameField;
    private TextFieldCustom lastNameField;
    private TextFieldCustom usernameField;
    private TextFieldCustom emailField;
    private TextFieldPassword passwordField;
    private TextFieldPassword confirmPasswordField;
    private RadioButtonCustom studentRadioButton;
    private RadioButtonCustom professorRadioButton;
    private ButtonGroup buttonGroup;
    private ButtonCustom registerButton;
    private ButtonCustom loginButton;

    private final int yPadding = 120;
    private final int xPadding = 75;
    private final int xPaddingRadio = 50;
    private final int intraSeparator = 50;
    private final int interSeparator = 50;

    public RegisterUI() {
        JPanel mainJPanel = getMainJPanel();

        addTitle(mainJPanel);
        addFirstNameTextField(mainJPanel);
        addLastNameTextField(mainJPanel);
        addUsernameField(mainJPanel);
        addSeparator(mainJPanel);
        addEmailTextField(mainJPanel);
        addPasswordField(mainJPanel);
        addConfirmPasswordField(mainJPanel);
        addRoleRadioButtons(mainJPanel);
        addRegisterButton(mainJPanel);
        addLoginButton(mainJPanel);

        this.add(mainJPanel);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.toFront();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width / 2 - getWidth() / 2, screenSize.height / 2 - getHeight() / 2);

        toaster = new Toaster(mainJPanel);
    }

    private JPanel getMainJPanel() {
        this.setUndecorated(false);

        Dimension size = new Dimension(800, 600);

        JPanel panel1 = new JPanel();
        panel1.setSize(size);
        panel1.setPreferredSize(size);
        panel1.setBackground(UIUtils.COLOR_BACKGROUND);
        panel1.setLayout(null);

        return panel1;
    }

    private void addTitle(JPanel panel1){
        LabelCustom titleLabel = new LabelCustom("Register", new Color(158, 200, 185));
        titleLabel.setBounds(20, 20, 250, 80);
        titleLabel.setToCenter();
        UIUtils.increaseFontSize(titleLabel, 32);

        panel1.add(titleLabel);
    }

    private void addFirstNameTextField(JPanel panel1){
        LabelCustom firstNameLabel = new LabelCustom("First name", UIUtils.OFFWHITE);
        firstNameLabel.setBounds(xPadding, yPadding, 250, 45);
        panel1.add(firstNameLabel);

        firstNameField = new TextFieldCustom();
        firstNameField.setBounds(xPadding, yPadding + intraSeparator, 250, 45);
        panel1.add(firstNameField);
    }

    private void addLastNameTextField(JPanel panel1){
        int y = yPadding + intraSeparator + interSeparator;

        LabelCustom lastNameLabel = new LabelCustom("Last name", UIUtils.OFFWHITE);
        lastNameLabel.setBounds(xPadding, y, 250, 45);
        panel1.add(lastNameLabel);

        lastNameField = new TextFieldCustom();
        lastNameField.setBounds(xPadding, y + intraSeparator, 250, 45);
        panel1.add(lastNameField);
    }

    private void addUsernameField(JPanel panel){
        int y = yPadding + (intraSeparator + interSeparator) * 2;

        LabelCustom usernameLabel = new LabelCustom("Username", UIUtils.OFFWHITE);
        usernameLabel.setBounds(xPadding, y, 250, 45);
        panel.add(usernameLabel);

        usernameField = new TextFieldCustom();
        usernameField.setBounds(xPadding, y + intraSeparator, 250, 45);
        panel.add(usernameField);
    }

    private void addSeparator(JPanel panel1) {
        JSeparator separator1 = new JSeparator();
        separator1.setOrientation(SwingConstants.VERTICAL);
        separator1.setForeground(UIUtils.COLOR_OUTLINE);
        separator1.setBounds(xPadding * 2 + 250, yPadding + 10, 1, 310);

        panel1.add(separator1);
    }

    private void addEmailTextField(JPanel panel1){
        int x = xPadding * 3 + 250;

        LabelCustom emailLabel = new LabelCustom("Email address", UIUtils.OFFWHITE);
        emailLabel.setBounds(x, yPadding, 250, 45);
        panel1.add(emailLabel);

        emailField = new TextFieldCustom();
        emailField.setBounds(x, yPadding + intraSeparator, 250, 45);
        panel1.add(emailField);
    }

    private void addPasswordField(JPanel panel1){
        int x = xPadding * 3 + 250;
        int y = yPadding + intraSeparator + interSeparator;

        LabelCustom passwordLabel = new LabelCustom("Password", UIUtils.OFFWHITE);
        passwordLabel.setBounds(x, y, 250, 45);
        panel1.add(passwordLabel);

        passwordField = new TextFieldPassword();
        passwordField.setBounds(x, y + intraSeparator, 250, 45);
        panel1.add(passwordField);
    }

    private void addConfirmPasswordField(JPanel panel1){
        int x = xPadding * 3 + 250;
        int y = yPadding + (intraSeparator + interSeparator) * 2;

        LabelCustom confirmPasswordLabel = new LabelCustom("Confirm password", UIUtils.OFFWHITE);
        confirmPasswordLabel.setBounds(x, y, 250, 45);
        panel1.add(confirmPasswordLabel);

        confirmPasswordField = new TextFieldPassword();
        confirmPasswordField.setBounds(x, y + intraSeparator, 250, 45);
        panel1.add(confirmPasswordField);
    }

    private void addRoleRadioButtons(JPanel panel){
        int y = yPadding + (intraSeparator + interSeparator) * 3 + 10;

        LabelCustom roleLabel = new LabelCustom("Role:", UIUtils.OFFWHITE);
        roleLabel.setBounds(xPadding, y, 70, 45);
        panel.add(roleLabel);

        buttonGroup = new ButtonGroup();

        studentRadioButton = new RadioButtonCustom("Student");
        studentRadioButton.setBounds(xPadding + 100, y, 150, 45);
        buttonGroup.add(studentRadioButton);
        studentRadioButton.setSelected(true);
        panel.add(studentRadioButton);

        professorRadioButton = new RadioButtonCustom("Professor");
        professorRadioButton.setBounds(xPadding + xPaddingRadio + 200, y, 150, 45);
        buttonGroup.add(professorRadioButton);
        panel.add(professorRadioButton);
    }

    private void addRegisterButton(JPanel panel){
        registerButton = new ButtonCustom("Register", unused -> {
            try {
                register();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        registerButton.setBounds(625, 510, 150, 50);
        panel.add(registerButton);
    }

    private void addLoginButton(JPanel panel){
        loginButton = new ButtonCustom("<<Login", unused -> login());
        loginButton.setBounds(25, 510, 100, 50);
        panel.add(loginButton);
    }

    private void register() throws InterruptedException {
        for(TextFieldCustom i: new TextFieldCustom[]{usernameField, emailField, firstNameField, lastNameField}){
            if (i.getText().equals("")) {
                i.warn();
                if(i.equals(lastNameField)) {
                    for(TextFieldPassword t: new TextFieldPassword[]{passwordField, confirmPasswordField}){
                        String j = new String(t.getPassword());
                        if (j.equals("")) t.warn();
                    }
                    return;
                }
            }

        }

        String username = usernameField.getText().strip();
        String password = new String(passwordField.getPassword()).strip();
        String confirmPassword = new String(confirmPasswordField.getPassword()).strip();
        String email = emailField.getText().strip().toLowerCase();
        String fullname = firstNameField.getText().strip() + " " + lastNameField.getText().strip();
        String role = studentRadioButton.isSelected() ? "student" : "professor";

        if(!password.equals(confirmPassword)){
            toaster.error("Passwords don't match");
            passwordField.warn();
            confirmPasswordField.warn();
            return;
        }

        String json = Connector.register(username, password, email, fullname, role);
        Data data = Parser.parseJson(json);
        System.out.println(json);

        boolean success = data.getSuccess();
        String message = data.getMessage();

        if(success){
            toaster.success(message);
            toaster.success("You can log in now");
            Timer timer = new Timer(2000, e -> login());
            timer.setRepeats(false);
            timer.start();
        }else{
            switch (message.toLowerCase()){
                case "invalid fullname":
                    firstNameField.warn();
                    lastNameField.warn();
                    toaster.error("Invalid fullname");
                    toaster.warn("Your full name should not be longer than 30 characters");
                    break;
                case "username already available":
                    usernameField.warn();
                    toaster.error("Username already taken");
                    break;
                case "invalid username":
                    usernameField.warn();
                    toaster.error("Invalid username");
                    toaster.warn("Username can only contain letters, numbers, and dots");
                    toaster.warn("with a maximum length of 20 characters");
                    break;
                case "invalid password":
                    passwordField.warn();
                    toaster.error("Invalid password");
                    break;
                case "email address already available":
                    emailField.warn();
                    toaster.error("Email address already taken");
                    break;
                case "invalid email address":
                    emailField.warn();
                    toaster.error("Invalid email address");
                    toaster.warn("Your can only use UMI's academic emails");
                    break;
                default:
                    toaster.error("Unknown error occurred");
                    break;
            }
        }
    }

    private void login(){
        LoginUI loginUI = new LoginUI();
        loginUI.setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new RegisterUI().setVisible(true);
            }
        });
    }
}

