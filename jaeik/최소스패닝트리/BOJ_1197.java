package 최소스패닝트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1197 {
    static class Edge implements Comparable<Edge> {
        int start;
        int end;
        int weight;

        public Edge(int start, int end, int weight){
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight-o.weight;
        }
    }

    static int v, e;
    static int[] parent;
    static int[] rank;
    static PriorityQueue<Edge> edges;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        parent = new int[v+1];
        rank = new int[v+1];
        edges = new PriorityQueue<>();

        for(int i=1; i<=v; i++){
            parent[i] = i;
        }

        for(int i=0; i<e; i++){
             st = new StringTokenizer(br.readLine());
             int start = Integer.parseInt(st.nextToken());
             int end = Integer.parseInt(st.nextToken());
             int weight = Integer.parseInt(st.nextToken());

            edges.add(new Edge(start, end, weight));
        }

        int sum = 0;
        while(!edges.isEmpty()){
            Edge edge = edges.poll();

            if(find(edge.start)==find(edge.end))continue;

            union(edge.start, edge.end);
            sum += edge.weight;
        }

        System.out.println(sum);
    }

    static int find(int x){
        if(parent[x]==x)return x;
        return parent[x]=find(parent[x]);
    }

    static void union(int x, int y){
        x = find(x);
        y = find(y);

        if(x==y)return;

        if(rank[x]<rank[y]){
            parent[x] = y;
        }
        else{
            parent[y] = x;

            if(rank[x]==rank[y]){
                rank[x]++;
            }
        }
    }
}
