//BOJ10815 숫자 카드, 실버5
//그냥 반복문인 arr.contains(num)를 사용하면 시간초과가 난다.
//이분 탐색을 사용(재귀로 구현)
import java.io.*;
import java.util.*;

public class BOJ10815 {
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			arr[i] = num;
		}
		
		Arrays.sort(arr);
		
//		for(int cur : arr) {
//			System.out.print(cur + " ");
//		}
//		System.out.println();
		
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			int num = Integer.parseInt(st.nextToken());
			// arr.contains(num)를 사용하면 시간초과가 난다.
			int start = 0;
			int end = arr.length-1; //여기서 -1 안 빼주면 index 에러 발생한다.
			
			int ans = BinarySearch(start, end, num);
			bw.write(ans + " ");
		}
		bw.flush();
		bw.close();
		br.close();
	}
	static int BinarySearch(int start, int end, int num) {
		if(start > end) return 0;
		int mid = (start + end) / 2; 
		
		if(arr[mid] == num) {
			return 1; 
		}else if(arr[mid] < num) {
			return BinarySearch(mid+1, end, num);
		}else { //arr[mid] > num
			return BinarySearch(start, mid-1, num);
		}
	}
}
