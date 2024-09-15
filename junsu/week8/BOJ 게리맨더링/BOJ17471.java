//BOJ 17471 �Ը��Ǵ��� ���3
// 3�ð� �ɷȴ�.. 
//������ 
//- ArrayList�� Ȱ���� 2���� �迭�� ���� ����� �̼��ϴ�.
//- get() �Լ� ����Ҷ� �ڲ� �迭 �� ���� �򰥷��� index ���� �̽�
//- 2�� ���ű� area�� �α��� �־��ִ� �Ÿ� ��ġ �ε��� �־��ֱ�� ����  @@@@@@�� �κ��� �߸� �����߾��� �α����� �������� �����ϸ� �ȵƴµ�..!
//���� ���鶧 return �� �ֱ�.. ��Դ´� �ڲ�
//1~N���� ������ 2���� ���ű��� �����ֱ� (�κ�����)
//������ ���ű� ������ �������� ���� ����Ǿ����� Ȯ�� (�׷��� Ž�� - BFS)
//���� ����Ǿ� ������ �α��� ���ϱ�
import java.io.*;
import java.util.*;

public class BOJ17471 {
	static int N;
	static int diffCnt;
	static int[] population;
	static ArrayList<Integer>[] graph;
	static boolean[] isSelected;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	    StringTokenizer st;
	    
	    N = Integer.parseInt(br.readLine());
	    diffCnt = Integer.MAX_VALUE;
	    population = new int[N+1];
	    isSelected = new boolean[N+1];
	    
	    //�׷��� �ʱ�ȭ
	    graph = new ArrayList[N+1];
	    for(int i = 1; i <= N; i++) {
	    	graph[i] = new ArrayList<>();
	    }


	    //�α��� ���
	    st = new StringTokenizer(br.readLine());
	    for(int i = 1; i <= N; i++) {
	    	population[i] = Integer.parseInt(st.nextToken());
	    }
//	    System.out.println(Arrays.toString(population));
	    
	    //�� �������� ������ ���� ����
	    for(int i = 1; i <= N; i++) {
	    	st = new StringTokenizer(br.readLine());
	    	int vertex = Integer.parseInt(st.nextToken());
	    	for(int j = 1; j <= vertex; j++) {
	    		graph[i].add(Integer.parseInt(st.nextToken()));
	    	}
	    }
//	    System.out.println(Arrays.toString(graph));
	    
	    generateSubset(1);
	    
	    bw.write((diffCnt == Integer.MAX_VALUE ? -1 : diffCnt)+"\n");
	    bw.flush();
	    bw.close();
	    br.close();
	}
	
	//�κ� ���� ����� �Լ�
	static void generateSubset(int depth) {
		if(depth == N+1) {
			ArrayList<Integer> areaA = new ArrayList<>();
			ArrayList<Integer> areaB = new ArrayList<>();
			
			for(int i = 1; i <= N; i++) {
				if(isSelected[i]) {
					areaA.add(i); //�α��� �־��ִ� �ſ��� ��ġ �ε��� �־��ֱ�� ����
				} else {
					areaB.add(i); //�α��� �־��ִ� �ſ��� ��ġ �ε��� �־��ֱ�� ����
				}
			}
			
			if(areaA.size() == 0 || areaB.size() == 0)
				return;
			
			if(check(areaA) && check(areaB)) {
				calc(areaA, areaB);
			}
			
			return;
		}
		
		// true�� A������ �ְڴٴ� ��
		isSelected[depth] = true;
		generateSubset(depth+1);
		// false�� B������ �ְڴٴ� ��
		isSelected[depth] = false;
		generateSubset(depth+1);
		
	}
	
	static boolean check(ArrayList<Integer> area) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
        visited[area.get(0)] = true;  // 1~N ������ �迭�� ��Ƽ� ������ �ͼ��ؼ� area.get(1)�ؼ� index ���� ����..
        q.add(area.get(0));
		
		int count = 1;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			 
			//���ű��� �ش��ϰ�, ���� �̹湮
			for(int next : graph[cur]) {
				// area���� idx�� ����ְ� graph[cur] �� ��� ���� idx�� contains�� �˻��Ѵ�.
				if(area.contains(next) && !visited[next]) {
					visited[next] = true;
					q.add(next); //�̰� �ִ°� ������;;;
					count++;
				}
			
			}

		}
		return count == area.size();
	}
	
	//�� ������ ���̸� �˻��ϴ� �Լ�
	static void calc(ArrayList<Integer> areaA, ArrayList<Integer> areaB) {
		int A = 0;
		int B = 0;
		
		// ArrayList�� N�� ��ŭ ���µ� N�� ��ŭ ���ƺ����ؼ� Ʋ��...
//		for(int i = 1; i <= N; i++) {
		// area�� ���� idx�ϱ� ���� �α����� idx�� Ȱ���ؼ� ���ϸ� �ȴ�.
		for(int i : areaA) {
			A += population[i];
		}
		
		for(int i : areaB) {
			B += population[i];
		}
		
		diffCnt = Math.min(diffCnt, Math.abs(A-B));
	}
}








