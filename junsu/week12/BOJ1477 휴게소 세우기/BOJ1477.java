//BOJ1477 휴게소 세우기, 골드4
//이분 탐색
import java.io.*;
import java.util.*;

public class BOJ1477 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int[] arr = new int[N+2];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		arr[N+1] = L;
		Arrays.sort(arr);
//		System.out.println(Arrays.toString(arr));
		
		int start = 1;
		int end = L-1;
		
		while(start <= end) {
			int mid = (start + end) / 2;
			int sum = 0;
			
			for(int i = 1; i < arr.length; i++) {
				sum += (arr[i] - arr[i-1]-1) / mid;
			}
			if(sum > M) {
				start = mid + 1;
			}else {
				end = mid - 1;
			}
		}
		
		
		bw.write(start+"\n");
		bw.flush();
		bw.close();
		br.close();
	}
}
