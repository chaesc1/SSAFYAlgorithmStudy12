package BOJ.Day0919.BOJ돌_게임;

import java.util.*;
import java.io.*;

public class BOJ9655 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        if (N % 2 == 0) System.out.println("CY");
        else System.out.println("SK");
    }
}
