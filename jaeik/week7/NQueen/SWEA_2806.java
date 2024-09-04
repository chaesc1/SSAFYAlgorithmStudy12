package week7.NQueen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_2806 {
    static int count;
    static int N;
    static boolean[][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int tc=0; tc<T; tc++){
            N = Integer.parseInt(br.readLine());
            board = new boolean[N][N];

            count = 0;
            dfs(0);

            System.out.println("#"+(tc+1)+" "+count);
        }
    }

    static void dfs(int row){
        if(row==N){
            count++;
            return;
        }

        for(int i=0; i<N; i++){
            if(board[row][i])continue;
            if(!checkPossibility(row, i))continue;

            board[row][i] = true;
            dfs(row+1);
            board[row][i] = false;
        }
    }

    static boolean checkPossibility(int row, int col){
        //열 검사
        for(int i=0; i<=row; i++){
            if(board[i][col])return false;
        }

        //대각선 검사
        for(int i=0; i<row; i++){
            for(int j=0; j<N; j++){
                if(board[i][j]){
                    if(Math.abs(row-i)==Math.abs(col-j))return false;
                }
            }
        }

        return true;
    }
}
