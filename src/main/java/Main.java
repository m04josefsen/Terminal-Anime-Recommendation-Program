import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        List<Anime> animelist = new ArrayList<>();
        boolean isActive = true;

        APIConnection api = new APIConnection();
        Map<String, String> genreMap = genreMap();

        String print = "Copy your Anime List from anilist.co and paste it under";
        System.out.println(print);

        //TODO: Nå må man ha en ekstra tom linje når du limer inn
        /*
        while(s.hasNextLine()) {
            String animeTitle = s.nextLine();
            double animeRating = Double.parseDouble(s.nextLine());
            int episodeCount = Integer.parseInt(s.nextLine());
            String animeFormat = s.nextLine();

            Anime anime = new Anime(animeTitle, animeRating);
            animelist.add(anime);
            System.out.println(anime);

            System.out.println(episodeCount);
            System.out.println(animeFormat);
        }
         */
        System.out.println("Ute av loop");
        List<SearchedAnime> list = api.fetchTopAnimesByGenre(genreMap.get("Romance"), 10);

        for(SearchedAnime anime : list) {
            System.out.println(anime);
        }

        //TODO: loop gjennom animelist å søk i APIConnection for å finne hvilken genres de er + hva hen rata de

        /*
        while(isActive) {

        }

         */
    }

    public static Map<String, String> genreMap() {
        Map<String, String> genreMap = new HashMap<>();

        genreMap.put("Action", "1");
        genreMap.put("Adventure", "2");
        genreMap.put("Cars", "3");
        genreMap.put("Comedy", "4");
        genreMap.put("Dementia", "5");
        genreMap.put("Demons", "6");
        genreMap.put("Mystery", "7");
        genreMap.put("Drama", "8");
        genreMap.put("Ecchi", "9");
        genreMap.put("Fantasy", "10");
        genreMap.put("Game", "11");
        genreMap.put("Hentai", "12");
        genreMap.put("Historical", "13");
        genreMap.put("Horror", "14");
        genreMap.put("Kids", "15");
        genreMap.put("Magic", "16");
        genreMap.put("Martial Arts", "17");
        genreMap.put("Mecha", "18");
        genreMap.put("Music", "19");
        genreMap.put("Parody", "20");
        genreMap.put("Samurai", "21");
        genreMap.put("Romance", "22");
        genreMap.put("School", "23");
        genreMap.put("Sci-Fi", "24");
        genreMap.put("Shoujo", "25");
        genreMap.put("Shoujo Ai", "26");
        genreMap.put("Shounen", "27");
        genreMap.put("Shounen Ai", "28");
        genreMap.put("Space", "29");
        genreMap.put("Sports", "30");
        genreMap.put("Super Power", "31");
        genreMap.put("Vampire", "32");
        genreMap.put("Yaoi", "33");
        genreMap.put("Yuri", "34");
        genreMap.put("Harem", "35");
        genreMap.put("Slice of Life", "36");
        genreMap.put("Supernatural", "37");
        genreMap.put("Military", "38");
        genreMap.put("Police", "39");
        genreMap.put("Psychological", "40");
        genreMap.put("Thriller", "41");
        genreMap.put("Seinen", "42");
        genreMap.put("Josei", "43");

        return genreMap;
    }
}
