package week3.PGS;

import java.util.LinkedList;
import java.util.Queue;

public class PGS_게임맵최단거리 {
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    public int solution(int[][] maps) {
        int rSize = maps.length;
        int cSize = maps[0].length;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[rSize][cSize];
        queue.add(new int[] {0, 0});
        visited[0][0] = true;

        int count = 0;
        int min = Integer.MAX_VALUE;
        boolean flag = false;
        while(!queue.isEmpty()){
            int[] now = queue.poll();
            int nowR = now[0];
            int nowC = now[1];

            count++;

            if(nowR==rSize-1 && nowC==cSize-1){
                flag = true;
                break;
            }

            for(int i=0; i<4; i++){
                int nextRow = nowR + dr[i];
                int nextCol = nowC + dc[i];

                if(nextRow<0 || nextCol<0 || nextRow>=rSize || nextCol>=cSize)continue;
                if(maps[nextRow][nextCol]==0)continue;

                if(maps[nextRow][nextCol] == 1){
                    maps[nextRow][nextCol] = maps[nowR][nowC]+1;
                    queue.add(new int[] {nextRow, nextCol});
                    //visited[nextRow][nextCol] = true;
                }
            }
        }

        int answer = (flag)?maps[rSize-1][cSize-1]:-1;

        return answer;
    }
}
