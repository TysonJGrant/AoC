package input;
import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.nio.file.*;
import java.util.concurrent.TimeUnit;

public class InputDownloader {

    private static final String INPUT_DIR = "input";
    private static final String USER_AGENT = "github.com/yourgithubusername"; // customize this politely

    public static void downloadAllInputs(int year, String sessionCookie) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        Files.createDirectories(Paths.get(INPUT_DIR));

        for (int day = 1; day <= 25; day++) {
            Path filePath = Paths.get(INPUT_DIR, String.format("input%d.txt", day));
            if (Files.exists(filePath)) {
                System.out.println("Day " + day + " input already exists. Skipping.");
                continue;
            }

            String url = String.format("https://adventofcode.com/%d/day/%d/input", year, day);
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Cookie", "session=" + sessionCookie)
                .header("User-Agent", USER_AGENT)
                .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                Files.writeString(filePath, response.body());
                System.out.println("Downloaded input for day " + day);
            } else {
                System.out.printf("Failed to download day %d (HTTP %d)\n", day, response.statusCode());
            }

            // Avoid hammering AoC's server — be respectful
            TimeUnit.MILLISECONDS.sleep(300);
        }
    }

    public static void main(String[] args) {
        String sessionCookie = "session cookie";
        int year = 2024;

        try {
            InputDownloader.downloadAllInputs(year, sessionCookie);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
