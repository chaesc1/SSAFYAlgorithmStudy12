package boj.빙산;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2573 {
    static int count = 0;
    static boolean flag = false;
    static int year = 0;
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());

            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        if(isMelted()){
            System.out.println(0);
            return;
        }

        while(true){
            visited = new boolean[N][M];
            count = 0;
            year++;

            for(int i=0; i<N; i++){
                for(int j=0; j<M; j++){
                    if(map[i][j] != 0 && !visited[i][j]){
                        bfs(i, j);
                        count++;
                    }
                }
            }

            if(isMelted() && count==1){
                flag = true;
                break;
            }

            if(count>1)break;
        }

        int result = (flag)?0:year-1;
        System.out.println(result);
    }

    static boolean isMelted(){
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(map[i][j] != 0){
                    return false;
                }
            }
        }

        return true;
    }

    static void bfs(int row, int col){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {row, col});
        visited[row][col] = true;

        while(!queue.isEmpty()){
            int[] now = queue.poll();
            int nowR = now[0];
            int nowC = now[1];
            int sea_count = 0;

            for(int i=0; i<4; i++){
                int nextR = nowR + dr[i];
                int nextC = nowC + dc[i];

                if(nextR<0 || nextC<0 || nextR>=N || nextC>=M)continue;
                if(visited[nextR][nextC])continue;
                if(map[nextR][nextC] == 0) {
                    sea_count++;
                    continue;
                }

                queue.add(new int[] {nextR, nextC});
                visited[nextR][nextC] = true;
            }

            sea_count = Math.min(sea_count, map[nowR][nowC]);
            map[nowR][nowC] -= sea_count;
        }

    }
}

