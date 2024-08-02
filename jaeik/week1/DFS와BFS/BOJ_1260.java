import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_1260 {
    static StringBuilder sb;
    static boolean[] visited;
    static List<List<Integer>> map;
    static int N, M, V;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        map = new ArrayList<>();
        for(int i=0; i<=N; i++){
            map.add(new ArrayList<>());
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map.get(a).add(b);
            map.get(b).add(a);
        }

        for(int i=0; i<map.size(); i++){
            Collections.sort(map.get(i));
        }

        visited = new boolean[N+1];
        sb = new StringBuilder();

        dfs(V);
        System.out.println(sb+"\n"+bfs(V));
    }

    static void dfs(int start){
        visited[start] = true;
        sb.append(start).append(" ");

        for(int i=0; i<map.get(start).size(); i++){
            int next = map.get(start).get(i);

            if(!visited[next]){
                dfs(next);
            }
        }
    }

    static String bfs(int start){
        StringBuilder sb = new StringBuilder();
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N+1];
        queue.add(start);
        visited[start] = true;
        sb.append(start).append(" ");

        while(!queue.isEmpty()){
            int now = queue.poll();

            for(int i=0; i<map.get(now).size(); i++){
                int next = map.get(now).get(i);

                if(!visited[next]){
                    visited[next] = true;
                    queue.add(next);
                    sb.append(next).append(" ");
                }
            }
        }

        return sb.toString();
    }
}

//스택 dfs
//리스트 정렬