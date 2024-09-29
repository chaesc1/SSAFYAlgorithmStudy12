//BOJ1300 K번째 수, 골드1
//이 문제 또한 처음에 이분 탐색을 생각하지 못했다.
//N 크기 조건을 생각하지 않고 냅다 배열을 사용하였지만 역시 메모리 초과가 발생하였다.
//N은 10^5 로 전체 행렬의 크기는 최대 10^10 으로, 10,000,000,000 (100억)이 된다. 
//이 말은 행렬을 만들어 브루트 포스로 탐색하기에는 너무 많은 메모리와 시간을 잡아먹게 된다는 의미다.
// 참고 자료 : https://st-lab.tistory.com/281
 
import java.io.*;
import java.util.*;

public class BOJ1300 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		
		long start = 1;
		long end = K;  //여기서 틀림
		
		while(start <= end) {
			long mid = (start+end) / 2;
			long count = 0;
			
//			 임의의 x(mid)에 대해 i번 째 행을 나눔으로써 x보다 작거나 같은 원소의 개수
//			 누적 합을 구한다.
//			 이 때 각 행의 원소의 개수가 N(열 개수)를 초과하지 않는 선에서 합해주어야 한다. 
			for(int i = 1; i <= N; i++) {
				count += Math.min(mid/i, N);
			}
			
			if(count < K) {
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
