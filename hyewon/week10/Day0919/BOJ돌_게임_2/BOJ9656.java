package BOJ.Day0919.BOJ돌_게임_2;

import java.util.*;
import java.io.*;

public class BOJ9656 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        if (N % 2 == 0) System.out.println("SK");
        else System.out.println("CY");
    }
}
