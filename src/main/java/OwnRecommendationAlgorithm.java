import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OwnRecommendationAlgorithm {
    private static Genre action;
    private static Genre adventure;
    private static Genre cars;
    private static Genre comedy;
    private static Genre dementia;
    private static Genre demons;
    private static Genre mystery;
    private static Genre drama;
    private static Genre ecchi;
    private static Genre fantasy;
    private static Genre game;
    private static Genre hentai;
    private static Genre historical;
    private static Genre horror;
    private static Genre kids;
    private static Genre magic;
    private static Genre martialArts;
    private static Genre mecha;
    private static Genre music;
    private static Genre parody;
    private static Genre samurai;
    private static Genre romance;
    private static Genre school;
    private static Genre sciFi;
    private static Genre shoujo;
    private static Genre shoujoAi;
    private static Genre shounen;
    private static Genre shounenAi;
    private static Genre space;
    private static Genre sports;
    private static Genre superPower;
    private static Genre vampire;
    private static Genre yaoi;
    private static Genre yuri;
    private static Genre harem;
    private static Genre sliceOfLife;
    private static Genre supernatural;
    private static Genre military;
    private static Genre police;
    private static Genre psychological;
    private static Genre thriller;
    private static Genre seinen;
    private static Genre josei;

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

        /*
        for(Genre g : allGenres) {
            System.out.println(g);
            System.out.println();
        }
         */

        for(Anime a : recommendedList) {
            System.out.println(a);
            System.out.println();
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
        // Søk etter underrated animes, gjennom rating vs antall anmeldelser
        // Sortert basert på score
        // Kanskje søk på "action" sortert på score, top 10 under x anmeldelser

        for(int i = 0; i < 3; i++) {
            Genre genre = allGenres.get(i);
            List<Anime> templist = api.fetchTopAnimesByGenre(genre.getGenreCode(), 25);

            for(Anime a : templist) {
                if(a.getMembers() <= 500000) { //TODO: Finn en riktig value, kun temp holder value
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

        action = new Genre("Action", "1", 0, 0.0);
        adventure = new Genre("Adventure", "2", 0, 0.0);
        cars = new Genre("Cars", "3", 0, 0.0);
        comedy = new Genre("Comedy", "4", 0, 0.0);
        dementia = new Genre("Dementia", "5", 0, 0.0);
        demons = new Genre("Demons", "6", 0, 0.0);
        mystery = new Genre("Mystery", "7", 0, 0.0);
        drama = new Genre("Drama", "8", 0, 0.0);
        ecchi = new Genre("Ecchi", "9", 0, 0.0);
        fantasy = new Genre("Fantasy", "10", 0, 0.0);
        game = new Genre("Game", "11", 0, 0.0);
        hentai = new Genre("Hentai", "12", 0, 0.0);
        historical = new Genre("Historical", "13", 0, 0.0);
        horror = new Genre("Horror", "14", 0, 0.0);
        kids = new Genre("Kids", "15", 0, 0.0);
        magic = new Genre("Magic", "16", 0, 0.0);
        martialArts = new Genre("Martial Arts", "17", 0, 0.0);
        mecha = new Genre("Mecha", "18", 0, 0.0);
        music = new Genre("Music", "19", 0, 0.0);
        parody = new Genre("Parody", "20", 0, 0.0);
        samurai = new Genre("Samurai", "21", 0, 0.0);
        romance = new Genre("Romance", "22", 0, 0.0);
        school = new Genre("School", "23", 0, 0.0);
        sciFi = new Genre("Sci-Fi", "24", 0, 0.0);
        shoujo = new Genre("Shoujo", "25", 0, 0.0);
        shoujoAi = new Genre("Shoujo Ai", "26", 0, 0.0);
        shounen = new Genre("Shounen", "27", 0, 0.0);
        shounenAi = new Genre("Shounen Ai", "28", 0, 0.0);
        space = new Genre("Space", "29", 0, 0.0);
        sports = new Genre("Sports", "30", 0, 0.0);
        superPower = new Genre("Super Power", "31", 0, 0.0);
        vampire = new Genre("Vampire", "32", 0, 0.0);
        yaoi = new Genre("Yaoi", "33", 0, 0.0);
        yuri = new Genre("Yuri", "34", 0, 0.0);
        harem = new Genre("Harem", "35", 0, 0.0);
        sliceOfLife = new Genre("Slice of Life", "36", 0, 0.0);
        supernatural = new Genre("Supernatural", "37", 0, 0.0);
        military = new Genre("Military", "38", 0, 0.0);
        police = new Genre("Police", "39", 0, 0.0);
        psychological = new Genre("Psychological", "40", 0, 0.0);
        thriller = new Genre("Thriller", "41", 0, 0.0);
        seinen = new Genre("Seinen", "42", 0, 0.0);
        josei = new Genre("Josei", "43", 0, 0.0);

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
