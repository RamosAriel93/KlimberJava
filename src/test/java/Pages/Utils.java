package Pages;
import java.util.Random;

public class Utils {
    public String emailRandom() {
        Random random = new Random();
        int numberRandom = random.nextInt(1000);
        return "testing" + numberRandom + "@testing.com";
    }

    public int dniRandom() {
        int min = 15000000;
        int max = 85000000;
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}
