package week8.색종이붙이기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17136 {
    static int[][] map;
    static int[] paper = {0, 5, 5, 5, 5, 5};
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new int[10][10];
        for(int i=0; i<10; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<10; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, 0);

        if(min == Integer.MAX_VALUE)min=-1;

        System.out.println(min);
    }

    static void dfs(int row, int col, int count){
        if(row==9 && col>9){
            min = Math.min(min, count);
            return;
        }

        if(min<=count)return;

        if(col>9){
            dfs(row+1, 0, count);
            return;
        }

        if(map[row][col]==1){
            for(int i=5; i>=1; i--){
                if(paper[i]>0 && canAttach(row, col, i)){
                    attach(row, col, i, 0);
                    paper[i]--;
                    dfs(row, col+1, count+1);
                    attach(row, col, i, 1);
                    paper[i]++;
                }
            }
        }
        else {
            dfs(row, col+1, count);
        }
    }

    static void attach(int row, int col, int size, int state){
        for(int i=row; i<row+size; i++){
            for(int j=col; j<col+size; j++){
                map[i][j] = state;
            }
        }
    }

    static boolean canAttach(int row, int col, int size){
        for(int i=row; i<row+size; i++){
            for(int j=col; j<col+size; j++){
                if(i<0 || j<0 || i>=10 || j>=10)return false;
                if(map[i][j]==0)return false;
            }
        }
        return true;
    }
}
