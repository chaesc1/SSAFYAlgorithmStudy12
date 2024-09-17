package week8.등산로조정;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_1949 {
    static int MAX_PATH;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    static int[][] path;
    static int N, K;
    static int[][] map;
    static List<int[]> top;
    static int MAX_HEIGHT;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int tc=0; tc<T; tc++){
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            MAX_HEIGHT = 0;
            map = new int[N][N];
            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                    MAX_HEIGHT = Math.max(MAX_HEIGHT, map[i][j]);
                }
            }

            top = new ArrayList<>();
            for(int i=0; i<N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == MAX_HEIGHT) {
                        top.add(new int[]{i, j});
                    }
                }
            }

            MAX_PATH = 0;
            for(int i=0; i<top.size(); i++){
                int row = top.get(i)[0];
                int col = top.get(i)[1];

                path = new int[N][N];
                path[row][col] = 1;
                dfs(false, row, col);
            }
            System.out.println("#"+(tc+1)+" "+MAX_PATH);
        }
    }

    static void dfs(boolean isDrilled, int row, int col){
        MAX_PATH = Math.max(MAX_PATH, path[row][col]);

        for(int i=0; i<4; i++){
            int nextRow = row + dr[i];
            int nextCol = col + dc[i];

            if(nextRow<0 || nextCol<0 || nextRow>=N || nextCol>=N)continue;
            if(path[nextRow][nextCol]>0)continue;

            if(map[row][col]>map[nextRow][nextCol] && map[nextRow][nextCol]!=0){
                path[nextRow][nextCol] = path[row][col]+1;
                dfs(isDrilled, nextRow, nextCol);
                path[nextRow][nextCol] = 0;
            }
            else if(map[row][col]<=map[nextRow][nextCol]) {
                if(!isDrilled && map[nextRow][nextCol]-K<map[row][col]){
                    int temp = map[nextRow][nextCol];
                    path[nextRow][nextCol] = path[row][col]+1;
                    map[nextRow][nextCol] = map[row][col]-1;

                    dfs(true, nextRow, nextCol);

                    path[nextRow][nextCol] = 0;
                    map[nextRow][nextCol] = temp;
                }
            }
        }
    }
}

/**
 * 78 line에 깎이기 이전의 높이를 저장해주는 temp를 선언해 사용해야한다
 * 탐색이 끝난 후 이전 값으로 되돌리려면 이전 값을 저장해줘야 한다
 */