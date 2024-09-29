//BOJ2512 예산
// 이진 탐색은 정렬된 데이터 위에서만 유효한 동작
// 매개변수 탐색은 주어진 범위 내에서 원하는 값 또는 조건에 일치하는 값을 찾아내는 알고리즘이다. 
// 조건에 부합하는 값을 찾는 경우이므로 정렬이 필수조건으로 적용되지 않는다.
// 이 문제는 이진 탐색을 활용함
// 코드 길이는 짧지만 문제 풀이 원리를 이해하는데 어려웠다..
import java.io.*;
import java.util.*;

public class BOJ2512 {
	static int N, M;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr); //정렬 해줘야 이진 탐색 가능
		M = Integer.parseInt(br.readLine());
		
		int start = 0;
		int end = arr[N-1];
		int mid, sum;
		int cnt = 1;
		while(start <= end) {
//			System.out.println(cnt++ + " " + start + " " + end);
			sum = 0;
			mid = (start+end) / 2;
			for(int i = 0; i < N; i++) {
				sum += Math.min(mid, arr[i]);
			}
			if(sum > M) {
				end = mid - 1;
			}else {
				start = mid + 1;
			}
		}
		
		
		bw.write(end+"\n");
		bw.flush();
		bw.close();
		br.close();
	}
}
