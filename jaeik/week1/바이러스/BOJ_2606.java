package boj.바이러스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2606 {
    static int count;
    static int n;
    static int m;
    static int[][] network;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        network = new int[n+1][n+1];
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            network[start][end] = 1;
            network[end][start] = 1;
        }

        bfs(1);

        System.out.println(count-1);
    }

    static void bfs(int start){
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n+1];
        queue.add(start);
        visited[start] = true;
        count = 0;

        while(!queue.isEmpty()){
            int cur = queue.poll();
            count++;

            for(int i = 0; i <= n; i++){
                int next = network[cur][i];

                if(visited[i])continue;
                if(network[cur][i] == 1){
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }
    }
}
