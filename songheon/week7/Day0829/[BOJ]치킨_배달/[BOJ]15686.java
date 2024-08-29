import java.io.*;
import java.util.*;

public class Main {

	static int n, m;
	static int map[][];
	static ArrayList<Pair> house;
	static ArrayList<Pair> chicken;
	static int cnt; // 치킨집 수
	static int output[]; // 선택된 치킨집
	static int answer;
	
	static class Pair{
		int x;
		int y;
		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		answer = 25000;
		map = new int[n][n];
		house = new ArrayList<Pair>();
		chicken = new ArrayList<Pair>();
		output = new int[m];
		
		// 도시 정보 입력
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] == 1) {
					// 집
					house.add(new Pair(i, j));
				}
				else if(map[i][j] == 2) {
					// 치킨 집
					chicken.add(new Pair(i, j));
					cnt++;
				}
			}
		}
		
		//치킨 집 고르기
		combi(0, 0);
		
		System.out.println(answer);
		
	}
	
	static void calc() {
		
		int total = 0;
		for(int i = 0; i < house.size(); i++) { // 집 
			Pair curh = house.get(i);
			int mind = 25000;
			for(int j = 0; j < m; j++) { // 치킨 집
				Pair curc = chicken.get(output[j]);
				int dist = Math.abs(curh.x - curc.x) + Math.abs(curh.y - curc.y);
				mind = Math.min(dist, mind);
			}
			total += mind;
		}
		
		answer = Math.min(answer, total);
		
	}
	
	static void combi(int order, int start) {
		if(order == m) {
			calc();
			return;
		}
		
		
		for(int i = start; i < cnt; i++) {
			output[order] = i;
			combi(order + 1, i + 1);
		}
	}
}