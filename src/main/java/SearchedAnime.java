public class SearchedAnime {
    private int malId;
    private String title;
    private double score;
    private String imageUrl;
    private String description;

    public SearchedAnime(int malId, String title, double score, String imageUrl, String description) {
        this.malId = malId;
        this.title = title;
        this.score = score;
        this.imageUrl = imageUrl;
        this.description = description;
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

    public String toString() {
        String print = "MyAnimeList ID: " + malId;
        print += "\nTitle: " + title;
        print += "\nScore: " + score;
        print += "\nImageUrl: " + imageUrl;
        print += "\nDescription: " + description;
        return print;
    }
}

