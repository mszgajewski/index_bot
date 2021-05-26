import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<Bot> boty = new ArrayList<>();
        boty.add(new Bot("https://sport.pl", 1));
        boty.add(new Bot("https://www.onet.pl", 2));
        boty.add(new Bot("https://f1.com", 3));

        for (Bot w: boty) {
            try {
                w.getThread().join();
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
