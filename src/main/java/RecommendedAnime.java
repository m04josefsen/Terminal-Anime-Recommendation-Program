import java.util.List;

public class RecommendedAnime {
    private int malId;
    private String title;
    private String imageUrl;
    private int votes;
    private double combinedScore;

    public RecommendedAnime(int malId, String title, String imageUrl, int votes, double combinedScore) {
        this.malId = malId;
        this.title = title;
        this.imageUrl = imageUrl;
        this.votes = votes;
        this.combinedScore = combinedScore;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public double getCombinedScore() {
        return combinedScore;
    }

    public void setCombinedScore(double combinedScore) {
        this.combinedScore = combinedScore;
    }

    public String toString() {
        String print = "MyAnimeList ID: " + malId;
        print += "\nTitle: " + title;
        print += "\nImageUrl: " + imageUrl;
        print += "\nVotes: " + votes;
        print += "\nCombined Score: " + combinedScore;

        return print;
    }
}