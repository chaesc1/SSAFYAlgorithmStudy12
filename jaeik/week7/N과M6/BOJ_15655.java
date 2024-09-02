package week7.N과M6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15655 {
    static boolean[] visited;
    static int[] selected;
    static int n, m;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

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

        combination(0,0);
    }

    static void combination(int start, int depth){
        if(depth==m){
            for(int val : selected){
                System.out.print(val+" ");
            }
            System.out.println();

            return;
        }

        for(int i=start; i<n; i++){
            if(!visited[i]){
                visited[i] = true;
                selected[depth] = arr[i];
                combination(i+1, depth+1);
                visited[i] = false;
            }
        }
    }
}
