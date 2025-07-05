import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class InputLoader {

    private static final String INPUT_DIR = "input";

    // Load the input as a list of lines
    public static List<String> loadLines(int day) throws IOException {
        Path path = getInputPath(day);
        return Files.readAllLines(path);
    }

    // Load the input as a single string
    public static String loadAsString(int day) throws IOException {
        Path path = getInputPath(day);
        return Files.readString(path);
    }

    // Get the Path object for a given day's input
    private static Path getInputPath(int day) {
        String filename = String.format("input%d.txt", day);
        return Paths.get(INPUT_DIR, filename);
    }

    // Optional: check if input file exists
    public static boolean inputExists(int day) {
        return Files.exists(getInputPath(day));
    }
}
