import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class APIConnection {
    public List<SearchedAnime> fetchTopAnimesByGenre(String genre, int limit) {
        HttpClient client = HttpClient.newHttpClient();
        String encodedGenre = URLEncoder.encode(genre, StandardCharsets.UTF_8);
        String url = "https://api.jikan.moe/v4/anime?genres=" + encodedGenre + "&order_by=score&sort=desc&limit=" + limit;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        List<SearchedAnime> list = new ArrayList<>();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            list = parseAnimeList(response.body());
        } catch (Exception e) {
            //logger.severe("There was an error while fetching top animes by genre: " + e.getMessage());
        }

        return list;
    }

    private List<SearchedAnime> parseAnimeList(String responseBody) {
        List<SearchedAnime> list = new ArrayList<>();

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(responseBody);

            JsonNode dataNode = rootNode.path("data");
            if (dataNode.isArray() && dataNode.size() > 0) {
                for (JsonNode animeNode : dataNode) {
                    int malId = animeNode.path("mal_id").asInt();
                    String title = animeNode.path("title").asText();
                    double score = animeNode.path("score").asDouble();
                    String imageUrl = animeNode.path("images").path("jpg").path("image_url").asText();
                    String description = animeNode.path("synopsis").asText();

                    SearchedAnime anime = new SearchedAnime(malId, title, score, imageUrl, description);
                    list.add(anime);
                }
            }
        } catch (Exception e) {
            //logger.severe("There was an error while parsing data: " + e.getMessage());
        }

        return list;
    }
}
