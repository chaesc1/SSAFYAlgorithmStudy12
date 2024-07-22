import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2178 {
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    static int[][] map;
    static int[][] path;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        path = new int[N][M];
        for(int i=0; i<N; i++){
            String line = br.readLine();

            for(int j=0; j<M; j++){
                map[i][j] = Character.getNumericValue(line.charAt(j));
            }
        }

        bfs();

        System.out.println(path[N-1][M-1]);
    }

    static void bfs(){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {0, 0});
        path[0][0] = 1;

        while(!queue.isEmpty()){
            int[] now = queue.poll();
            int row = now[0];
            int col = now[1];
            int distance = path[row][col];

            for(int i=0; i<4; i++){
                int nextRow = row + dr[i];
                int nextCol = col + dc[i];

                if(nextRow<0 || nextCol<0 || nextRow>=N || nextCol>=M)continue;
                if(map[nextRow][nextCol] == 0)continue;
                if(path[nextRow][nextCol] > 0)continue;

                queue.add(new int[] {nextRow, nextCol});
                path[nextRow][nextCol] = distance+1;
            }
        }
    }
}
