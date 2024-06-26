package data;

import data.Data;
import model.User;

public class UserData extends Data {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
