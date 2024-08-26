public class BaseAnime {
    private int malId;
    private String title;
    private String imageUrl;
    private double score;

    public BaseAnime(int malId, String title, String imageUrl, double score) {
        this.malId = malId;
        this.title = title;
        this.imageUrl = imageUrl;
        this.score = score;
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

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String toString() {
        String print = "MyAnimeList ID: " + getMalId();
        print += "\nTitle: " + getTitle();
        print += "\nImageUrl: " + getImageUrl();

        return print;
    }
}
