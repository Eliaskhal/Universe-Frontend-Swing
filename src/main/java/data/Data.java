package data;

import model.User;

public class Data {
    private boolean success;
    private String message;

    public boolean getSuccess(){
        return success;
    }
    public String getMessage(){
        return message;
    }


    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
