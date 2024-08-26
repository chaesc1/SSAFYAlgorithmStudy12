import java.io.*;
import java.util.*;

public class SWEA5653 {

	static final int dy[]= {0,0,1,-1};
	static final int dx[]= {1,-1,0,0};
	
	static final int DEAD=0, ACTIVE=1, INACTIVE=2;
	static int T,N,M,K;
	
	static List<Cell> cell;
	static PriorityQueue<Cell> pq;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		T=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			st=new StringTokenizer (br.readLine());
			N=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			K=Integer.parseInt(st.nextToken());
			
			cell=new ArrayList<>();
			pq=new PriorityQueue<>( (p1, p2) -> p2.power-p1.power );
			visited=new boolean[N+2*K][M+2*K];
			
			
			for (int i=0; i<N; i++) {
				st=new StringTokenizer (br.readLine());
				for (int j=0; j<M; j++) {
					int n=Integer.parseInt(st.nextToken());
				
					if (n!=0) {
						cell.add(new Cell(i+K,j+K,n,n));
						visited[i+K][j+K]=true;
					}

				}
			}
			simulation();
			System.out.println("#"+tc+" "+count());
				
		}
	}
	
	static void simulation () {
		for (int k=1; k<=K; k++) {
			
			// 직전에 INACTIVE -> ACTIVE 상태로 변경된 cell들 
			while (!pq.isEmpty()) {
				Cell c=pq.poll();
				int y=c.y;
				int x=c.x;
				
				if (!visited[y][x]) {
					visited[y][x]=true;
					cell.add(c);
				}
			}
			
			for (int i=0; i<cell.size(); i++) {
				if (cell.get(i).state==DEAD) continue;
				
				else if (cell.get(i).state==INACTIVE && cell.get(i).time==k) {
					cell.get(i).state=ACTIVE;				// X시간동안 활성상태
					cell.get(i).time=k+cell.get(i).power;	// 현재 시간 보다 X 시간이 지난 후에 죽는 상태로 만들어줄 것
					
					
					for (int d=0; d<4; d++) {
						// k+1+power 후에 변경상태가 될 것
						int ny=cell.get(i).y+dy[d];
						int nx=cell.get(i).x+dx[d];
						
						pq.add(new Cell(ny,nx, k+1+cell.get(i).power, cell.get(i).power));
					}
					
				} else if (cell.get(i).state==ACTIVE && cell.get(i).time==k) {
					cell.get(i).state=DEAD;
					cell.get(i).time=0;
					cell.get(i).power=0;
				}
				
			}
		}
	}
	
	static int count () {
		int cnt=0;
		for (int i=0; i<cell.size(); i++) {
			if (cell.get(i).state==ACTIVE || cell.get(i).state==INACTIVE)
				cnt++;
		}
		return cnt;
	}
	
	
	static class Cell {
		int y,x,time,state,power;
		
		Cell (int y, int x, int time, int power) {
			this.y=y;
			this.x=x;
			this.time=time;
			this.power=power;
			this.state=INACTIVE;
		}		
	}
}