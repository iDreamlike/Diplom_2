package data;

import java.util.Arrays;
import java.util.List;

public class OrderDataGenerator {
    public static final String BUN_ID = "61c0c5a71d1f82001bdaaa6d";
    public static final String MEAT_ID = "61c0c5a71d1f82001bdaaa6f";
    public static final String SAUCE_ID = "61c0c5a71d1f82001bdaaa72";

    public static List<String> getIngredients() {
        return Arrays.asList(BUN_ID, MEAT_ID, SAUCE_ID);
    }

    public static List<String> getNoIngredients() {
        return List.of();
    }

    public static List<String> getIncorrectIngredients() {
        return Arrays.asList("incorrect-Hash-111", "incorrect-Hash-222", "incorrect-Hash-333");
    }
}
