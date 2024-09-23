import java.util.*;
import java.io.*;
public class Main {
    static int n,m;
    static int[] parent;
    static void make(){
        parent = new int[n+1];
        for(int i=1; i<=n; i++){
            parent[i] = i;
        }
    }
    static int find(int x){
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
    static boolean union(int x, int y){
        x = find(x);
        y = find(y);
        if(x == y) return false;
        parent[y] = x;
        return true;
    }



    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        ArrayList<Edge> list = new ArrayList<>();
        //최대합
        Long max = 0L;
        //최소합
        Long sum = 0L;
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list.add(new Edge(a,b,c));
            max += c;
        }
        Collections.sort(list);
        make();
        int cnt = 0;
        for(Edge edge: list){
            if(cnt == n-1) break;
            if(union(edge.from, edge.to)){
                sum += edge.cost;
                cnt++;
            }
        }

        if(cnt != n-1){
            System.out.println(-1);
            return;
        }
        System.out.println(max - sum);

    }
    static class Edge implements Comparable<Edge>{
        int from, to, cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

}
