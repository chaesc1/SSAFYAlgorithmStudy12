package Day0816.SWEA무선충전;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Pos {
    int x, y;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(int dir) {
        if (dir == 1) {
            y--;
        } else if (dir == 2) {
            x++;
        } else if (dir == 3) {
            y++;
        } else if (dir == 4) {
            x--;
        }
    }
}

class BC {
    Pos pos;
    int c;
    int p;

    public BC(Pos pos, int c, int p) {
        this.pos = pos;
        this.c = c;
        this.p = p;
    }
}

public class SWEA5644 {
    static ArrayList<BC> bcs;
    static int[] dirA;
    static int[] dirB;
    static int t;
    static int m;
    static int a;
    static int result;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        t = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= t; tc++) {
            st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());

            bcs = new ArrayList<>();

            dirA = new int[m];
            dirB = new int[m];

            // A 이동 정보 입력
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                dirA[i] = Integer.parseInt(st.nextToken());
            }
            // B 이동 정보 입력
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                dirB[i] = Integer.parseInt(st.nextToken());
            }

            // bc 정보 입력
            for (int i = 0; i < a; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());
                bcs.add(new BC(new Pos(x, y), c, p));
            }
            result = 0;
            solve();

            sb.append("#").append(tc).append(" ").append(result).append("\n");

        }
        System.out.println(sb.toString());
    }

    private static void solve() {
        Pos userA = new Pos(1, 1);
        Pos userB = new Pos(10, 10);

        // 첫 좌표에서 충전이 가능한지
        charge(userA, userB);

        for (int i = 0; i < m; i++) {
            userA.move(dirA[i]);
            userB.move(dirB[i]);
            // 움직이고 -> 충전 가능한지
            charge(userA, userB);
        }
    }

    /**
     * BC의 충전 범위가 C일 때, BC와 거리가 C 이하이면 BC에 접속할 수 있다. 이때, 두 지점 A(XA, YA), B(XB, YB) 사이의 거리는 다음과 같이 구할 수 있다.
     * <p>
     * D = |XA – XB| + |YA – YB| 겹칠때는 이제 충전량비교해서 큰쪽 선택해야 최대값 얻을 수 있음 만약 한 BC에 두 명의 사용자가 접속한 경우, 접속한 사용자의 수만큼 충전 양을 균등하게
     * 분배한다.
     *
     * @param userA
     * @param userB
     */
    private static void charge(Pos userA, Pos userB) {
        // 이동하면서 충전 할 수 있는 BC의 목록을 각각 구해야해
        ArrayList<Integer> aList = new ArrayList<>();
        ArrayList<Integer> bList = new ArrayList<>();

        for (int i = 0; i < a; i++) {
            if (bcs.get(i).c >= (Math.abs(bcs.get(i).pos.x - userA.x) + Math.abs(bcs.get(i).pos.y - userA.y))) {
                aList.add(i);
            }

            if (bcs.get(i).c >= (Math.abs(bcs.get(i).pos.x - userB.x) + Math.abs(bcs.get(i).pos.y - userB.y))) {
                bList.add(i);
            }
        }
        int max = 0;
        int sum = 0;
        // A B 둘다 BC 에 접근 가능한 경우
        if (!aList.isEmpty() && !bList.isEmpty()) {
            for (int bcA : aList) {
                for (int bcB : bList) {
                    sum = 0;
                    if (bcA == bcB) { // 같은 BC면
                        sum += bcs.get(bcA).p;
                    } else {
                        sum += bcs.get(bcA).p;
                        sum += bcs.get(bcB).p;
                    }
                    max = Math.max(max, sum);
                }
            }
        } else if (!aList.isEmpty()) {
            for (int bcA : aList) {
                if (max < bcs.get(bcA).p) {
                    max = bcs.get(bcA).p;
                }
            }
        } else if (!bList.isEmpty()) {
            for (int bcB : bList) {
                if (max < bcs.get(bcB).p) {
                    max = bcs.get(bcB).p;
                }
            }
        }

        result += max;
    }
}
