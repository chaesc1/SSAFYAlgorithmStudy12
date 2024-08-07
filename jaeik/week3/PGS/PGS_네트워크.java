package week3.PGS;

import java.util.ArrayDeque;
import java.util.Queue;

public class PGS_네트워크 {
    static boolean[] visited;
    public int solution(int n, int[][] computers) {
        int answer = 0;

        visited = new boolean[n];
        for(int i=0; i<n; i++){
            if(!visited[i]){
                bfs(i, computers, n);
                answer++;
                System.out.println();
            }
        }

        return answer;
    }

    static void bfs(int start, int[][] computers, int n){
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start] = true;

        while(!queue.isEmpty()){
            int now = queue.poll();
            System.out.println(now);
            for(int i=0; i<n; i++){
                int next = computers[now][i];
                if(now==i)continue;
                if(visited[i])continue;
                if(next == 1){
                    queue.offer(i);
                    visited[i] = true;
                }
            }
        }
    }
}
