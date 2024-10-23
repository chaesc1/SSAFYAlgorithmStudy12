//BOJ12015 가장 긴 증가하는 부분 수열2, 골드2
// 시리즈 1번 문제는 dp로 풀었고 시리즈 2인 이번 문제는 LIS를 이분탐색을 통해 풀어야한다.
import java.io.*;
import java.util.*;

public class BOJ12015 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i =  0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
	
		ArrayList<Integer> res = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			int num = A[i];
			if(res.isEmpty()) res.add(num);
			else {
				if(res.get(res.size()-1) < num) res.add(num);
				else {
					//이분 탐색 해야함
					int start = 0;
					int end = res.size()-1;
					while(start <= end) {
						int mid = (start+end) / 2;
						if(res.get(mid) < num) {
							start = mid + 1;
						}else {
							end = mid - 1;
						}
					}
					res.set(start, num);
					
				}
			}
		}

//		for(int cur : res) {
//			System.out.print(cur + " ");
//		}
	
		bw.write(res.size()+"\n");
		bw.flush();
		bw.close();
		br.close();
	}
}
