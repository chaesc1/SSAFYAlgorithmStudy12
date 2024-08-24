import java.util.*;
import java.io.*;

public class Main {
	static int n;
	static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
	static int[] parent;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		n = scanner.nextInt();
		parent = new int[n + 1];
		for (int i = 0; i <= n; i++) {
            tree.add(new ArrayList<>());
        }
		
		for(int i = 1; i < n; i++) {
			int start = scanner.nextInt();
			int end = scanner.nextInt();
			
			tree.get(start).add(end);
			tree.get(end).add(start);
		}
		
		Queue<Integer> q = new LinkedList<>();
		q.add(1);
		parent[1] = -1;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for(int i : tree.get(cur)) {
				if(parent[i] == 0) {
					parent[i] = cur;
					q.add(i);
				}
			}
			
		}
		
		for(int i = 2; i <= n; i++) {
			System.out.println(parent[i]);
		}
		
		
	}
}