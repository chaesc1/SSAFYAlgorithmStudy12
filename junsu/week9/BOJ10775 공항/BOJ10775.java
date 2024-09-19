//BOJ10775 공항, 골드2
//처음에 읽고 그냥 visited배열에 방문기록으로 도킹수 구하면 되는게 아닌가..? 라는 생각함
//그냥 풀어봤는데 테케는 맞지만 시간초과 떴다. (유니온-파인드를 몰랐다면 왜 틀렸는지 몰랐을 것이다.)
//유니온 파인드랑 어떻게 연결해야할지 모르겠음

//g번 비행기를 g번 게이트에 도킹하는 것이 최선임. (그리디 생각했을 때 최선)
// 만약, g번 비행기를 g번게이트에 도킹할 수 없다면,
// g-1번 게이트에 차선책으로 도킹시킴.
// g-1번도 안 된다면, g-2번, ... 0번까지 탐색 - 0번 가리키는건 도킹이 불가능한 상태
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
			
			if(temp == 0) break; // 0번 가리키는건 도킹이 불가능한 상태
			
			cnt++;  // 도킹한 비행기 수
			union(temp, temp-1); // g-1번 게이트에 차선책으로 도킹시킴.
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
		
		if(a != b) {      // g-1번 게이트에 차선책으로 도킹시킴.
			gate[a] = b;  //차선책 등록(자기 상태에서 -1 한 노드)
		}
	}
	static int find(int x) {
		if(gate[x] == x) return x;
		
		//  ################헷갈리는 부분 설명###################
		//  gate[x] = 를 추가함으로써 경로압축이 이루어집니다.
		//	Parent[0] = 1, Parent[1] = 2 ... Parent[5] = 5 인 상황을 가정하고 Find(0)을 여러번 호출하려고 합시다
		//	gate[x] = 이 없으면, 한번 Find(0) 호출시에 5번의 재귀호출이 일어나고 최종적으로 최상위노드인 5를 리턴할겁니다. 그리고 다시 한번 Find(0)을 호출시에도 똑같이 5번의 재귀호출이 일어나겠죠.
		//	허나 gate[x] = 이 있으면, 처음 한번 Find(0) 호출시에는 위와같이 5번의 호출이 일어나긴 합니다만 5번의 재귀호출이 끝나면
		//	Parent[0] = 5, Parent[1] =5 ... Parent[5] = 5 으로 할당되어서 다음 Find(n) (n은 0~5까지의 수) 호출시엔 5번의 재귀호출까진 필요없이 단번에 최상위노드인 5를 리턴합니다.
		//	재귀다 보니깐 적어가면서 추적하는 것이 어려워서 그냥 최상위노드까지의 경로를 압축하는 용도구나 까지만 이해하면 됩니다.
		//  return할 때 gate[x] = 이거 안넣어주고 그냥 find(gate[x])만 하면 시간초과난다. 
		return gate[x] = find(gate[x]);  
	}
}
