import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day1 {
    public static void main(String[] args) {

        int p1 = 0;
        int p2 = 0;
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        Map<Integer, Integer> l2Counts = new HashMap<>();

        try {
            int day = 1;
            List<String> lines = InputLoader.loadLines(day);
            for (String line : lines) {
                String[] vals = line.split("   ");
                l1.add(Integer.parseInt(vals[0]));
                l2.add(Integer.parseInt(vals[1]));   
                
                int occurrences = l2Counts.containsKey(Integer.parseInt(vals[1])) ? l2Counts.get(Integer.parseInt(vals[1])) : 0;
                l2Counts.put(Integer.parseInt(vals[1]), occurrences+1);
            }

            Collections.sort(l1);
            Collections.sort(l2);

            for (int i = 0; i < l1.size(); i++) {
                p1 += Math.abs(l1.get(i) - l2.get(i));
            }

            for (Integer val : l1) {
                if (l2Counts.containsKey(val)) {
                    p2 += val * l2Counts.get(val);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading input: " + e.getMessage());
        }

        System.out.println("P1: " + p1);
        System.out.println("P2: " + p2);
    }
}
