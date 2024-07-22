package BOJ.DFSBFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P2644 {
    static int result = -1;
    static ArrayList<Integer>[] relation;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        relation = new ArrayList[N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            relation[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());

            relation[parent].add(child);
            relation[child].add(parent);
        }

        dfs(x,y,0);
        System.out.println(result);
    }

    private static void dfs(int start,int end, int depth) {
        if (start == end) {
            result = depth;
            return;
        }

        visited[start] = true;
        for (int i = 0; i < relation[start].size(); i++) {
            int next = relation[start].get(i);

            if (!visited[next]) {
                dfs(next, end, depth + 1);
            }
        }
    }
}
