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
import java.util.logging.Logger;

public class APIConnection {
    private static final Logger logger = Logger.getLogger(APIConnection.class.getName());
    private static final HttpClient client = HttpClient.newHttpClient();

    // Method to fetch top animes by genre
    public List<Anime> fetchTopAnimesByGenre(String genre, int limit) {
        String encodedGenre = URLEncoder.encode(genre, StandardCharsets.UTF_8);
        String url = "https://api.jikan.moe/v4/anime?genres=" + encodedGenre + "&order_by=score&sort=desc&limit=" + limit;

        return fetchAnimeList(url);
    }

    // Method to search for a single anime by query
    public Anime searchAnime(String searchQuery) {
        HttpClient client = HttpClient.newHttpClient();
        String encodedQuery = URLEncoder.encode(searchQuery, StandardCharsets.UTF_8);
        String url = "https://api.jikan.moe/v4/anime?q=" + encodedQuery + "&limit=1";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        Anime anime = null;

        try {
            int delay = 1000;
            Thread.sleep(delay);

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            anime = parseSingleAnime(response.body());
        } catch (Exception e) {
            logger.severe("There was an error while fetching data: " + e.getMessage());
        }
        return anime;
    }

    // Method to fetch and parse a list of recommended animes
    public List<RecommendedAnime> fetchRecommendedAnimes(int malId) {
        String url = "https://api.jikan.moe/v4/anime/" + malId + "/recommendations";

        List<RecommendedAnime> list = new ArrayList<>();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            list = parseRecommendedAnimes(response.body());
        } catch (Exception e) {
            logger.severe("There was an error while fetching data: " + e.getMessage());
        }

        return list;
    }

    // Common method to fetch and parse a list of animes from a given URL
    private List<Anime> fetchAnimeList(String url) {
        List<Anime> list = new ArrayList<>();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            list = parseAnimeList(response.body());
        } catch (Exception e) {
            logger.severe("There was an error while fetching data: " + e.getMessage());
        }

        return list;
    }

    // Method to parse the list of animes from JSON response
    private List<Anime> parseAnimeList(String responseBody) {
        List<Anime> list = new ArrayList<>();

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(responseBody);

            JsonNode dataNode = rootNode.path("data");
            if (dataNode.isArray() && dataNode.size() > 0) {
                for (JsonNode animeNode : dataNode) {
                    int malId = animeNode.path("mal_id").asInt();
                    String title = animeNode.path("title_english").asText();
                    double score = animeNode.path("score").asDouble();
                    String imageUrl = animeNode.path("images").path("jpg").path("image_url").asText();
                    String description = animeNode.path("synopsis").asText();

                    List<String> genres = new ArrayList<>();
                    JsonNode genresNode = animeNode.path("genres");
                    if (genresNode.isArray()) {
                        for (JsonNode genreNode : genresNode) {
                            genres.add(genreNode.path("name").asText());
                        }
                    }

                    Anime anime = new Anime(malId, title, score, imageUrl, description, genres, 0);
                    list.add(anime);
                }
            }
        } catch (Exception e) {
            logger.severe("There was an error while parsing data: " + e.getMessage());
        }

        return list;
    }

    // Method to parse the list of recommended animes from JSON response
    private List<RecommendedAnime> parseRecommendedAnimes(String responseBody) {
        List<RecommendedAnime> list = new ArrayList<>();

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(responseBody);

            JsonNode dataNode = rootNode.path("data");
            if (dataNode.isArray() && dataNode.size() > 0) {
                for (JsonNode recommendationNode : dataNode) {
                    JsonNode animeNode = recommendationNode.path("entry");
                    int malId = animeNode.path("mal_id").asInt();
                    String title = animeNode.path("title").asText();
                    String imageUrl = animeNode.path("images").path("jpg").path("image_url").asText();

                    int votes = recommendationNode.path("votes").asInt();

                    RecommendedAnime recommendedAnime = new RecommendedAnime(malId, title, imageUrl, votes, 0);
                    list.add(recommendedAnime);
                }
            }
        } catch (Exception e) {
            logger.severe("There was an error while parsing data: " + e.getMessage());
        }

        return list;
    }

    private Anime parseSingleAnime(String responseBody) {
        Anime anime = null;

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(responseBody);

            // Access the first anime data from the "data" array
            JsonNode dataNode = rootNode.path("data");
            if (dataNode.isArray() && dataNode.size() > 0) {
                JsonNode animeNode = dataNode.get(0); // Get the first anime

                int malId = animeNode.path("mal_id").asInt();
                String title = animeNode.path("title_english").asText();
                double score = animeNode.path("score").asDouble();
                String imageUrl = animeNode.path("images").path("jpg").path("image_url").asText();
                String description = animeNode.path("synopsis").asText();

                List<String> genres = new ArrayList<>();
                JsonNode genresNode = animeNode.path("genres");
                if (genresNode.isArray()) {
                    for (JsonNode genreNode : genresNode) {
                        genres.add(genreNode.path("name").asText());
                    }
                }

                anime = new Anime(malId, title, score, imageUrl, description, genres, 0);
            }
        } catch (Exception e) {
            logger.severe("There was an error while parsing data: " + e.getMessage());
        }

        return anime;
    }


}
