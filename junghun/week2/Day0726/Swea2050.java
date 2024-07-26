package Day0726;

import java.io.*;

public class Swea2050 {
    public static void main(String[] args) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        try (FileInputStream fis = new FileInputStream("./input.txt");
             BufferedReader br = new BufferedReader(new InputStreamReader(fis))) {
            String line;
            while ((line = br.readLine()) != null) {
                for (char c : line.toCharArray()) {
                    System.out.print((c - 'A' + 1) + " ");
                }
                System.out.println();
            }
        }
    }
}
