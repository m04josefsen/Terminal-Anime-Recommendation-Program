import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static APIConnection api;
    private static List<Anime> watchedlist;

    public static void main(String[] args) {
        api = new APIConnection();

        Scanner s = new Scanner(System.in);
        List<InputAnime> inputAnimelist = new ArrayList<>();

        String print = "Copy your Anime List from anilist.co and paste it under";
        System.out.println(print);

        boolean isActive = true;

        while(isActive) {
            String animeTitle = s.nextLine();

            if(animeTitle.isEmpty()) {
                isActive = false;
                break;
            }
            if (!s.hasNextLine()) break;
            double animeRating = Double.parseDouble(s.nextLine());

            if (!s.hasNextLine()) break;
            int episodeCount = Integer.parseInt(s.nextLine());

            if (!s.hasNextLine()) break;
            String animeFormat = s.nextLine();

            InputAnime anime = new InputAnime(animeTitle, animeRating);
            inputAnimelist.add(anime);
        }
        System.out.println("Detection finished");

        updateUsersAnimelist(inputAnimelist);
        isActive = true;

        while(isActive) {
            System.out.println("Animelist scanned propertly" +
                    "\n-Press 1 to use the My Anime List recommendation system" +
                    "\n-Press 2 to use a customized recommendation system for underrated animes" +
                    "\n-Press 3 to end the program");

            String line = s.nextLine();

            if(line.equals("1")) {
                MalRecommendations.main(watchedlist);
            } else if(line.equals("2")) {
                UnderratedRecommendations.main(inputAnimelist);
            } else if(line.equals("3")) {
                isActive = false;
                System.out.println("Thank you for using the program c:");
            } else {
                System.out.println("Invalid input, please type 1, 2 or 3");
            }
        }
    }

    // Method to update the users animes from InputAnime object to Anime object
    public static void updateUsersAnimelist(List<InputAnime> InputAnimelist) {
        watchedlist = new ArrayList<>();

        long time = System.currentTimeMillis();
        int count = 1;
        for(InputAnime anime : InputAnimelist) {
            Anime newAnime = api.searchAnime(anime.getTitle());
            if(newAnime != null) {
                newAnime.setUserScore(anime.getRating());
                watchedlist.add(newAnime);

                System.out.println(count + " / " + InputAnimelist.size() + " detected");
                count++;
            }
        }
        time = (System.currentTimeMillis() - time) / 1000;
        System.out.println("Time taken: " + time + " seconds");
        System.out.println(divider());
    }

    public static String divider() {
        return "----------";
    }
}
