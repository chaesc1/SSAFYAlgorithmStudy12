//BOJ2805 나무 자르기, 실버2
//이분 탐색, 이분 탐색에 대한 감을 조금씩 찾는 중이다.
import java.io.*;
import java.util.*;

public class BOJ2805 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); //나무의 수
		long M = Integer.parseInt(st.nextToken()); //필요한 나무의 길이
		long H; //절단기 높이
		int[] tree = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			tree[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(tree);
//		System.out.println(Arrays.toString(tree));
		
		long start = 0;
		long end = tree[N-1];
		
		while(start <= end) {
//			System.out.println(start + " " + end);
			long treeTotal = 0;
			long mid = (start+end) / 2;
			for(int i = 0; i < N; i++) {
				if(tree[i] - mid > 0) {
					treeTotal += tree[i] - mid;
				}
			}
//			System.out.println("mid : " + mid);
//			System.out.println(M + " "+ treeTotal);
			
			//if(M < treeTotal) 로 했을 때 틀림
			if(M <= treeTotal) {  //아직 이분 탐색 if문 조건에 대해 완전히 이해하지 못했다.
				start = mid + 1; // treeTotal을 줄이려면 mid를 높여야함 그래서 start 지점을 mid+1로 설정
			}else {
				end = mid - 1;
			}
//			System.out.println();
		}
		H = end;  //여기도 아직 start 넣어야 하는지 end 넣어야 하는지 이해 제대로 못함
		
		bw.write(H + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
}
