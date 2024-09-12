import java.util.*;
import java.io.*;

public class Solution {
	//nxn 크기의 벌꿀 저장할 이차원 배열
	//채취할 벌꿀 선택 n*nC2 (이차원 배열 조합 이용)
		//선택된 값이 벌꿀 크기를 벗어나는 경우 패스 (열의 값 < n-m)
		//다음 벌꿀 선택할 때는 바로 옆 벌꿀 선택 안되도록 조정 (cur + m 부터 탐색)
	//채취한 벌꿀을 탐색하면서 C 고려해서  제곱 total에 합산
		//부분집합 이용해서 고르기
	//total이 최댓값인 경우 갱신
	
	static int tc;
	static int n, m, c;
	static int map[][];
	static Pair output[];
	static int answer;
	static int tmp;
	
	static class Pair{
		int x, y;

		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		tc = sc.nextInt();
		for(int t = 1; t <= tc; t++) {
			sb.append("#").append(t).append(" ");
			
			answer = 0;
			n = sc.nextInt();
			m = sc.nextInt();
			c = sc.nextInt();
			map = new int[n][n];
			output = new Pair[2];
			
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			
			combi(0, 0);
			
			sb.append(answer).append('\n');
		}
		System.out.println(sb);
	}
	
	static void calc(int x, int y, int order, int sum, int square) {
		if(order == m) {
			if(tmp < square)
				tmp = square;
			return;
		}
		
		int val = map[x][y];
		//포함 x
		calc(x, y + 1, order + 1, sum, square);
		//포함
		if(sum + val <= c)
			calc(x, y + 1, order + 1, sum + val, square + val * val);
	}
	
	static void combi(int start, int order) {
		if(order == 2) {
			
//			System.out.println("combi1 : " + output[0].x + " " + output[0].y);
//			System.out.println("combi2 : " + output[1].x + " " + output[1].y);
			int user1 = 0; int user2 = 0;
			tmp = 0;
			calc(output[0].x, output[0].y, 0, 0, 0);
			user1 = tmp;
			tmp = 0;
			calc(output[1].x, output[1].y, 0, 0, 0);
			user2 = tmp;
//			System.out.println(user1 + " " + user2);
			answer = Math.max(answer, user1 + user2);
			return;
		}

		for(int i = start; i < n * n; i++) {
			int r = i / n;
			int c = i % n;
			
			if(c > n-m) continue;
			output[order] = new Pair(r, c);
			combi(r * n + c + m, order + 1);
		}
		
	}
}