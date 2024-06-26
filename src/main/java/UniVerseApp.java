import gui.FeedUI;
import gui.LoginUI;
import gui.Utils.RefreshTask;
import model.User;

import javax.swing.*;


public class UniVerseApp {

    public static void main(String[] args) {
        User currentUser = User.loadUser();
        User.resetProfileUser();

        if(currentUser == null){
            LoginUI loginUI = new LoginUI();
            loginUI.setVisible(true);
        } else{
            FeedUI feedUI = new FeedUI();
            feedUI.setVisible(true);
            Timer timer = new Timer(2000, new RefreshTask(feedUI));
            timer.start();
        }
    }
}
