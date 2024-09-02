package BOJ.Day0901.BOJ모든_순열;

import java.io.*;
import java.util.Arrays;

public class BOJ10974 {
    static int N;
    static int[] number;
    static boolean[] isSelected;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        number = new int[N];
        isSelected = new boolean[N + 1];
        perm(0);
    }

    public static void perm(int cnt) {
        if (cnt == N) {
            for (int i : number) System.out.print(i + " ");
            System.out.println();
        }
        else {
            for (int i = 1; i <= N; i++) {
                if (isSelected[i]) continue;
                number[cnt] = i;
                isSelected[i] = true;
                perm(cnt + 1);
                isSelected[i] = false;
            }
        }
    }
}
