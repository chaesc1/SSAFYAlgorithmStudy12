//BOJ1654 랜선 자르기, 실버2
// 이분 탐색 문제
import java.io.*;
import java.util.*;

public class BOJ1654 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[] arr = new int[K];
		
		for(int i = 0; i < K; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);
//		System.out.println(Arrays.toString(arr));
		
		long start = 1; //런타임 에러 (/ by zero) 발생해서 1로 초기화
		long end = arr[K-1];
		long ans = 0;
		
		while(start <= end) {
			long cnt = 0;
			long mid = (start+end) / 2;
			
			for(int i = 0; i < K; i++) {
				cnt += arr[i] / mid;
			}
			
			if(cnt < N) {
				end = mid - 1;
			}else {
				start = mid + 1;
			}
		}
		ans = end;
		
		bw.write(ans + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
}
