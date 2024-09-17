//BOJ10775 ����, ���2
//ó���� �а� �׳� visited�迭�� �湮������� ��ŷ�� ���ϸ� �Ǵ°� �ƴѰ�..? ��� ������
//�׳� Ǯ��ôµ� ���ɴ� ������ �ð��ʰ� ����. (���Ͽ�-���ε带 �����ٸ� �� Ʋ�ȴ��� ������ ���̴�.)
//���Ͽ� ���ε�� ��� �����ؾ����� �𸣰���

//g�� ����⸦ g�� ����Ʈ�� ��ŷ�ϴ� ���� �ּ���. (�׸��� �������� �� �ּ�)
// ����, g�� ����⸦ g������Ʈ�� ��ŷ�� �� ���ٸ�,
// g-1�� ����Ʈ�� ����å���� ��ŷ��Ŵ.
// g-1���� �� �ȴٸ�, g-2��, ... 0������ Ž�� - 0�� ����Ű�°� ��ŷ�� �Ұ����� ����
import java.io.*;
import java.util.*;
public class BOJ10775 {
	static int[] gate;
	static int G, P;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		G = Integer.parseInt(br.readLine());
		P = Integer.parseInt(br.readLine());
		
		gate = new int[G+1];
		for(int i = 1; i <= G; i++) {
			gate[i] = i;
		}
		
		int cnt = 0;
		for(int i = 0; i < P; i++) {
			int g = Integer.parseInt(br.readLine());
			int temp = find(g);
			
			if(temp == 0) break; // 0�� ����Ű�°� ��ŷ�� �Ұ����� ����
			
			cnt++;  // ��ŷ�� ����� ��
			union(temp, temp-1); // g-1�� ����Ʈ�� ����å���� ��ŷ��Ŵ.
		}
		
//		System.out.println(Arrays.toString(gate));
		
//		System.out.println(cnt);
		bw.write(cnt+"\n");
		bw.flush();
		bw.close();
		br.close();
	}
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a != b) {      // g-1�� ����Ʈ�� ����å���� ��ŷ��Ŵ.
			gate[a] = b;  //����å ���(�ڱ� ���¿��� -1 �� ���)
		}
	}
	static int find(int x) {
		if(gate[x] == x) return x;
		
		//  ################�򰥸��� �κ� ����###################
		//  gate[x] = �� �߰������ν� ��ξ����� �̷�����ϴ�.
		//	Parent[0] = 1, Parent[1] = 2 ... Parent[5] = 5 �� ��Ȳ�� �����ϰ� Find(0)�� ������ ȣ���Ϸ��� �սô�
		//	gate[x] = �� ������, �ѹ� Find(0) ȣ��ÿ� 5���� ���ȣ���� �Ͼ�� ���������� �ֻ�������� 5�� �����Ұ̴ϴ�. �׸��� �ٽ� �ѹ� Find(0)�� ȣ��ÿ��� �Ȱ��� 5���� ���ȣ���� �Ͼ����.
		//	�㳪 gate[x] = �� ������, ó�� �ѹ� Find(0) ȣ��ÿ��� ���Ͱ��� 5���� ȣ���� �Ͼ�� �մϴٸ� 5���� ���ȣ���� ������
		//	Parent[0] = 5, Parent[1] =5 ... Parent[5] = 5 ���� �Ҵ�Ǿ ���� Find(n) (n�� 0~5������ ��) ȣ��ÿ� 5���� ���ȣ����� �ʿ���� �ܹ��� �ֻ�������� 5�� �����մϴ�.
		//	��ʹ� ���ϱ� ����鼭 �����ϴ� ���� ������� �׳� �ֻ����������� ��θ� �����ϴ� �뵵���� ������ �����ϸ� �˴ϴ�.
		//  return�� �� gate[x] = �̰� �ȳ־��ְ� �׳� find(gate[x])�� �ϸ� �ð��ʰ�����. 
		return gate[x] = find(gate[x]);  
	}
}
