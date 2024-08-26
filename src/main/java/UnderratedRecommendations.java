import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class UnderratedRecommendations {
    private static List<Genre> allGenres;
    private static List<Anime> recommendedList;
    private static APIConnection api;

    public static List<Anime> main(List<InputAnime> inputWatchedlist) {
        long time = System.currentTimeMillis();

        initializeGenres();
        api = new APIConnection();
        List<InputAnime> watchedlist = inputWatchedlist;
        recommendedList = new ArrayList<>();

        findingGenreScore(watchedlist);
        recommendationAlgorithm();

        // Randomizing the list
        Collections.shuffle(recommendedList);

        System.out.println("Your top 3 genres are: ");
        for(int i = 0; i < 3; i++) {
            System.out.println(allGenres.get(i));
            System.out.println();
        }

        System.out.println("Your recommended animes are: ");
        int counter = 1;
        if(recommendedList.size() > 10) {
            for(int i = 0; i < 10; i++) {
                System.out.println("Number " + counter + " recommendation");
                System.out.println(recommendedList.get(i));
                System.out.println();
                counter++;
            }
        }
        else {
            for(Anime anime : recommendedList) {
                System.out.println("Number " + counter + " recommendation");
                System.out.println(anime);
                System.out.println();
                counter++;
            }
        }

        time = (System.currentTimeMillis() - time) / 1000;
        System.out.println("Time taken: " + time + " seconds");

        return recommendedList;
    }

    public static void findingGenreScore(List<InputAnime> animelist) {
        List<Anime> watchedlist = new ArrayList<>();

        int count = 1;
        for(InputAnime anime : animelist) {
            Anime newAnime = api.searchAnime(anime.getTitle());

            if(newAnime != null) {
                System.out.println(count + " / " + animelist.size() + " animes analyzed");
                count++;

                watchedlist.add(newAnime);
                List<String> genres = newAnime.getGenres();

                // Adding to the genre score from the users rating
                for(String genre : genres) {
                    for(Genre g : allGenres) {
                        if(genre.equals(g.getGenreName())) {
                            g.setGenreCount(g.getGenreCount() + 1);
                            g.setCollectiveRating(g.getCollectiveRating() + anime.getRating());
                        }
                    }
                }
            }
        }

        addGenreScore();
    }

    public static void recommendationAlgorithm() {
        final int MAXMEMBERS = 300000;

        for(int i = 0; i < 3; i++) {
            Genre genre = allGenres.get(i);
            List<Anime> templist = api.fetchTopAnimesByGenre(genre.getGenreCode(), 25);

            for(Anime a : templist) {
                if(a.getMembers() <= MAXMEMBERS) {
                    recommendedList.add(a);
                }
            }
        }
    }

    // Calculating the genre score, 0 - 10, and adding to the genre objects
    public static void addGenreScore() {
        for(Genre g : allGenres) {
            g.setGenreScore(g.getCollectiveRating() / (g.getGenreCount() + 1));
        }
        allGenres.sort(Comparator
                .comparingDouble(Genre::getGenreScore).reversed());
    }

    // Intializing genres and adding them to a list
    public static void initializeGenres() {
        allGenres = new ArrayList<>();

        Genre action = new Genre("Action", "1", 0, 0.0);
        Genre adventure = new Genre("Adventure", "2", 0, 0.0);
        Genre cars = new Genre("Cars", "3", 0, 0.0);
        Genre comedy = new Genre("Comedy", "4", 0, 0.0);
        Genre dementia = new Genre("Dementia", "5", 0, 0.0);
        Genre demons = new Genre("Demons", "6", 0, 0.0);
        Genre mystery = new Genre("Mystery", "7", 0, 0.0);
        Genre drama = new Genre("Drama", "8", 0, 0.0);
        Genre ecchi = new Genre("Ecchi", "9", 0, 0.0);
        Genre fantasy = new Genre("Fantasy", "10", 0, 0.0);
        Genre game = new Genre("Game", "11", 0, 0.0);
        Genre hentai = new Genre("Hentai", "12", 0, 0.0);
        Genre historical = new Genre("Historical", "13", 0, 0.0);
        Genre horror = new Genre("Horror", "14", 0, 0.0);
        Genre kids = new Genre("Kids", "15", 0, 0.0);
        Genre magic = new Genre("Magic", "16", 0, 0.0);
        Genre martialArts = new Genre("Martial Arts", "17", 0, 0.0);
        Genre mecha = new Genre("Mecha", "18", 0, 0.0);
        Genre music = new Genre("Music", "19", 0, 0.0);
        Genre parody = new Genre("Parody", "20", 0, 0.0);
        Genre samurai = new Genre("Samurai", "21", 0, 0.0);
        Genre romance = new Genre("Romance", "22", 0, 0.0);
        Genre school = new Genre("School", "23", 0, 0.0);
        Genre sciFi = new Genre("Sci-Fi", "24", 0, 0.0);
        Genre shoujo = new Genre("Shoujo", "25", 0, 0.0);
        Genre shoujoAi = new Genre("Shoujo Ai", "26", 0, 0.0);
        Genre shounen = new Genre("Shounen", "27", 0, 0.0);
        Genre shounenAi = new Genre("Shounen Ai", "28", 0, 0.0);
        Genre space = new Genre("Space", "29", 0, 0.0);
        Genre sports = new Genre("Sports", "30", 0, 0.0);
        Genre superPower = new Genre("Super Power", "31", 0, 0.0);
        Genre vampire = new Genre("Vampire", "32", 0, 0.0);
        Genre yaoi = new Genre("Yaoi", "33", 0, 0.0);
        Genre yuri = new Genre("Yuri", "34", 0, 0.0);
        Genre harem = new Genre("Harem", "35", 0, 0.0);
        Genre sliceOfLife = new Genre("Slice of Life", "36", 0, 0.0);
        Genre supernatural = new Genre("Supernatural", "37", 0, 0.0);
        Genre military = new Genre("Military", "38", 0, 0.0);
        Genre police = new Genre("Police", "39", 0, 0.0);
        Genre psychological = new Genre("Psychological", "40", 0, 0.0);
        Genre thriller = new Genre("Thriller", "41", 0, 0.0);
        Genre seinen = new Genre("Seinen", "42", 0, 0.0);
        Genre josei = new Genre("Josei", "43", 0, 0.0);

        allGenres.add(action);
        allGenres.add(adventure);
        allGenres.add(cars);
        allGenres.add(comedy);
        allGenres.add(dementia);
        allGenres.add(demons);
        allGenres.add(mystery);
        allGenres.add(drama);
        allGenres.add(ecchi);
        allGenres.add(fantasy);
        allGenres.add(game);
        allGenres.add(hentai);
        allGenres.add(historical);
        allGenres.add(horror);
        allGenres.add(kids);
        allGenres.add(magic);
        allGenres.add(martialArts);
        allGenres.add(mecha);
        allGenres.add(music);
        allGenres.add(parody);
        allGenres.add(samurai);
        allGenres.add(romance);
        allGenres.add(school);
        allGenres.add(sciFi);
        allGenres.add(shoujo);
        allGenres.add(shoujoAi);
        allGenres.add(shounen);
        allGenres.add(shounenAi);
        allGenres.add(space);
        allGenres.add(sports);
        allGenres.add(superPower);
        allGenres.add(vampire);
        allGenres.add(yaoi);
        allGenres.add(yuri);
        allGenres.add(harem);
        allGenres.add(sliceOfLife);
        allGenres.add(supernatural);
        allGenres.add(military);
        allGenres.add(police);
        allGenres.add(psychological);
        allGenres.add(thriller);
        allGenres.add(seinen);
        allGenres.add(josei);
    }


}
