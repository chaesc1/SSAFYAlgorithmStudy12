package week7.준환이의양팔저울;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_3234 {
    static int count;
    static int N;
    static int[] weight;
    static int[] selected;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int tc=0; tc<T; tc++){
            N = Integer.parseInt(br.readLine());
            weight = new int[N];
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++){
                weight[i] = Integer.parseInt(st.nextToken());
            }

            selected = new int[N];
            visited=  new boolean[N];
            count = 0;

            permutation(0);

            System.out.println("#"+(tc+1)+" "+count);
        }
    }

    static void subset(int depth, int right, int left){
        if(right>left)return;

        if(depth==N){
            count++;
            return;
        }

        subset(depth+1, right+selected[depth], left);
        subset(depth+1, right, left+selected[depth]);
    }

    static void permutation(int depth){
        if(depth == N){
            subset(0, 0, 0);
            return;
        }

        for(int i=0; i<N; i++){
            if(!visited[i]){
                selected[depth] = weight[i];
                visited[i] = true;

                permutation(depth+1);

                visited[i] = false;
            }
        }
    }
}
