package week3.BOJ.ABCDE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_13023 {
    static boolean[] visited;
    static boolean flag;
    static int n;
    static int m;
    static ArrayList<Integer>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            graph[start].add(end);
            graph[end].add(start);
        }

        flag = false;
        visited = new boolean[n];
        for(int i=0; i<n; i++){
            if(!flag){
                dfs(i, 1);
            }
        }

        int answer = (flag)?1:0;

        System.out.println(answer);
    }

    static void dfs(int start, int depth){
        if(depth==5){
            flag = true;
            return;
        }

        visited[start] = true;

        for(int i=0; i<graph[start].size(); i++){
            int next = graph[start].get(i);
            if(visited[next])continue;
            dfs(next, depth+1);
        }

        visited[start] = false;
    }
}

/**
 * [Record]
 *  - 접근 방식 -
 *      그래프의 임의의 노드에서 dfs를 했을 때 깊이가 5이상인 경로가 존재하면 1을 아니면 0을 출력한다
 *
 *  - 오류 -
 *      1. 2차원 배열로 그래프를 구현하면 최대 크기가 2000*2000이기 때문에 시간 초과가 발생한다
 *      2. 63line과 같이 visited 배열을 다시 false로 돌려놓지 않으면 return 후에 다른 유망한 노드로의 재탐색이 불가능하다
 *
 *  - 결론 -
 *      1. 시간 복잡도 계산을 잘해야 한다
 *      2. 백트래킹 가능성이 있는지 잘 판단해야 한다
 */