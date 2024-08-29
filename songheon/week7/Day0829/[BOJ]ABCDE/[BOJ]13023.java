import java.io.*;
import java.util.*;

public class Main {

	static int n, m;
	static ArrayList<ArrayList<Integer>> list;
	static boolean visit[];
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		visit = new boolean[n];
		list = new ArrayList<ArrayList<Integer>>();
		
		// 인접 리스트 만들기
		for(int i = 0; i < n; i++) {
			list.add(new ArrayList<Integer>());
		}
		for(int i = 0; i < m; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			list.get(a).add(b);
			list.get(b).add(a);
		}
		
		// 친구가 연속으로 다섯 명이 연결되는지 확인
		for(int i = 0; i < n; i++) {
			Arrays.fill(visit, false);
			if(!dfs(i, 0)) continue;
			System.out.println(1);
			return;
		}
		System.out.println(0);
		return;
	}
	
	static boolean dfs(int num, int depth) {
		if(depth == 5) {
			return true;
		}
		
		for(int i = 0; i < list.get(num).size(); i++) {
			int cur = list.get(num).get(i);
			if(visit[cur]) continue;
			visit[cur] = true;
			if(dfs(cur, depth + 1))
				return true;
			visit[cur] = false;
		}
		
		return false;
	}
	
}