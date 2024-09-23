import java.io.*;
import java.util.*;
public class Main {
    static int n;
    static int[] parent;
    static void make(){
        parent = new int[n+1];
        for(int i=1; i<=n; i++){
            parent[i]= i;
        }
    }
    static int find(int a){
        if(parent[a] == a) return a;
        return parent[a]= find(parent[a]);
    }
    static boolean union(int a, int b){
        a= find(a);
        b= find(b);
        if(a==b) return false;
        parent[b]= a;
        return true;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        ArrayList<Edge> list = new ArrayList<>();
        for(int i=1; i<=n; i++){
            StringTokenizer st= new StringTokenizer(br.readLine());
            for(int j=1; j<=n; j++){
                int cost= Integer.parseInt(st.nextToken());
                if(cost!=0){
                    list.add(new Edge(i,j,cost));
                }
            }
        }
        Collections.sort(list);
        Long result=0L;
        int cnt = 0;
        make();
        for(Edge edge: list){
            if (cnt == n-1) break;
            if(union(edge.from, edge.to)){
                result+= edge.cost;
            }
        }
        System.out.println(result);
        
    }
    static class Edge implements Comparable<Edge>{
        int from, to, cost;
        public Edge(int from, int to, int cost){
            this.from= from;
            this.to= to;
            this.cost= cost;
        }
        @Override
        public int compareTo(Edge o){
            return Integer.compare(this.cost, o.cost);
        }
    }
}
