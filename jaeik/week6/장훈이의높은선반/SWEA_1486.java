package week6.장훈이의높은선반;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1486 {
    static int min;
    static boolean[] selected;
    static int n;
    static int top;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int tc=0; tc<T; tc++){
            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            top = Integer.parseInt(st.nextToken());

            arr = new int[n];
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<n; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }

            selected = new boolean[n];
            min = Integer.MAX_VALUE;

            subset(0, 0);

            System.out.println("#"+(tc+1)+" "+(min-top));
        }
    }

    static void subset(int depth, int length){
        if(depth == n){
            if(length<top)return;

            min = Math.min(min, length);
            return;
        }

        subset(depth+1, length+arr[depth]);
        subset(depth+1, length);
    }
}