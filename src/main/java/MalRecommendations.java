import java.util.*;
import java.util.logging.Logger;

public class MalRecommendations {
    private static List<RecommendedAnime> malRecommendations;
    private static List<Anime> watchedlist;
    private static APIConnection api;
    private static Logger logger;

    public static List<RecommendedAnime> main(List<Anime> inputWatchedlist) {
        long time = System.currentTimeMillis();

        logger = Logger.getLogger(MalRecommendations.class.getName());

        api = new APIConnection();
        malRecommendations = new ArrayList<>();
        watchedlist = inputWatchedlist;

        int count = 1;

        // Getting recommendations from MaL
        for(Anime anime : watchedlist) {
            getMalRecommendations(anime);
            System.out.println(count + " / " + watchedlist.size() + " animes analyzed");
            count++;
        }

        malRecommendations.sort(Comparator
                .comparingDouble(RecommendedAnime::getCombinedScore).reversed());

        removeDuplicateAnimes();

        int counter = 1;
        if(malRecommendations.size() > 10) {
            for(int i = 0; i < 10; i++) {
                System.out.println("Number " + counter + " recommendation");
                System.out.println(malRecommendations.get(i));
                System.out.println();
                counter++;
            }
        }
        else {
            for(RecommendedAnime anime : malRecommendations) {
                System.out.println("Number " + counter + " recommendation");
                System.out.println(anime);
                System.out.println();
                counter++;
            }
        }

        time = (System.currentTimeMillis() - time) / 1000;
        System.out.println("Time taken: " + time + " seconds");

        return malRecommendations;
    }

    // Method to get the recommendations for each anime the user has watched
    public static void getMalRecommendations(Anime inAnime) {
        try {
            // Adding delay for Jikan API (Rate limit: 3 per second - 60 per minute
            int delay = 1000;
            Thread.sleep(delay);

            List<RecommendedAnime> recommendedAnimelist = api.fetchRecommendedAnimes(inAnime.getMalId());

            HashMap<Integer, Integer> indexMultiplier = indexMultiplierMap();

            //TODO: hva hvis flere ting har like mange votes
            int i = 0;
            while (i < 10 && i < recommendedAnimelist.size()) {
                RecommendedAnime anime = recommendedAnimelist.get(i);

                // Calculating score for the recommendations
                // User score * recommendations placement, 8.2 * 7
                int multiplier = indexMultiplier.get(i);
                anime.setCombinedScore(inAnime.getUserScore() * multiplier);

                // Checking if the recommendation is already in the recommended list
                boolean found = false;
                for (RecommendedAnime a : malRecommendations) {
                    if (a.getTitle().equals(anime.getTitle())) {
                        a.setCombinedScore(a.getCombinedScore() + anime.getCombinedScore());
                        a.setVotes(a.getVotes() + anime.getVotes());
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    malRecommendations.add(anime);
                }

                i++;
            }
        } catch (Exception e) {
            logger.severe("Exception in MalRecommendations.getMalRecommendations()");
        }
    }

    // Removing animes from the recommended list that the user already watched
    public static void removeDuplicateAnimes() {
        List<RecommendedAnime> removedAnimes = new ArrayList<>();

        for(RecommendedAnime anime : malRecommendations) {
            for(Anime watchedAnime : watchedlist) {
                if(anime.getTitle().equals(watchedAnime.getJapTitle())) {
                    removedAnimes.add(anime);
                }
            }
        }

        malRecommendations.removeAll(removedAnimes);
    }

    public static HashMap<Integer, Integer> indexMultiplierMap() {
        HashMap<Integer, Integer> hashmap = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            hashmap.put(i, 10 - i);
        }

        return hashmap;
    }

}
