package week8.야구;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17281 {
    static int[] lineup;
    static boolean[] visited;
    static int result = 0;
    static int score;
    static int out;
    static boolean[] base;
    static int batter;
    static int N;
    static int[][] inning;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        inning = new int[N][9];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<9; j++){
                inning[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[9];
        lineup = new int[9];
        visited[3] = true;
        lineup[3] = 0;

        permutation(0);

        System.out.println(result);
    }

    static void permutation(int num){
        if(num == 9){
            game();
            return;
        }

        for(int i=1; i<9; i++){
            if(!visited[i]){
                visited[i] = true;
                lineup[i] = num;
                permutation(num+1);
                visited[i] = false;
            }
        }
    }

    static void game(){
        score = 0;
        batter = 0;

        for(int i=0; i<N; i++){
            out = 0;
            base = new boolean[4];

            while(out!=3){
                for(int j=batter; j<9; j++){
                    int hit = inning[i][lineup[j]];
                    attack(hit);

                    //아웃 카운트가 3이면 다음 이닝으로
                    if(out==3) {
                        batter = (j+1)%9;
                        break;
                    }
                }
            }
        }

        result = Math.max(result, score);
    }

    static void attack(int hit){
        switch (hit){
            case 0:
                out++;
                break;
            case 1:
                updateBase(1);
                break;
            case 2:
                updateBase(2);
                break;
            case 3:
                updateBase(3);
                break;
            case 4:
                updateBase(4);
                break;
        }
    }

    static void updateBase(int hit){
        //주자들 위치 및 점수 업데이트
        for(int i=3; i>=1; i--){
            if(base[i]){
                if(i+hit>3){
                    base[i] = false;
                    score++;
                }else{
                    base[i] = false;
                    base[i+hit] = true;
                }
            }
        }
        //현재 타자 위치 및 점수 업데이트
        if(hit==4){
            score++;
        }else{
            base[hit] = true;
        }
    }
}
