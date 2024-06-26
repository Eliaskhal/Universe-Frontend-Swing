package model;

import connectors.Connector;
import data.UserData;
import parser.Parser;

import java.io.*;

public class User implements Serializable {
    private int id;
    private String username;
    private String password;
    private String email;
    private String fullName;
    private String bio;
    private String role;
    private String registrationDate;

    public User() {}

    public User(int id, String username, String password, String email, String fullName, String bio, String role, String registrationDate){
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.bio = bio;
        this.role = role;
        this.registrationDate = registrationDate;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getBio() {
        return bio;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public String getRole() {
        return role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", bio='" + bio + '\'' +
                ", role='" + role + '\'' +
                ", registrationDate=" + registrationDate +
                '}';
    }

    public static User getUser(int id){
        String json = Connector.getUser(id);
        UserData userData = Parser.parseUserJson(json);

        return userData.getUser();
    }

    public static void saveUser(User user) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("user.ser"))) {
            oos.writeObject(user);
            System.out.println("User saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving user: " + e.getMessage());
        }
    }

    public static User loadUser() {
        User user = null;
        File file = new File("user.ser");
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                user = (User) ois.readObject();
                System.out.println("User loaded successfully.");
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error loading user: " + e.getMessage());
            }
        } else {
            saveUser(null);
            System.out.println("User file created with null user.");
        }
        return user;
    }

    public static void saveProfileUser(User user) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("profile-user.ser"))) {
            oos.writeObject(user);
            System.out.println("User saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving user: " + e.getMessage());
        }
    }

    public static User loadProfileUser() {
        User user = null;
        File file = new File("profile-user.ser");
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                user = (User) ois.readObject();
                System.out.println("User loaded successfully.");
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error loading user: " + e.getMessage());
            }
        } else {
            saveUser(null);
            System.out.println("User file created with null user.");
        }
        return user;
    }

    public static void resetUser(){ saveUser(null); }
    public static void resetProfileUser(){
        saveProfileUser(loadUser());
    }
}
