import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        List<Anime> animeList = new ArrayList<>();
        boolean isActive = true;

        String print = "Copy your Anime List from anilist.co and paste it under";
        System.out.println(print);

        //TODO: Nå må man ha en ekstra tom linje når du limer inn
        while(s.hasNextLine()) {
            String animeName = s.nextLine();
            System.out.println(animeName);
            double animeRating = Double.parseDouble(s.nextLine());
            System.out.println(animeRating);
            int episodeCount = Integer.parseInt(s.nextLine());
            System.out.println(episodeCount);
            String animeFormat = s.nextLine();
            System.out.println(animeFormat);

            Anime anime = new Anime(animeName, animeRating);
            animeList.add(anime);
            System.out.println(anime);
        }

        /*
        while(isActive) {

        }
         */
    }
}
