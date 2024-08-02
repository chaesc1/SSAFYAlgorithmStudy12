package DFS와BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1260 {
    static StringBuilder sb;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());

        int[][] map = new int[n+1][n+1];
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            map[start][end] = 1;
            map[end][start] = 1;
        }

        visited = new boolean[n+1];
        sb = new StringBuilder();
        dfs(map, v);

        sb.append("\n");

        visited = new boolean[n+1];
        bfs(map, v);

        System.out.println(sb.toString());
    }

    static void dfs(int[][] map, int start){
        int[] now = map[start];
        sb.append(start).append(" ");
        visited[start] = true;

        for(int i=1; i<now.length; i++){
            if(visited[i])continue;

            if(now[i] == 1){
                dfs(map, i);
            }
        }
    }

    static void bfs(int[][] map, int start){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        while(!queue.isEmpty()){
            int n = queue.poll();
            sb.append(n).append(" ");
            int[] now = map[n];

            for(int i=1; i<now.length; i++){
                if(visited[i])continue;

                if(now[i] == 1){
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
    }
}
