import java.util.*;

public class MalRecommendations {
    private static List<RecommendedAnime> malRecommendations;
    private static APIConnection api;
    private static List<Anime> watchedlist;

    public static void main(String[] args) {
        api = new APIConnection();
        malRecommendations = new ArrayList<>();

        Scanner s = new Scanner(System.in);
        List<InputAnime> animelist = new ArrayList<>();

        APIConnection api = new APIConnection();

        String print = "Copy your Anime List from anilist.co and paste it under";
        System.out.println(print);

        boolean isActive = true;

        while(isActive) {
            String animeTitle = s.nextLine();

            if(animeTitle.equals("END")) {
                isActive = false;
                continue;
                //break;
            }

            double animeRating = Double.parseDouble(s.nextLine());
            int episodeCount = Integer.parseInt(s.nextLine());
            String animeFormat = s.nextLine();

            InputAnime anime = new InputAnime(animeTitle, animeRating);
            animelist.add(anime);
        }

        updateUsersAnimelist(animelist);

        for(Anime anime : watchedlist) {
            getMalRecommendations(anime);
        }

        for(RecommendedAnime anime : malRecommendations) {
            System.out.println(anime);
            System.out.println();
        }

    }

    public static void updateUsersAnimelist(List<InputAnime> animelist) {
        watchedlist = new ArrayList<>();

        for(InputAnime anime : animelist) {
            Anime newAnime = api.searchAnime(anime.getTitle());
            if(newAnime != null) {
                System.out.println(newAnime);
                System.out.println();
                newAnime.setUserScore(anime.getRating());

                watchedlist.add(newAnime);
            }
        }
        System.out.println("SIZE: " + watchedlist.size());
    }

    public static void getMalRecommendations(Anime inAnime) {
        //TODO: Må kanskje ta inn et objekt fordi trenger scoren du har gitt også fakk

        APIConnection api = new APIConnection();

        List<RecommendedAnime> recommendedAnimelist = api.fetchRecommendedAnimes(inAnime.getMalId());

        HashMap<Integer, Integer> indexMultiplier = indexMultiplierMap();

        //TODO: må fikse hvis recommendedAnimeList har mindre enn 10
        //TODO: Må også oppdatere eksiterende verdier hvis de finnes, dvs hvis haikyuu finnes fra før må jeg addere verdien
        //TODO: hva hvis flere ting har like mange votes

        int i = 0;
        while (i < 10 && i < recommendedAnimelist.size()) {
            RecommendedAnime anime = recommendedAnimelist.get(i);
            int multiplier = indexMultiplier.get(i);
            anime.setCombinedScore(inAnime.getUserScore() * multiplier);

            malRecommendations.add(anime);
            i++;
        }
    }

    public static HashMap<Integer, Integer> indexMultiplierMap() {
        HashMap<Integer, Integer> hashmap = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            hashmap.put(i, 10 - i);
        }

        return hashmap;
    }

}
