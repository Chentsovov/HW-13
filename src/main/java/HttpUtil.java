import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.utils.URIBuilder;

import javax.xml.stream.events.Comment;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class HttpUtil {
    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();
    private static final Gson GSON = new Gson();
    private final String URL;

    public HttpUtil(String url) {
        URL = url;
    }

    public User sendGet(URI uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        List<User> userData = GSON.fromJson(response.body(), new TypeToken<List<User>>() {
        }.getType());
        return userData.get(0);
    }

    public List<User> sendGetWithListOfResults(URI uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return GSON.fromJson(response.body(), new TypeToken<List<User>>() {
        }.getType());
    }

    public User sendPost(URI uri, User userData) throws IOException, InterruptedException {
        final String requestBody = GSON.toJson(userData);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .header("Content-type", "application/json")
                .build();
        final HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return GSON.fromJson(response.body(), User.class);
    }

    public User sendPut(URI uri, User userData) throws IOException, InterruptedException {
        final String requestBody = GSON.toJson(userData);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                .header("Content-type", "application/json")
                .build();
        final HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return GSON.fromJson(response.body(), User.class);
    }

    public boolean sendDelete(URI uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().uri(uri).DELETE().build();
        HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return true;
    }

    public List<Comment> getCommentsFromLastPost(User userData) throws IOException, InterruptedException, URISyntaxException {
        URIBuilder URI_BUILDER = new URIBuilder(URI.create(URL));
        int idLastPost = getPostsByUser(userData).stream().mapToInt(Post::getId).max().orElse(0);
        URI uri = URI_BUILDER.setPath("comments").setParameter("postId", String.valueOf(idLastPost)).build();
        HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().header("Content-type", "application/json").build();
        final HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        List<Comment> comments = GSON.fromJson(response.body(), new TypeToken<List<Comment>>() {
        }.getType());
        String fileName = String.format("user-%d-post-%d-comments.json", userData.getId(), idLastPost);
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)
        ) {
            fileOutputStream.write(response.body().getBytes(StandardCharsets.UTF_8));
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        return comments;
    }

    public List<Todos> getOpenTask(User userData) throws URISyntaxException, IOException, InterruptedException {
        URIBuilder URI_BUILDER = new URIBuilder(URI.create(URL));
        URI uri = URI_BUILDER.setPath("users/" + userData.getId() + "/todos").setParameter("completed", "false").build();
        HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().header("Content-type", "application/json").build();
        final HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return GSON.fromJson(response.body(), new TypeToken<List<Todos>>() {
        }.getType());
    }

    public List<Post> getPostsByUser(User userData) throws IOException, InterruptedException, URISyntaxException {
        URIBuilder URI_BUILDER = new URIBuilder(URI.create(URL));
        URI uri = URI_BUILDER.setPath("users/" + userData.getId() + "/posts").build();
        HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().header("Content-type", "application/json").build();
        final HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return GSON.fromJson(response.body(), new TypeToken<List<Post>>() {
        }.getType());
    }


}