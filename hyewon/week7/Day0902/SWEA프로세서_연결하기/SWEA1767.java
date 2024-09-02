package SWEA.Day0830.SWEA프로세서_연결하기;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class SWEA1767 {
    static int N, minWire, maxCore;
    static int[][] cell;
    static List<Point> coreList;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0 ,-1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            cell = new int[N][N];
            coreList = new ArrayList<>();

            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    cell[r][c] = Integer.parseInt(st.nextToken());
                    if (r < 1 || c < 1 || r >= N - 1 || c >= N - 1) continue;
                    if (cell[r][c] == 1) coreList.add(new Point(c, r)); // (열, 행)
                }
            }
            minWire = Integer.MAX_VALUE;
            maxCore = Integer.MIN_VALUE;

            connect(0, 0, 0); // index, coreCnt, wireCnt
            sb.append("#" + tc + " " + minWire + "\n");
        }
        System.out.println(sb);
    }

    public static void connect(int idx, int coreCnt, int wireCnt) {
        if(idx == coreList.size()) {
            if(maxCore < coreCnt) {
                maxCore = coreCnt;
                minWire = wireCnt;
            } else if(maxCore == coreCnt) minWire = Math.min(wireCnt, minWire);
            return;
        }
        int c = coreList.get(idx).x;
        int r = coreList.get(idx).y;

        for(int dir = 0; dir < 4; dir++) {
            int count = 0;
            int nc = c;
            int nr = r;

            while(true) {
                nc += dc[dir];
                nr += dr[dir];
                if(nr < 0 || nr >= N || nc < 0 || nc >= N)  break;

                if (cell[nr][nc] == 1) {
                    count = 0;
                    break;
                }
                count++;
            }
            int fill_c = c;
            int fill_r = r;

            for(int i=0; i<count; i++) {
                fill_c += dc[dir];
                fill_r += dr[dir];
                cell[fill_r][fill_c] = 1;
            }

            if(count==0)
                connect(idx+1, coreCnt, wireCnt);
            else {
                connect(idx+1, coreCnt+1, wireCnt+count);

                fill_c = c;
                fill_r = r;
                for(int i=0; i<count; i++) {
                    fill_c += dc[dir];
                    fill_r += dr[dir];
                    cell[fill_r][fill_c] = 0;
                }
            }
        }
    }
}
