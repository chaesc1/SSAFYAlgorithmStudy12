//BOJ1927 최소 힙, 실버2
// PriorityQueue를 연습할 수 있는 문제였다.
import java.io.*;
import java.util.*;

public class BOJ1927 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			
			// 출력해야하는 경우
			if(num == 0) {
				if(pq.isEmpty()) {
					bw.write(0+"\n");
				}
				else {
					int pqNum = pq.poll();
					bw.write(pqNum+"\n");
				}
			} // 삽입해야하는 경우
			else {
				pq.offer(num);
			}
		}

		bw.flush(); //여기서 몰아서 출력함
		bw.close();
		br.close();
	}
}
