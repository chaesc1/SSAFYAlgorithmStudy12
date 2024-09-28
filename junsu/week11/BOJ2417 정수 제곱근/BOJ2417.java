//BOJ2417 정수 제곱근, 실버4
//처음에는 이 문제와 이분탐색이 어떤 접점이 있는지 이해하기 어려웠음
// 1번 방법 Math.sqrt
// 2번 방법 이분 탐색 - 이걸로 품
import java.io.*;

public class BOJ2417 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		long num = Long.parseLong(br.readLine());

		long start = 0;
		long end = num;
		long ans = 0;
		
		while(start <= end) {
			long mid = (start+end) / 2;
			if(num <= Math.pow(mid, 2)) {
				ans = mid;
				end = mid-1;
			} else {
				start = mid+1;
			}
		}
		
		bw.write(ans+"\n");
		bw.flush();
		bw.close();
		br.close();
	}
}
