import java.util.*;
import java.io.*;

public class Solution {
	//n : 초기 집합의 수
	//m : 연산 수
	//0 : 합집합
	//1 : 같은 집합인지 확인 -> 같은 집합이면 1, 아니면 0 (한줄에 출력)
	//makeset, find, union 연산으로 수행
	
	static int tc;
	static int n, m;
	static int parents[];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		
		tc= sc.nextInt();
		
		for(int t = 1; t <= tc; t++) {
			String answer = "";
			sb.append("#").append(t).append(" ");
			n = sc.nextInt();
			m = sc.nextInt();
			parents = new int[n + 1];
			
			make();
			
			for(int i = 0; i < m; i++) {
				int op = sc.nextInt();
				int e1 = sc.nextInt();
				int e2 = sc.nextInt();
				
				if(op == 0) {
					//union
					union(e1, e2);
				}
				else {
					//find
					if(find(e1) == find(e2))
						sb.append("1");
					else
						sb.append("0");
				}
				
				
			}

			sb.append('\n');
		}
		
		System.out.print(sb);
	}
	
	static void make() {
		for(int i = 1; i <= n; i++) {
			parents[i] = i;
		}
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		return true;
		
	}
	
	static int find(int a) {
		if(parents[a] == a)
			return a;
		else
			return parents[a] = find(parents[a]);
	}
}