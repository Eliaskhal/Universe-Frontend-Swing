package connectors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class Connector {
    private static URL url;
    private static HttpURLConnection connection;
    private static String connectionRequestMethod;
    private static BufferedReader reader;
    private static StringBuilder response;

    public static String getResponse(String urlAddress, String requestMethod) throws IOException {
        url = new URL(urlAddress);
        connection = (HttpURLConnection) url.openConnection();
        connectionRequestMethod = requestMethod;

        connection.setRequestMethod(requestMethod);
        int responseCode = connection.getResponseCode();

        if (responseCode != 200) return null;

        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        response = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();
        connection.disconnect();

        return response.toString();
    }

    public static String login(String login, String password){
        String baseUrl = "http://localhost:8080/api/login";
        String queryParams = String.format("?login=%s&password=%s",
                URLEncoder.encode(login, StandardCharsets.UTF_8),
                URLEncoder.encode(password, StandardCharsets.UTF_8));
        String url = baseUrl + queryParams;
        String response;
        try {
            response = Connector.getResponse(url, "GET");
            return response;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String register(String username, String password, String email, String fullname, String role){
        String baseUrl = "http://localhost:8080/api/register";
        String queryParams = String.format("?username=%s&password=%s&email=%s&fullname=%s&role=%s",
                URLEncoder.encode(username, StandardCharsets.UTF_8),
                URLEncoder.encode(password, StandardCharsets.UTF_8),
                URLEncoder.encode(email, StandardCharsets.UTF_8),
                URLEncoder.encode(fullname, StandardCharsets.UTF_8),
                URLEncoder.encode(role, StandardCharsets.UTF_8));
        String url = baseUrl + queryParams;
        String response;
        try {
            response = Connector.getResponse(url, "POST");
            return response;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String getUser(int id){
        String baseUrl = "http://localhost:8080/api/user";
        String queryParams = String.format("?id=%s",
                URLEncoder.encode(String.valueOf(id), StandardCharsets.UTF_8));
        String url = baseUrl + queryParams;
        String response;
        try {
            response = Connector.getResponse(url, "GET");
            return response;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String getUser(String username){
        String baseUrl = "http://localhost:8080/api/user/username";
        String queryParams = String.format("?username=%s",
                URLEncoder.encode(String.valueOf(username), StandardCharsets.UTF_8));
        String url = baseUrl + queryParams;
        String response;
        try {
            response = Connector.getResponse(url, "GET");
            return response;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String addPost(String content, int id){
        String baseUrl = "http://localhost:8080/api/post/new";
        String queryParams = String.format("?id=%s&content=%s",
                URLEncoder.encode(String.valueOf(id), StandardCharsets.UTF_8),
                URLEncoder.encode(String.valueOf(content), StandardCharsets.UTF_8));
        String url = baseUrl + queryParams;
        String response;
        try {
            response = Connector.getResponse(url, "POST");
            return response;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String getAllPosts(){
        String url = "http://localhost:8080/api/post/all";
        String response;
        try {
            response = Connector.getResponse(url, "GET");
            return response;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
