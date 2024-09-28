//BOJ1789 수들의 합, 실버5
//이분 탐색 알고리즘의 기반이 되는 문제
import java.io.*;
import java.util.*;

public class BOJ1789 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		long N = Long.parseLong(br.readLine());
		long sum = 0;
		
		int cnt = 0;
		int i = 1;
		
		while(sum + i <= N) {
			sum += i;
			cnt++;
			i++;
		}
		
//		// 1을 입력했을 때는 예외처리 해줘야한다.
//		if(N == 1) {
//			cnt = 1;
//		}
		
		bw.write(cnt + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
}
