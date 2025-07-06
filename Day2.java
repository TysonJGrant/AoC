import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day2 {
    public static void main(String[] args) {

        int p1 = 0;
        int p2 = 0;

        List<List<Integer>> sequence = new ArrayList<>();

        try {
            int day = 2;
            List<String> lines = InputLoader.loadLines(day);
            for (String line : lines) {
                List<Integer> current = new ArrayList<>();
                String[] vals = line.split(" ");
                for (String val: vals) {
                    current.add(Integer.parseInt(val));
                }
                sequence.add(current);
            }
        } catch (IOException e) {
            System.err.println("Error reading input: " + e.getMessage());
        }

        for (List<Integer> current: sequence) {
            if (sequenceIncreasesOrDecreases(current, 1, 0) 
                    || sequenceIncreasesOrDecreases(current, -1, 0)) {
                p1++;
            }

            if (sequenceIncreasesOrDecreases(current, 1, 1) 
                    || sequenceIncreasesOrDecreases(current, -1, 1)) {
                p2++;
            }
        }

        System.out.println("P1: " + p1);
        System.out.println("P2: " + p2);
    }

    private static boolean sequenceIncreasesOrDecreases(List<Integer> sequence, int direction, int tolerance) {
        if (tolerance < 0) {
            return false;
        }
        for (int i = 1; i < sequence.size(); i++) {
            int diff = (sequence.get(i) - sequence.get(i-1)) * direction;
            if (diff < 1 || diff > 3) {
                return sequenceIncreasesOrDecreases(deepCopy(sequence, i), direction, tolerance-1) 
                    || sequenceIncreasesOrDecreases(deepCopy(sequence, i-1), direction, tolerance-1);
            }
        }
        return true;
    }

    private static List<Integer> deepCopy(List<Integer> sequence, int removeIndex) {
        List<Integer> current = new ArrayList<>();
        for (int i = 0; i < sequence.size(); i++) {
            if (i != removeIndex) {
                current.add(sequence.get(i));
            }
        }
        return current;
    }
}
