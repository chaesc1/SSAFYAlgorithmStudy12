//BOJ11663 선분 위의 점, 실버3
import java.io.*;
import java.util.*;

public class BOJ11663 {
	static int N, M;
	static long[] point;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		point = new long[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int n = Integer.parseInt(st.nextToken());
			point[i] = n;
		}
		Arrays.sort(point);
		
		long ans = 0;
		for(int j = 0; j < M; j++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			ans = BinarySearch(x, y);
			bw.write(ans +"\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
	static long BinarySearch(int x, int y) {
		int start = 0;
		int end = point.length-1;
		
		while(start <= end) {
			int mid = (start + end) / 2;
			
			if(point[mid] < x) {
				start = mid + 1;
			}else {
				end = mid - 1;
			}
		}
		int startIndex = start;
		
		start = 0;
		end = point.length-1;
		
		while(start <= end) {
			int mid = (start + end) / 2;
			
			if(y < point[mid]) {
				end = mid - 1;
			}else { 
				start = mid + 1;
			}
		}
		int endIndex = end + 1;
		
//		System.out.println(startIndex + " " + endIndex);
		return endIndex - startIndex;
	}
}




