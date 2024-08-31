package week7.디저트카페;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_2105 {
    static class Cafe{
        int row;
        int col;
        int dessert;
        int rowDir;
        int colDir;

        public Cafe(int row, int col, int dessert, int rowDir, int colDir){
            this.row = row;
            this.col = col;
            this.dessert = dessert;
            this.rowDir = rowDir;
            this.colDir = colDir;
        }

    }

    static int max_path;
    static int n;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int tc=0; tc<T; tc++){
            int n = Integer.parseInt(br.readLine());

            map = new int[n][n];
            for(int i=0; i<n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<n; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            max_path = 0;
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    int count = 1;

                    //왼쪽 아래에 카페가 존재 하면 dfs
                    if(i+1<n && j+1<n){
                        dfs(new Cafe(i+1, j+1, 1, 1, map[i+1][j+1]), i, j, count);
                    }
                }
            }

            System.out.println(max_path);
        }
    }

    static void dfs(Cafe now, int startRow, int startCol, int count){
        if(now.row==startRow && now.col==startCol) {
            max_path = Math.max(max_path, count);
            return;
        }

        //현재 카페와 같은 방향으로 이동한 곳에 카페가 있으면 dfs
        if(now.row+now.rowDir>=0 && now.col+now.colDir>=0 && now.row+now.rowDir<n && now.col+now.colDir<n){
            int nextRow = now.row+now.rowDir;
            int nextCol = now.col+now.colDir;

            dfs(new Cafe(nextRow, nextCol, map[nextRow][nextCol], now.rowDir, now.colDir), startRow, startCol, count+1);
        }

        //현재 카페에서 대각선 방향에 카페가 있으면 dfs
        int rowDir = now.rowDir;
        int colDir = now.colDir;
        if(rowDir==1)colDir*=-1;
        if(rowDir==-1)rowDir*=-1;
        if(now.row+rowDir>=0 && now.col+colDir>=0 && now.row+rowDir<n && now.col+colDir<n){
            int nextRow = now.row+rowDir;
            int nextCol = now.col+colDir;

            dfs(new Cafe(nextRow, nextCol, map[nextRow][nextCol], rowDir, colDir), startRow, startCol, count+1);
        }

    }
}
