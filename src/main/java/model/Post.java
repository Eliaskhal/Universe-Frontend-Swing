package model;

import java.sql.Date;

public class Post {
    private int postID;
    private String postingDate;
    private String content;
    private int userID;


    public Post(int postID, String postingDate, String content, int userID){
        this.postID = postID;
        this.userID = userID;
        this.content = content;
        this.postingDate = postingDate;
    }


    public int getPostID() {
        return postID;
    }

    public int getUserID() {
        return userID;
    }

    public String getContent() {
        return content;
    }

    public String getPostingDate() {
        return postingDate;
    }
}

