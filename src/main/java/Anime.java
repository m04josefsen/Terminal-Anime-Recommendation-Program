import java.util.List;

public class Anime extends BaseAnime {
    private String japTitle;
    private String description;
    private List<String> genres;
    private double userScore;
    private int members;

    public Anime(int malId, String title, String japTitle, double score, String imageUrl, String description, List<String> genres, double userScore) {
        super(malId, title, imageUrl, score);
        this.japTitle = japTitle;
        this.description = description;
        this.genres = genres;
        this.userScore = userScore;
    }

    // Getters and setters for additional fields
    public String getJapTitle() {
        return japTitle;
    }

    public void setJapTitle(String japTitle) {
        this.japTitle = japTitle;
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
        String print = super.toString();
        print += "\nScore: " + getScore();
        print += "\nMembers: " + getMembers();
        return print;
    }

}
