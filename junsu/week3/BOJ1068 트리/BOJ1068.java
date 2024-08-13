// ���� 1068�� Ʈ�� ���5
import java.io.*;
import java.util.*;

public class BOJ1068 {
	static ArrayList<Integer>[] tree;
	static boolean visited[];
	static int delete;
	static int ans;
	
	public static void main(String[] args) throws IOException {
	       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	       StringTokenizer st;
	       //��� ��
	       int n = Integer.parseInt(br.readLine());
	       //Ʈ�� ����
	       tree = new ArrayList[n+1];
	       visited = new boolean[n+1];
	       
	       for(int i = 0; i < n; i++) {
	    	   tree[i] = new ArrayList<>();
	       }
	       int root = -1;
	       st = new StringTokenizer(br.readLine());
	       for(int i = 0; i < n; i++) {
	    	   int p = Integer.parseInt(st.nextToken());
	    	   if(p == -1) {
	    		   // i��° ��尡 ��Ʈ ����̴�.
	    		   root = i;
	    	   }else {
	    		   // p�� i��° ����� �θ���
	    		   tree[i].add(p);
	    		   tree[p].add(i);
	    	   }
	       }
//	       for(int i = 0; i<n; i++)	{
//	    	   System.out.println(tree[i]);
//	       }
	       delete = Integer.parseInt(br.readLine());
	       //����� ��尡 ��Ʈ ����� ���
	       if(delete == root) {
	    	   System.out.println(0);
	    	   return;
	       }else {
	    	   dfs(root);
	       }
	       System.out.println(ans);
	}
	static void dfs(int v) {
		visited[v] = true;
		int nodes = 0;
		for(int cur : tree[v]) {
			//���� ��� Ž��
			if(cur != delete && !visited[cur]) {
				nodes++;
				dfs(cur);
			}
		}
		if(nodes == 0) {
			ans++;
//			System.out.println(ans);
		}
	}
	
}
