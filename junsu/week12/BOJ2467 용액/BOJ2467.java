//BOJ2467 용액, 골드5
// 이분 탐색 문제, 배열 양끝에서 서로 비교하면서 탐색
import java.io.*;
import java.util.*;

public class BOJ2467 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		long[] arr = new long[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
//		Arrays.sort(arr);
//		System.out.println(Arrays.toString(arr));
		
		int start = 0; // 시작과 끝을 이번에는 index로 설정
		int end = N-1;
		long abs = Long.MAX_VALUE;
		int a = 0;
		int b = 0;
		
		//양끝에서 계산 시작해서 좁혀가야함
		while(start < end) {
			long num = arr[start] + arr[end];
//			System.out.println(arr[start] + " "+arr[end]);
//			System.out.println(num);
			
			//핵심 - 절대값 기준으로 비교해야한다. 
			if(abs > Math.abs(num)) {
				abs = Math.abs(num);
				a = start;
				b = end;
			}
			
			//핵심 - 비교할 때는 음수, 양수 구분해야 해서 절대값 해주면 안된다.
			if(0 < num) {
				end--;
			}
			else {
				start++;
			}
		}
//		System.out.println(abs);
		
		bw.write(arr[a]+" "+arr[b]+"\n");
		bw.flush();
		bw.close();
		br.close();
	}
}
