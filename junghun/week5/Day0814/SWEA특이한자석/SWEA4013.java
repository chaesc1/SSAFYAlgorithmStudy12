package Day0814.algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * - 1 번 자석에서 빨간색 화살표 위치에 있는 날의 자성이 N 극이면 0 점, S 극이면 1 점을 획득한다.
 * - 2 번 자석에서 빨간색 화살표 위치에 있는 날의 자성이 N 극이면 0 점, S 극이면 2 점을 획득한다.
 * - 3 번 자석에서 빨간색 화살표 위치에 있는 날의 자성이 N 극이면 0 점, S 극이면 4 점을 획득한다.
 * - 4 번 자석에서 빨간색 화살표 위치에 있는 날의 자성이 N 극이면 0 점, S 극이면 8 점을 획득한다.
 */

public class SWEA4013 {
    static int K;
    static ArrayList<Integer>[] gear;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int testcase = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= testcase; tc++) {
            K = Integer.parseInt(br.readLine());
            gear = new ArrayList[5]; // 1 ~ 4 기어
            for (int i = 1; i < 5; i++) {
                gear[i] = new ArrayList<>();
                st = new StringTokenizer(br.readLine());
                while (st.hasMoreTokens()) {
                    gear[i].add(Integer.parseInt(st.nextToken()));
                }
            }

            for (int k = 0; k < K; k++) {
                st = new StringTokenizer(br.readLine());
                int num = Integer.parseInt(st.nextToken());
                //회전방향은 1 일 경우 시계방향이며, -1 일 경우 반시계방향이다.
                int dir = Integer.parseInt(st.nextToken());
                // 각 기어별로 회전 정보를 저장
                int[] turnInfo = new int[5];
                turnInfo[num] = dir;

                turnRight(num, dir, turnInfo);
                turnLeft(num, dir, turnInfo);

                for (int i = 1; i < turnInfo.length; i++) {
                    int turnDir = turnInfo[i];

                    if (turnDir == -1) { // 반시계방향
                        // 맨 앞 빼서 맨 뒤에 넣기
                        gear[i].add(gear[i].remove(0));
                    } else if (turnDir == 1) {
                        // 시계방향은 맨 뒤 빼서 맨 앞에 넣기
                        gear[i].add(0, gear[i].remove(gear[i].size() - 1));
                    }
                }
            }

            int result = 0;
            for (int i = 1; i < gear.length; i++) {
                // N -> 0, S -> 1
                result += (int) (gear[i].get(0) * Math.pow(2, i - 1));
            }

            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }

        System.out.println(sb);
    }

    private static void turnLeft(int num, int dir, int[] turnInfo) {
        if (num == 1) {
            return;
        }
        // 닿아있는 톱니의 극성이 다르면 회전
        if(gear[num].get(6) != gear[num-1].get(2)) {
            turnInfo[num-1] = dir * -1; // 방향 반전
            turnLeft(num-1, dir * -1, turnInfo);
        }
    }

    private static void turnRight(int num, int dir, int[] turnInfo) {
        if (num == 4) {
            return;
        }
        // 닿아있는 톱니의 극성이 다르면 회전
        if(gear[num].get(2) != gear[num+1].get(6)) {
            turnInfo[num+1] = dir * -1; // 방향 반전
            turnRight(num+1, dir * -1, turnInfo);
        }
    }
}
