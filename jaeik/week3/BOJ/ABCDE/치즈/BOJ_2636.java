package week3.BOJ.ABCDE.치즈;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class BOJ_2636 {
    static boolean isMelted;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    static int r, c;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new int[r][c];
        for(int i=0; i<r; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<c; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int hour = 0;
        int count = 0;
        while(true){
            for(int i=1; i<r-1; i++){
                for(int j=1; j<c-1; j++){
                    if(map[i][j] == 1){
                        checkIsEdge(i, j);
                    }
                }
            }

            hour++;

            if(isAllMelted())break;

            count = countCheese();
        }

        System.out.println(hour);
        System.out.print(count);

    }

    static void checkIsEdge(int row, int col){
        for(int i=0; i<4; i++){
            int nextRow = row + dr[i];
            int nextCol = col + dc[i];

            if(nextRow<1 || nextRow>=r-1 || nextCol<1 || nextCol>=c-1)continue;

            if(map[nextRow][nextCol] == 0){
                map[row][col] = 0;
                return;
            }
        }
    }

    static boolean isAllMelted(){
        for(int i=1; i<r-1; i++){
            for(int j=1; j<c-1; j++){
                if(map[i][j] == 1){
                    return false;
                }
            }
        }

        return true;
    }

    static int countCheese(){
        int count = 0;

        for(int i=1; i<r-1; i++){
            for(int j=1; j<c-1; j++){
                if(map[i][j] == 1){
                    count++;
                }
            }
        }

        return count;
    }
}
