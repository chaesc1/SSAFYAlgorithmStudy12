import java.io.*;
import java.util.*;
public class Main {
    static int n,m;
    static String[] gender;
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

        gender = new String[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++){
            gender[i] = st.nextToken();
        }
        make();
        ArrayList<Edge> list = new ArrayList<>();
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list.add(new Edge(a,b,c));
        }
        Collections.sort(list);
        int cnt = 0;
        int result = 0;
        for(Edge edge: list) {
            if (cnt == n - 1) break;
            if(gender[edge.from].equals(gender[edge.to])) {continue; }
            if (union(edge.from, edge.to)) {
                result += edge.cost;
                cnt++;
            }
        }
        System.out.println(cnt == n-1 ? result : -1);


    }
    static class Edge implements Comparable<Edge>{
        int from, to, cost;
        public Edge(int from, int to, int cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
        @Override
        public int compareTo(Edge o){
            return Integer.compare(this.cost, o.cost);
        }
    }
}
