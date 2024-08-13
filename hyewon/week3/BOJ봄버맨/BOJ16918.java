import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int R, C, N;
	static char[][] bomb;
	static int[][] bombtime;
	
	static int time = 0;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        bomb = new char[R][C];
        bombtime = new int[R][C];

        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
            	bomb[i][j] = s.charAt(j);
                if(bomb[i][j]=='O'){
                    bombtime[i][j] = 3; // 폭탄이 터질 시간 (놓인 시간 + 3)
                }
            }
        }

        // N초가 흐를 때까지 반복
        while(time++ < N) {
            if(time % 2 == 0) install();
            else if(time % 2 == 1) boom();
        }

        // 출력
        for (int i = 0; i < R; i++) {
            System.out.println(bomb[i]);
        }

    }
    
    static void install() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (bomb[i][j] == '.') {
                    bomb[i][j] = 'O'; // 비어있는 모든 칸에 폭탄을 설치
                    bombtime[i][j] = time + 3; // 폭탄 설치 후 time 값 세팅
                }
            }
        }
    }
    
    static void boom() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (bombtime[i][j] == time) {
                    bomb[i][j] = '.';
                    for (int d = 0; d < 4; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];

                        if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue;

                        if(bomb[nx][ny]=='O' && bombtime[nx][ny] != time) { 
                            bomb[nx][ny] = '.';
                            bombtime[nx][ny] = 0;
                        }
                    }
                }
            }
        }
    }
}