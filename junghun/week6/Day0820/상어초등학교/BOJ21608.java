package BOJ.Implement;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ21608 {
    static class Student {
        int id;
        int[] friend;

        public Student(int id, int[] friend) {
            this.id = id;
            this.friend = friend;
        }
    }

    static int N;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Student[] students;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        students = new Student[N * N];
        map = new int[N + 1][N + 1];

        // 학생 번호와 친구들을 자료에 넣어
        for (int i = 0; i < N * N; i++) {
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int[] friend = new int[4];
            for (int j = 0; j < 4; j++) {
                friend[j] = Integer.parseInt(st.nextToken());
            }
            students[i] = new Student(id, friend);
        }

        // 자리 배치
        for (Student student : students) {
            assignSeat(student);
        }


        // 만족도 계산 및 출력
        int totalSatisfaction = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                for (int k = 0; k < students.length; k++) {
                    if (students[k].id == map[i][j]) {
                        totalSatisfaction += calc(k,i,j); //학생 인덱스 , x y 좌표를 파라미터로 넘겨서 만족도 계산.
                    }
                }
            }
        }
        System.out.println(totalSatisfaction);
    }

    private static int calc(int studentNum, int x, int y) {
        Student student = students[studentNum];
        int score = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 1 || nx > N || ny < 1 || ny > N) continue;

            for (int friendId : student.friend) {
                if (map[nx][ny] == friendId) {
                    score++;
                }
            }
        }

        if (score == 1) return 1;
        else if (score == 2) return 10;
        else if (score == 3) return 100;
        else if (score == 4) return 1000;
        else return 0;
    }

    private static void assignSeat(Student student) {
        int x = 0;
        int y = 0;
        int maxEmpty = -1;
        int maxFriend = -1;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (map[i][j] != 0) {
                    continue;
                }
                int emptyCnt = 0;
                int friendCnt = 0;

                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];

                    // 범위 밖이면
                    if (nx < 1 || nx > N || ny < 1 || ny > N)
                        continue;

                    // 친구가 앉아있으면 카운트를 올려
                    for (int f : student.friend) {
                        if (f == map[nx][ny]) friendCnt++;
                    }

                    if (map[nx][ny] == 0) {
                        emptyCnt++;
                    }
                }
                // 1,2,3 번 조건을 순차적으로 확인.
                // 3번은 행 -> 열로 탐색하기 때문에 내재되어있다.
                if (friendCnt > maxFriend || (friendCnt == maxFriend && emptyCnt > maxEmpty)) {
                    x = i;
                    y = j;
                    maxEmpty = emptyCnt;
                    maxFriend = friendCnt;
                }
            }
        }

        map[x][y] = student.id;
    }
}