public class Genre {
    private String genreName;
    private String genreCode;
    private int genreCount;
    private double collectiveRating;
    private double genreScore;

    public Genre(String genreName, String genreCode, int genreCount, double collectiveRating) {
        this.genreName = genreName;
        this.genreCode = genreCode;
        this.genreCount = genreCount;
        this.collectiveRating = collectiveRating;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public String getGenreCode() {
        return genreCode;
    }

    public void setGenreCode(String genreCode) {
        this.genreCode = genreCode;
    }

    public int getGenreCount() {
        return genreCount;
    }

    public void setGenreCount(int genreCount) {
        this.genreCount = genreCount;
    }

    public double getCollectiveRating() {
        return collectiveRating;
    }

    public void setCollectiveRating(double collectiveRating) {
        this.collectiveRating = collectiveRating;
    }

    public double getGenreScore() {
        return genreScore;
    }

    public void setGenreScore(double genreScore) {
        this.genreScore = genreScore;
    }

    public String toString() {
        String print = "Genre name: " + genreName;
        print += "\nGenre code: " + genreCode;
        print += "\nGenre count: " + genreCount;
        print += "\nCollective rating: " + collectiveRating;
        print += "\nGenre score: " + genreScore;
        return print;
    }
}
