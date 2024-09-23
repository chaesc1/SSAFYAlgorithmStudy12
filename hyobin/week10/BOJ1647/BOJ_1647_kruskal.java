//크루스칼 알고리즘 이용
import java.io.*;
import java.util.*;
public class Main {
    static int n,m;
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
        //편의상 b를 a의 부모로 설정
        parent[b]= a;
        return true;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n= Integer.parseInt(st.nextToken());
        m= Integer.parseInt(st.nextToken());
        ArrayList<Edge> list = new ArrayList<>();
        for(int i=0; i<m; i++){
            st= new StringTokenizer(br.readLine());
            int a= Integer.parseInt(st.nextToken());
            int b= Integer.parseInt(st.nextToken());
            int c= Integer.parseInt(st.nextToken());
            list.add(new Edge(a,b,c));
        }
        //비용 오름차순으로 정렬
        Collections.sort(list);
        make();

        int cnt=0;
        int result=0;

        for(Edge edge: list){
            if(cnt==n-2) break;
            if(union(edge.from, edge.to)){
                result+= edge.cost;
                cnt++;
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
