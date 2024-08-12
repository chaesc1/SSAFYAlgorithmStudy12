package week4.게리멘더링;

import java.io.*;

import java.util.*;



public class BOJ_17471 {

    static int N;

    static List<List<Integer>> graph;

    static int[] po;

    static boolean[] visited;

    static int min;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        po = new int[N+1];

        visited = new boolean[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=1; i<=N; i++) {

            po[i] = Integer.parseInt(st.nextToken());

        }

        graph = new ArrayList<>();

        for(int i=0; i<N; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());

            int around = Integer.parseInt(st.nextToken());

            for(int j=0; j<around; j++) {
                graph.get(i).add(Integer.parseInt(st.nextToken()));
            }

        }

        min = Integer.MAX_VALUE;

        dfs(1);

        int result = (min==Integer.MAX_VALUE)?-1:min;
        System.out.println(result);

    }

    static void dfs(int city) {
        if(city == N) {
            List<Integer> a = new ArrayList<>();
            List<Integer> b = new ArrayList<>();

            for(int i=1; i<=N; i++) {
                if(visited[i])a.add(i);
                else b.add(i);
            }

            if(a.isEmpty() || b.isEmpty())return;
            if(!check(a) || !check(b))return;

            min = Math.min(min, Math.abs(getPo(a)-getPo(b)));

            return;
        }

        visited[city] = true;
        dfs(city+1);

        visited[city] = false;
        dfs(city+1);
    }

    static boolean check(List<Integer> site) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N+1];
        queue.add(site.get(0));
        visited[site.get(0)] = true;

        int count = 0;
        while(!queue.isEmpty()) {
            int now = queue.poll();
            count++;

            for(int i=0; i<graph.get(now-1).size(); i++) {
                int next = graph.get(now-1).get(i);

                if(visited[next])continue;
                if(site.contains(next)){
                    queue.add(next);
                    visited[next] = true;
                }
            }
        }

        return site.size() == count;
    }

    static int getPo(List<Integer> site) {
        int sum = 0;

        for (int i = 0; i < site.size(); i++) {
            int city = site.get(i);
            sum += po[city];
        }

        return sum;
    }

}
