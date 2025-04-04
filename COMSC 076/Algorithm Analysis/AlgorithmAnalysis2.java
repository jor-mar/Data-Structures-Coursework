import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class AlgorithmAnalysis2 {
    public static void main(String[] args) {
        int searches = 10000;

        
        ArrayList<Integer> one = new ArrayList<>();
        ArrayList<Integer> three = new ArrayList<>();
        ArrayList<Integer> ten = new ArrayList<>();

        Random rand = new Random();
        for (int i = 0; i < 100; i++) {
            one.add(rand.nextInt());
        }
        for (int i = 0; i < 300; i++) {
            three.add(rand.nextInt());
        }
        for (int i = 0; i < 10000; i++) {
            ten.add(rand.nextInt());
        }
        System.gc();

        long[] times1 = new long[searches];
        long[] times3 = new long[searches];
        long[] times10 = new long[searches];

        long start;
        long stop;

        for (int i = 0; i < searches; i++) {
            System.gc();
            int numSearch = rand.nextInt();

            System.gc();
            start = System.nanoTime();
            one.indexOf(numSearch);
            stop = System.nanoTime();
            times1[i] = (stop - start);

            System.gc();
            start = System.nanoTime();
            three.indexOf(numSearch);
            stop = System.nanoTime();
            times3[i] = (stop-start);

            System.gc();
            start = System.nanoTime();
            ten.indexOf(numSearch);
            stop = System.nanoTime();
            times10[i] = (stop-start);
        }

        String filePath = "timesOne";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (long time : times1) {
                writer.write("" + time);
                writer.newLine();
            }
            System.out.println("ArrayList saved to " + filePath);
        }
        catch (IOException e) {
            System.err.println("Error saving ArrayList to file: " + e.getMessage());
        }

        filePath = "timesThree";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (long time : times3) {
                writer.write("" + time);
                writer.newLine();
            }
            System.out.println("ArrayList saved to " + filePath);
        }
        catch (IOException e) {
            System.err.println("Error saving ArrayList to file: " + e.getMessage());
        }

        filePath = "timesTen";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (long time : times10) {
                writer.write("" + time);
                writer.newLine();
            }
            System.out.println("ArrayList saved to " + filePath);
        }
        catch (IOException e) {
            System.err.println("Error saving ArrayList to file: " + e.getMessage());
        }
    }
}
