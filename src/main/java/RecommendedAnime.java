public class RecommendedAnime extends BaseAnime {
    private int votes;
    private double combinedScore;

    public RecommendedAnime(int malId, String title, String imageUrl, int votes, double combinedScore) {
        super(malId, title, imageUrl, combinedScore);
        this.votes = votes;
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
        String print = "MyAnimeList ID: " + getMalId();
        print += "\nTitle: " + getTitle();
        print += "\nImageUrl: " + getImageUrl();
        print += "\nVotes: " + getVotes();
        print += "\nCombined Score: " + getCombinedScore();

        return print;
    }
}