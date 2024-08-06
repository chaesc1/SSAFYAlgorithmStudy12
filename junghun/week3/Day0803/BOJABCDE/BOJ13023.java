package BOJ.DFSBFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P13023 {
    static int N, M;
    static ArrayList<Integer>[] list;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
            list[b].add(a);
        }

        for (int i = 0; i < N; i++) {
            visited = new boolean[N];
            dfs(i, 1);
        }
        System.out.println(0);
    }

    private static void dfs(int num, int depth) {
        if (depth == 5) {
            System.out.println(1);
            System.exit(0);
        }
        visited[num] = true;

        for (int i = 0; i < list[num].size(); i++) {
            int tmp = list[num].get(i);
            if (!visited[tmp]) {
                visited[tmp] = true;
                dfs(tmp, depth+1);
                visited[tmp] = false;
            }
        }
    }

}
