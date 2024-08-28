import java.util.*;
import java.io.*;

public class Main {
	
	static int n;
	static Node node[];
	static String answer = "";
	
	static class Node{
		char self;
		char left;
		char right;
		public Node(char self, char left, char right) {
			super();
			this.self = self;
			this.left = left;
			this.right = right;
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		node = new Node[n];
		
		StringTokenizer st;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			node[i] = new Node(st.nextToken().charAt(0), st.nextToken().charAt(0), st.nextToken().charAt(0));
		}
		
		Arrays.sort(node, (a1, a2) -> a1.self-a2.self);
		
		//전위순회 (부-왼-오)
		preSearch(0);
		//중위순회 (왼-부-오)
		answer += '\n';
		midSearch(0);
		//후위순회 (왼-오-부)
		answer += '\n';
		postSearch(0);
		
		System.out.println(answer);
	}
	
	static void preSearch(int idx) {
		answer += node[idx].self;
		
		char left = node[idx].left;
		char right = node[idx].right;
		if(left != '.')
			preSearch(left - 'A');
		if(right != '.')
			preSearch(right - 'A');
	}
	static void midSearch(int idx) {
		
		char left = node[idx].left;
		char right = node[idx].right;
		if(left != '.')
			midSearch(left - 'A');
		answer += node[idx].self;
		if(right != '.')
			midSearch(right - 'A');
	}
static void postSearch(int idx) {
		
		char left = node[idx].left;
		char right = node[idx].right;
		if(left != '.')
			postSearch(left - 'A');
		if(right != '.')
			postSearch(right - 'A');
		answer += node[idx].self;
	}
}