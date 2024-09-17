package week7.N과M7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15656 {
    static boolean[] visited;
    static int[] selected;
    static int n, m;
    static int[] arr;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        visited = new boolean[n];
        selected = new int[m];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        combination(0);

        System.out.println(sb);
    }

    static void combination(int depth){
        if(depth==m){
            for(int val : selected){
               sb.append(val).append(" ");
            }
            sb.append("\n");

            return;
        }

        for(int i=0; i<n; i++){
                selected[depth] = arr[i];
                combination(depth+1);
        }
    }
}
