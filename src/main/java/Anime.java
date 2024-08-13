import java.util.List;

public class Anime {
    private int malId;
    private String title;
    private double score;
    private String imageUrl;
    private String description;
    private List<String> genres;

    public Anime(int malId, String title, double score, String imageUrl, String description, List<String> genres) {
        this.malId = malId;
        this.title = title;
        this.score = score;
        this.imageUrl = imageUrl;
        this.description = description;
        this.genres = genres;
    }

    public int getMalId() {
        return malId;
    }

    public void setMalId(int malId) {
        this.malId = malId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getGenres() {
        return genres;
    }
    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String toString() {
        String print = "MyAnimeList ID: " + malId;
        print += "\nTitle: " + title;
        print += "\nScore: " + score;
        print += "\nImageUrl: " + imageUrl;
        print += "\nDescription: " + description;
        print += "\nGenres: ";
        for(String genre : genres) {
            print += "\n- " + genre;
        }

        return print;
    }
}

