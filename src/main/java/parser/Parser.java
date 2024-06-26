package parser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import data.Data;
import data.UserData;
import model.Post;

import java.lang.reflect.Type;
import java.util.List;

public class Parser {
    private static Gson gson = new Gson();

    public static Data parseJson(String json){
        Data data = gson.fromJson(json, Data.class);
        return data;
    }
    public static UserData parseUserJson(String json){
        UserData userData = gson.fromJson(json, UserData.class);
        return userData;
    }

    public static List<Post> parsePostsJson(String json){
        Type listType = new TypeToken<List<Post>>(){}.getType();
        return gson.fromJson(json, listType);
    }
}
