public class InputAnime {
    private String title;
    private double rating;

    public InputAnime(String title, double rating) {
        this.title = title;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String toString() {
        String print = "Anime title: " + getTitle();
        print += "\nRating: " + getRating();

        return print;
    }
}
