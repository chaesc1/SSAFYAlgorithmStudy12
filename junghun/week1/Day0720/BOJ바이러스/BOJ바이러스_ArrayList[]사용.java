package BOJ.DFSBFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P2606_ArrayListADJ {
    static int N, M,count;
    static ArrayList<Integer>[] adjList;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        M = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine());

        adjList = new ArrayList[M + 1];
        for (int i = 1; i <= M; i++) {
            adjList[i] = new ArrayList<>();
        }
        visited = new boolean[M + 1];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            adjList[start].add(end);
            adjList[end].add(start);
        }

        int answer = dfs(1);
        System.out.println(answer-1); // 1번 노드를 제외하고 전염된 노드를 출력 그래서 -1.
    }

    private static int dfs(int start) {
        visited[start] = true;
        count = 1;
        for (int i = 0; i < adjList[start].size(); i++) {
            int next = adjList[start].get(i);

            if (!visited[next]) {
                count += dfs(next);
            }
        }
        return count;
    }
}
