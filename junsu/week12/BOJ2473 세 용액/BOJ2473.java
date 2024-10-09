//BOJ2473 세 용액, 골드3
//이분탐색, 투 포인터
//이분 탐색으로 mid를 설정하여 mid - 1, mid + 1 로 변수를 설정하려 했으나
//이 문제는 투 포인터 방식으로 start++, end-- 로 변수를 하나씩 살펴봐야하는 문제였다.
//지금 실력으로는 스스로 풀기 힘든 문제였다.
import java.io.*;
import java.util.*;

public class BOJ2473 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		long[] arr = new long[N];
		int[] ans = new int[3];
		long result = Long.MAX_VALUE;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		Arrays.sort(arr);
//		System.out.println(Arrays.toString(arr));
		
		//index(i) for문 돌 때마다 start와 end를 검사해야하니 N-2해줘야함
		for(int i = 0; i < N-2; i++) {
			int start = i+1;
			int end = arr.length-1;
			while(start < end) { //실수한 부분 3 - 여기 start <= end하면 매개변수 중복된거 받아버린다.
//				int mid = (start+end) / 2;
				long sum = arr[i] + arr[start] + arr[end];
//				System.out.println(i+ "="+ " " +start + " "+ end);
//				System.out.println(result + "&" +sum);
				if(result > Math.abs(sum)) {
					result = Math.abs(sum); //실수한 부분 1 - 값 넣을때도 음수 이슈로 절대값 넣어줘야한다.
					ans[0] = i;
					ans[1] = start;
					ans[2] = end;
				}

				if(sum > 0) {
					end--;  //실수한 부분 2 - mid 활용하는 이분 탐색인줄 알았으나 투 포인터였음
				}else {
					start++; //실수한 부분 2 - mid 활용하는 이분 탐색인줄 알았으나 투 포인터였음
				}
			}
		}
		
		bw.write(arr[ans[0]] + " " + arr[ans[1]] + " " + arr[ans[2]]);
		bw.flush();
		bw.close();
		br.close();
	}
}
