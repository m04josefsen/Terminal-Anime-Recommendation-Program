import java.util.List;

public class Anime {
    private int malId;
    private String title;
    private String japTitle;
    private double score;
    private String imageUrl;
    private String description;
    private List<String> genres;
    private double userScore;
    private int members;

    public Anime(int malId, String title, String japTitle, double score, String imageUrl, String description, List<String> genres, double userScore) {
        this.malId = malId;
        this.title = title;
        this.japTitle = japTitle;
        this.score = score;
        this.imageUrl = imageUrl;
        this.description = description;
        this.genres = genres;
        this.userScore = userScore;
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

    public String getJapTitle() {
        return japTitle;
    }

    public void setJapTitle(String japTitle) {
        this.japTitle = japTitle;
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

    public double getUserScore() {
        return userScore;
    }

    public void setUserScore(double userScore) {
        this.userScore = userScore;
    }

    public int getMembers() {
        return members;
    }

    public void setMembers(int members) {
        this.members = members;
    }

    public String toString() {
        String print = "MyAnimeList ID: " + malId;
        print += "\nTitle: " + title;
        print += "\nJapanese Title: " + japTitle;
        print += "\nScore: " + score;
        print += "\nImageUrl: " + imageUrl;
        print += "\nDescription: " + description;
        print += "\nGenres: ";
        for(String genre : genres) {
            print += "\n- " + genre;
        }
        print += "\nUser score: " + userScore;
        print += "\nMembers: " + members;

        return print;
    }
}

