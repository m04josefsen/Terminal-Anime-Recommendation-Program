public class Anime {
    private String name;
    private double rating;

    public Anime(String name, double rating) {
        this.name = name;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String toString() {
        String print = "Anime name: " + name;
        print += "\nRating: " + rating;
        return print;
    }
}
