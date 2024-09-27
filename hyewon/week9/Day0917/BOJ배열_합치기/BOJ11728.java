package BOJ.Day0917.BOJ배열_합치기;

import java.io.*;
import java.util.*;

public class BOJ11728 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int[] num = new int[A + B];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < A; i++) num[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = A; i < (A + B); i++) num[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(num);

        for (int i : num) sb.append(i + " ");

        System.out.println(sb);
    }
}
