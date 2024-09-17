package week8.파이프옮기기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_17070 {
    static int count = 0;
    static int N;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        count = 0;
        dfs(1, 2, 0, 1);

        System.out.println(count);
    }

    static void dfs(int row, int col, int rowDir, int colDir){
        if(row==N && col==N){
            count++;
            return;
        }

        List<int[]> nextDir = getNextDir(rowDir, colDir);

        for(int i=0; i<nextDir.size(); i++){
            int nextRowDir = nextDir.get(i)[0];
            int nextColDir = nextDir.get(i)[1];
            int nextRow = row + nextRowDir;
            int nextCol = col + nextColDir;

            if(nextRow<1 || nextCol<1 || nextRow>N || nextCol>N)continue;
            if(isDiag(nextRowDir, nextColDir)){
                if(map[nextRow][nextCol]==1)continue;
                if(map[nextRow-1][nextCol]==1)continue;
                if(map[nextRow][nextCol-1]==1)continue;
            }
            else{
                if(map[nextRow][nextCol]==1)continue;
            }

            dfs(nextRow, nextCol, nextRowDir, nextColDir);
        }
    }

    static boolean isDiag(int rowDir, int colDir){
        return (rowDir!=0&&colDir!=0);
    }

    static List<int[]> getNextDir(int rowDir, int colDir){
        List<int[]> nextDir = new ArrayList<>();

        //대각선으로 움직이는 경우
        if(rowDir!=0&&colDir!=0){
            nextDir.add(new int[]{rowDir, colDir});
            nextDir.add(new int[]{rowDir, 0});
            nextDir.add(new int[]{0, colDir});
        }
        //row 또는 col이 움직이는 경우
        else {
            nextDir.add(new int[]{rowDir, colDir});
            nextDir.add(new int[]{1, 1});
        }

        return nextDir;
    }
}
