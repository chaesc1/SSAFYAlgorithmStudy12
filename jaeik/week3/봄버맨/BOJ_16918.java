package week3.봄버맨;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Dot{
    boolean state;
    char mark;

    Dot(boolean state, char mark){
        this.state = state;
        this.mark = mark;
    }
}

public class BOJ_16918 {
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    static boolean[][] visited;
    static Dot[][] map;
    static int r, c, n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new Dot[r][c];


        if(n%2==0){
            for(int i=0; i<r; i++){
                for(int j=0; j<c; j++){
                    System.out.print("O");
                }
                System.out.println();
            }

            return;
        }

        for(int i=0; i<r; i++){
            String line = br.readLine();

            for(int j=0; j<line.length(); j++){
                char m = line.charAt(j);
                map[i][j] = new Dot(false, m);
            }
        }

        game(n);

        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                System.out.print(map[i][j].mark);
            }
            System.out.println();
        }
    }

    static void game(int n){
        for(int i=0; i<n/2; i++){
            updateDotState();
            installBomb();
            explode();
            updateExploded();
        }
    }

    static void installBomb(){
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                map[i][j].mark = 'O';
            }
        }
    }

    static void updateDotState(){
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(map[i][j].mark == 'O'){
                    map[i][j].state = true;
                }
            }
        }
    }

    static void explode(){
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(map[i][j].state){

                    map[i][j].state = false;
                    map[i][j].mark = '.';

                    for(int k=0; k<4; k++){
                        int nextRow = i + dr[k];
                        int nextCol = j + dc[k];

                        if(nextRow<0 || nextRow>=r || nextCol<0 || nextCol>=c)continue;

                        map[nextRow][nextCol].mark = '.';
                    }
                }
            }
        }
    }

    static void updateExploded(){
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(map[i][j].mark == '.'){
                    map[i][j].state = false;
                }
            }
        }
    }
}
