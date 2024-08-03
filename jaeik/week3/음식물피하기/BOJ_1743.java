package week3.음식물피하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1743 {
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    static int[][] map;
    static boolean[][] visited;
    static int n, m, k;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n+1][m+1];
        for(int i=0; i<k; i++){
            st = new StringTokenizer(br.readLine());

            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());

            map[row][col] = 1;
        }

        int result = 0;
        visited = new boolean[n+1][m+1];
        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++){
                if(map[i][j] == 1 && !visited[i][j]){
                    result = Math.max(result, bfs(i, j));
                }
            }
        }

        System.out.println(result);
    }

    static int bfs(int row, int col){
        Queue<int[]> queue = new ArrayDeque<>();
        int count = 0;
        queue.add(new int[] {row, col});
        visited[row][col] = true;

        while(!queue.isEmpty()){
            int[] now = queue.poll();
            int nowRow = now[0];
            int nowCol = now[1];
            count++;

            for(int i=0; i<4; i++){
                int nextRow = nowRow + dr[i];
                int nextCol = nowCol + dc[i];

                if(nextRow<1 || nextCol<1 || nextRow>n || nextCol>m)continue;
                if(visited[nextRow][nextCol])continue;

                if(map[nextRow][nextCol] == 1){
                    visited[nextRow][nextCol] = true;
                    queue.offer(new int[] {nextRow, nextCol});
                }
            }
        }

        return count;
    }
}
