//BOJ1927 �ּ� ��, �ǹ�2
// PriorityQueue�� ������ �� �ִ� ��������.
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
			
			// ����ؾ��ϴ� ���
			if(num == 0) {
				if(pq.isEmpty()) {
					bw.write(0+"\n");
				}
				else {
					int pqNum = pq.poll();
					bw.write(pqNum+"\n");
				}
			} // �����ؾ��ϴ� ���
			else {
				pq.offer(num);
			}
		}

		bw.flush(); //���⼭ ���Ƽ� �����
		bw.close();
		br.close();
	}
}
