import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int records[][];
	static int order[];
	static boolean visit[];
	static int answer;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		records = new int[n+1][10];
		order = new int[10];
		visit = new boolean[10];
		
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= 9; j++) {
				records[i][j] = sc.nextInt();
			}
		}
		
		permu(1);
		
		System.out.println(answer);
	}
	
	static void startGame() {
		int set = 1;
		int cur = 1;
		int scores = 0;
		
		while(set <= n) {
			int out = 0;
			boolean map[] = new boolean[4];
			
			while(out < 3) {
				int batter = order[cur];

				int recur = records[set][batter];
				
				if(recur == 0) out++;	
				else {
					map[0] = true;
					for(int i = 1; i <= recur; i++) {
						if(map[3] == true) scores++;
						for(int j = 3; j >= 1; j--) {
							map[j] = map[j-1]; 
						}
						map[0] = false;
					}
				}
				cur++;
				if(cur == 10) cur = 1;
			}
			
			set++;
		}
		answer = Math.max(answer, scores);
	}
	
	
	static void permu(int cur) {
		if(cur == 10) {
//			System.out.println(Arrays.toString(order));
			startGame();
			return;
		}
		
		if(cur == 4) {
			order[cur] = 1;
			permu(cur + 1);
		}
		else {
			for(int i = 2; i <= 9; i++) {
				if(visit[i]) continue;
				visit[i] = true;
				order[cur] = i;
				permu(cur + 1);
				visit[i] = false;
			}
		}
	}
}