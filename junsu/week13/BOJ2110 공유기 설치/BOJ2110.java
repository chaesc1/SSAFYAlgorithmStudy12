//BOJ2110 공유기 설치, 골드4
//이분탐색 문제
//이분탐색으로 적용하기 너무 어려운 문제였다. 이해가 잘 안됨
import java.io.*;
import java.util.*;

public class BOJ2110 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int[] homeList = new int[N+1];
		
		for(int i = 1; i <= N; i++) {
			int n = Integer.parseInt(br.readLine());
			homeList[i] = n;
		}
		Arrays.sort(homeList);
		
		int left = 1;
		int right = homeList[N] - homeList[1];
		int d = 0;
		int ans = 0;
		
		while(left <= right) {
			int mid = (left + right) / 2; //최소 거리 설정
			
			int start = homeList[1]; //첫 공유기부터 시작
			int cnt = 1; //이미 시작 부분에 한 개 설치했다고 가정
			for(int i = 1; i <= N; i++) {
				d = homeList[i] - start;
				if(d >= mid) {
					cnt++;
					start = homeList[i];
				}

			}
//			System.out.println(cnt + " " + left + " " + right);
			if(cnt >= C) { // 설치된 공유기 수가 가지고 있는 공유기의 수보다 적으면
				ans = mid;
				left = mid + 1;
			}else {
				right = mid - 1;
			}
			
		}
		
		bw.write(ans+"\n");
		
		bw.flush();
		bw.close();
		br.close();
	}
}
