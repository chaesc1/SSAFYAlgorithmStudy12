package week7.블랙잭;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2798 {
    static int max;
    static final int BLACK = 3;
    static int[] cards;
    static int[] selected;
    static int n, m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        cards = new int[n];
        selected = new int[BLACK];

        for(int i=0; i<n; i++){
            cards[i] = Integer.parseInt(st.nextToken());
        }

        max = 0;

        combination(0, 0);

        System.out.println(max);
    }

    static void combination(int start, int depth){
        if(depth==BLACK){
            int sum = 0;
            for(int val : selected){
                sum += val;
            }

            if(sum<=m){
                max = Math.max(max, sum);
            }

            return;
        }

        for(int i=start; i<n; i++){
            selected[depth] = cards[i];
            combination(i+1, depth+1);
        }
    }
}
