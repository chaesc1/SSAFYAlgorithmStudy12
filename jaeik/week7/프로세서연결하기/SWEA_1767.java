package week7.프로세서연결하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.JToolTip;

public class SWEA_1767 {
    static class Core{
        int row;
        int col;

        public Core(int row, int col){
            this.row = row;
            this.col = col;
        }
    }

    static int N, min, size;
    static int[][] map;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    static boolean[] selected;
    static Core[] cores;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for(int tc=0; tc<T; tc++){
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            selected = new boolean[12];
            cores = new Core[12];
            min = Integer.MAX_VALUE;
            size = 0;

            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if((i!=0 || j!=0) && map[i][j]==1) {
                        cores[size++] = new Core(i, j);
                    }
                }
            }

            for(int i=size; i>=0; i--){
                combination(0, 0, i);
                if(min<Integer.MAX_VALUE)break;
            }

            System.out.println("#"+(tc+1)+" "+min);
        }
    }

    static void dfs(int idx, int length){
        if(idx==size){
            min = Math.min(min, length);
            return;
        }

        if(!selected[idx]){
            dfs(idx+1, length);
            return;
        }

        for(int i=0; i<4; i++){
            Core core = cores[idx];
            int row = core.row;
            int col = core.col;
            int l = 0;
            boolean isConnected = false;

            //연결
            while(true){
                row += dr[i];
                col += dc[i];

                if(row<0 || col<0 || row>=N || col>=N){
                    isConnected = true;
                    break;
                }

                if(map[row][col]!=0)break;
                map[row][col] = 2;
                l++;
            }

            if(isConnected){
                dfs(idx+1, length+l);
            }

            //연결 해제
            while(true){
                row -= dr[i];
                col -= dc[i];

                if(row==core.row && col==core.col)break;

                map[row][col] = 0;
            }
        }
    }

    static void combination(int start, int count, int goal){
        if(count==goal){
            dfs(0,0);
            return;
        }

        for(int i=start; i<size; i++){
            selected[i] = true;
            combination(i+1, count+1, goal);
            selected[i] = false;
        }
    }
}
