import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

    public class Bot implements Runnable {
        private static final int MAX_DEPTH = 3;
        private final Thread thread;
        private final String first_link;
        private ArrayList<String> visitedlinks = new ArrayList<String>();
        private final int ID;

        public Bot(String link, int num){
            System.out.println("Bot został utworzony");
            first_link = link;
            ID = num;

            thread = new Thread(this);
            thread.start();
        }

        @Override
        public void run() {
            search(1,first_link);

        }

        private void search(int level, String url) {
            if(level <= MAX_DEPTH){
                Document doc = request(url);

                if (doc != null) {
                    for (Element link : doc.select("a[href]")) {
                        String next_link = link.absUrl("href");
                        if(!visitedlinks.contains(next_link)) {
                            search(level++, next_link);
                        }
                    }
                }
            }
        }

        private Document request(String url) {
            try {
                Connection con = Jsoup.connect(url);
                Document doc = con.get();

                if (con.response().statusCode() == 200) {
                    System.out.println("\n**Bot ID:" + ID + " Odwiedzona strona " + url);

                    String title = doc.title();
                    System.out.println(title);
                    visitedlinks.add(url);

                    return doc;
                }
                return null;
            } catch (IOException e){
                return null;

            }
        }

        public Thread getThread() {
            return thread;
        }
    }
