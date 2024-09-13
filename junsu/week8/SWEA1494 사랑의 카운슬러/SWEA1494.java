
//SWEA 1494 사랑의 카운슬러 D4
import java.io.*;
import java.util.*;

public class SWEA1494 {
	static int[][] worm;
	static int N;
	public static long answer;
	public static int[] number;
	public static void main(String[] args) throws IOException {
     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
     StringTokenizer st;
     StringBuilder sb = new StringBuilder();
     
     int T = Integer.parseInt(br.readLine());
     for(int tc = 1; tc <= T; tc++) {
     	N = Integer.parseInt(br.readLine());
     	answer = Long.MAX_VALUE;
     	worm = new int[N][2];
     	number = new int[N];
     	
     	for(int i = 0; i < N; i++) {
     		st = new StringTokenizer(br.readLine());
     		worm[i][0] = Integer.parseInt(st.nextToken());
     		worm[i][1] = Integer.parseInt(st.nextToken());
     	}
//     	System.out.println(Arrays.deepToString(worm));
     	combination(0,0);
     	sb.append("#").append(tc).append(" ").append(answer+"\n");
     }
     System.out.println(sb);
	}
	static void combination(int cnt, int start) {
		if(cnt == N/2) {
			long dx = 0, dy = 0;
			for(int i = 0; i < N; ++i) {
				if(number[i] > 0) {
					dx += worm[i][0];
					dy += worm[i][1];
				}
				else {
					dx -= worm[i][0];
					dy -= worm[i][1];
				}
			}
			long vectorSize = (dx * dx) + (dy * dy);
			
			answer = (answer > vectorSize) ? vectorSize : answer;
			return;
		}
		
		for(int i = start; i < N; ++i) {
			number[i] = 1;
			combination(cnt+1, i+1);
			number[i] = 0;
		}
	}
}




