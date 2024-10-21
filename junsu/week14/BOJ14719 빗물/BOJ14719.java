//BOJ14719 빗물, 골드5
//구현, 시뮬레이션 문제
//구현 문제라서 크게 피드백이 없다.
// 처음에 빈 공간과 이미 방문한 곳을 동시에 처리해서 에러가 발생했다.
// 빈 공간과 이미 방문한 곳을 따로 처리해줘야함
import java.io.*;
import java.util.*;

public class BOJ14719 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int[][] map = new int[H+1][W+1];
		boolean[][] visited = new boolean[H+1][W+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= W; i++) {
			int num = Integer.parseInt(st.nextToken());
			int cnt = 0;
			for(int j = H; j >= 1; j--) {
				if(cnt++ == num) break;
				map[j][i] = 1;
			}
		}
		
//		for(int i = 1; i <= H; i++) {
//			for(int j = 1; j <= W; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		int ans = 0;
		int N, M, endN, endM;
		Loop1 :
		for(int i = 1; i <= H; i++) {
			N = M = endN = endM = 0;
			for(int j = 1; j <= W; j++) {
				if(H == 1 && W == 1) {
					break Loop1;
				}
				// 빈 공간
				if(map[i][j] == 0) continue;
				
				// 이미 방문한 곳은 N과 M 리셋해줘야함
				if(visited[i][j]) {
					N = 0;
					M = 0;
					continue;
				}
				
				//블록 처음 발견했을때
				if(N == 0 && M == 0) {
					N = i;
					M = j;
				}else { //끝 블록 발견했을때
					endN = i;
					endM = j;
//					System.out.println("시작"+N+ " " + endN+ " "+M+" "+endM);
					
					//실수한 부분 : x, y 범위 설정을 잘못 생각함
					for(int x = i; x <= i; x++) {
						for(int y = M + 1; y < endM; y++) { 
							visited[x][y] = true;
							if(map[x][y] == 0) {
//								System.out.println(x + " " + y);
								ans++;
							}
						}
					}
					N = M = endN = endM = 0;
					j--;
				}
			}
		}
		bw.write(ans+"\n");
		bw.flush();
		bw.close();
		br.close();
	}
}
