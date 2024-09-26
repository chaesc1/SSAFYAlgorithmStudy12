//BOJ11279 최대 힙, 실버2
//우선순위 큐를 사용하는 문제
//new PriorityQueue<>() 에 Collections.reverseOrder() 를 사용
import java.io.*;
import java.util.*;

public class BOJ11279 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		//우선순위 조건을 내림차순으로 변경
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			if(num == 0) {
				if(!pq.isEmpty()) {
					bw.write(pq.poll()+"\n");
				}else {
					bw.write(0 + "\n");
				}
			}else {
				pq.add(num);
			}
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
