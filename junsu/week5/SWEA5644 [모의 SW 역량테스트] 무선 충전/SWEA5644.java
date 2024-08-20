//5644. [모의 SW 역량테스트] 무선 충전
import java.util.Scanner;

public class SWEA5644 {//SWEA 
	static int M, bcCnt; //이동 횟수, 충전기 수
	static int[] pathA, pathB, playerA, playerB;
	static int[][] bc;
	
	static int[] dx = {0,0,1,0,-1}; //x랑 y로 사용.
	static int[] dy = {0,-1,0,1,0}; //상우하좌
	
	public static void main(String[] args){
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		playerA = new int[2];
		playerB = new int[2];
		
		for(int tc=1; tc<=T; tc++) {
			M = sc.nextInt(); //이동횟수
			bcCnt = sc.nextInt(); //충전기 수
			
			playerA[0] = playerA[1] = 1;
			playerB[0] = playerB[1] = 10;
			
			pathA = new int[M+1]; //A의 경로
			pathB = new int[M+1]; //B의 경로
			bc = new int[bcCnt][4]; //충전기 정보
			
			for(int i=1; i<M+1; i++) { // i=0값은 0으로 남아 있음. 처음 위치부터 충전 가능한지 살펴보기
				pathA[i] = sc.nextInt();
			}
			for(int i=1; i<M+1; i++) {
				pathB[i] = sc.nextInt();
			}
			
			
			for(int i=0; i< bcCnt; i++) { //충전기 정보 저장.
				bc[i][0] = sc.nextInt(); //x
				bc[i][1] = sc.nextInt(); //y
				bc[i][2] = sc.nextInt(); //거리
				bc[i][3] = sc.nextInt(); //충전량
				
			}
			
			System.out.println("#"+tc+" "+move());
		}
	}
	
	private static int move(){
		int totalSum = 0;
		//매 시간마다 각 위치에서 두 플레이어의 최대 충전량을 계산하여 합산.
		for(int t=0; t<M+1; t++) { //초기 위치에서도 충전 시도.
			//시간에 따라 A와 B의 위치 변경. 
			playerA[0] += dx[pathA[t]];
			playerA[1] += dy[pathA[t]];
			playerB[0] += dx[pathB[t]];
			playerB[1] += dy[pathB[t]];
			
			//현 위치에서의 최대 충전량 계산하기
			totalSum += getMaxCharge();
		}
		return totalSum;
	}
	
	private static int check(int a, int x, int y) {
		//충전기 a에서 (x,y)까지의 거리가 충전할 수 있는 거리라면 충전량을 보내주고, 충전을 못한다면 0 리턴.
		return Math.abs(bc[a][0]-x)+Math.abs(bc[a][1]-y) <= bc[a][2] ? bc[a][3] : 0;
	}
	
	private static int getMaxCharge() {
		int max = 0;
		for(int a=0; a<bcCnt; a++) { //플레이어 A가 선택한 BC
			for(int b=0; b<bcCnt; b++) { //플레이어 B가 선택한 BC
				int sum = 0;
				int amountA = check(a, playerA[0], playerA[1]);
				int amountB = check(b, playerB[0], playerB[1]);
				
				//두 충전소가 다르면 충전량 나누지 않아도 됨.
				if(a != b) //어차피 함수에서 충전 불가능하면 0 보내주니까 그냥 더하면 됨.
					sum = amountA + amountB;
				else //충전소가 같다면 둘 중에 큰 값 가져오면 됨. 
					sum = Math.max(amountA, amountB);
				
				if(max < sum)
					max = sum;
			}
		}
		return max;
	}
}