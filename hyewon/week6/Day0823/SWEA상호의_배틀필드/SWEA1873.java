package BOJ.DAY0823.SWEA상호의_배틀필드;

import java.io.*;
import java.util.*;

public class SWEA1873 {
    static class Car {
        int x;
        int y;
        int direction_x;
        int direction_y;
        public Car(int x, int y, int direction_x, int direction_y) {
            super();
            this.x = x;
            this.y = y;
            this.direction_x = direction_x;
            this.direction_y = direction_y;
        }
    }

    static String[][] map;
    static Car car;
    static int H, W;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            map = new String[H][W];

            for(int i = 0; i < H; i++) {
                map[i] = br.readLine().split(""); // 맵 생성
                for(int j = 0; j < W; j++) {
                    if(map[i][j].equals("^")) car = new Car(i, j, -1, 0);
                    else if(map[i][j].equals("v")) car = new Car(i, j, 1, 0);
                    else if(map[i][j].equals("<")) car = new Car(i, j, 0, -1);
                    else if(map[i][j].equals(">")) car = new Car(i, j, 0, 1);
                }
            }

            int N = Integer.parseInt(br.readLine()); // 명령 개수
            String[] order = br.readLine().split(""); // 명령 입력

            for(String s : order) {
                if(s.equals("S")) { // Shoot
                    bomb(car.direction_x, car.direction_y);
                }
                else if(s.equals("D")) mapChange(1, 0, "v"); // down
                else if(s.equals("L")) mapChange(0, -1, "<"); // left
                else if(s.equals("R")) mapChange(0, 1, ">"); // right
                else if(s.equals("U")) mapChange(-1, 0, "^"); // up
            }

            System.out.print("#" + tc + " ");
            for(int i = 0; i < H; i++) {
                for(int j = 0; j < W; j++) {
                    System.out.print(map[i][j]);
                }
                System.out.println();
            }
        }
    }

    public static void mapChange(int dx, int dy, String d) {
        map[car.x][car.y] = d;
        int move_x = car.x + dx;
        int move_y = car.y + dy;
        if(move_x >= 0 && move_y >= 0 && move_x < H && move_y < W && map[move_x][move_y].equals(".")) {
            map[car.x][car.y] = ".";
            map[move_x][move_y] = d;
            car.x = move_x;
            car.y = move_y;
        }
        car.direction_x = dx;
        car.direction_y = dy;
    }

    public static void bomb(int dx, int dy) {
        int nx = car.x, ny = car.y;
        while(true) {
            nx += dx;
            ny += dy;
            if(nx < 0 || ny < 0 || nx >= H || ny >= W) break;
            if(map[nx][ny].equals("*")) {
                map[nx][ny] = "."; // 벽돌일 경우 평지로 변
                break;
            }
            else if(map[nx][ny].equals("#")) break;
        }
    }
}

