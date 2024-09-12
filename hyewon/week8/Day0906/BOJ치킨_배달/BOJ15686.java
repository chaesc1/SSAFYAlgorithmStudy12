package BOJ.Day0906.BOJ치킨_배달;

import java.awt.*;
import java.io.*;
import java.util.*;

public class BOJ15686 {
    static int N, M, answer;
    static int[][] city;
    static boolean[] visited;
    static ArrayList<Point> house = new ArrayList<>();
    static ArrayList<Point> chicken = new ArrayList<>();
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        city = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                city[i][j] = Integer.parseInt(st.nextToken());
                if (city[i][j] == 1) house.add(new Point(i, j));
                else if (city[i][j] == 2) chicken.add(new Point(i, j));
            }
        }
        visited = new boolean[chicken.size()];
        answer = Integer.MAX_VALUE;
        dfs(0, 0);

        System.out.println(answer);
    }
    public static void dfs(int start, int depth) {
        if (depth == M) {
            int total = 0;
            for (int i = 0; i < house.size(); i++) {
                int min = Integer.MAX_VALUE;
                for (int j = 0; j < chicken.size(); j++) {
                    if (!visited[j]) continue;
                    int distance = Math.abs(house.get(i).x - chicken.get(j).x) + Math.abs(house.get(i).y - chicken.get(j).y);
                    min = Math.min(min, distance);
                }
                total += min;
            }
            answer = Math.min(answer, total);
        }
        else {
            for (int i = start; i < chicken.size(); i++) {
                visited[i] = true;
                dfs(i + 1, depth + 1);
                visited[i] = false;
            }
        }
    }
}
