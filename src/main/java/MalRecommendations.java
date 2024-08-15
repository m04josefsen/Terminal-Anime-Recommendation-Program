import java.util.*;

public class MalRecommendations {
    private static List<RecommendedAnime> malRecommendations;
    private static APIConnection api;

    public static List<RecommendedAnime> main(List<Anime> inputWatchedlist) {
        api = new APIConnection();
        malRecommendations = new ArrayList<>();
        List<Anime> watchedlist = inputWatchedlist;

        int count = 1;
        for(Anime anime : watchedlist) {
            getMalRecommendations(anime);
            System.out.println(count + " / " + watchedlist.size() + " animes analyzed");
            count++;
        }

        malRecommendations.sort(Comparator.comparingDouble(RecommendedAnime::getCombinedScore).reversed());

        List<RecommendedAnime> removedAnimes = new ArrayList<>();

        for(RecommendedAnime anime : malRecommendations) {
            for(Anime watchedAnime : watchedlist) {
                if(anime.getTitle().equals(watchedAnime.getJapTitle())) {
                    removedAnimes.add(anime);
                }
            }
        }

        malRecommendations.removeAll(removedAnimes);

        for(RecommendedAnime anime : malRecommendations) {
            System.out.println(anime);
            System.out.println();
        }

        //TODO: burde kanskje limite til x size()
        return malRecommendations;
    }

    public static void getMalRecommendations(Anime inAnime) {
        try {
            int delay = 1000;
            Thread.sleep(delay);

            api = new APIConnection();

            List<RecommendedAnime> recommendedAnimelist = api.fetchRecommendedAnimes(inAnime.getMalId());

            HashMap<Integer, Integer> indexMultiplier = indexMultiplierMap();

            //TODO: hva hvis flere ting har like mange votes
            int i = 0;
            while (i < 10 && i < recommendedAnimelist.size()) {
                RecommendedAnime anime = recommendedAnimelist.get(i);
                int multiplier = indexMultiplier.get(i);
                anime.setCombinedScore(inAnime.getUserScore() * multiplier);

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
            System.out.println("LOG");
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
