// 백준 - 상어 초등학교, 골드5
//- 처음에 전부 빈칸이면 행렬 가장 가운데 자리로 배치
//- 1. 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
//- 2. 1을 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
//- 3. 2를 만족하는 칸도 여러 개인 경우에는 행의 번호가 가장 작은 칸으로, 
//   그러한 칸도 여러 개이면 열의 번호가 가장 작은 칸으로 자리를 정한다.
//- 자리 배치 끝나면 만족도를 구해야함
//- 클래스 사용해야할듯
   
import java.io.*;
import java.util.*;

class Seat{
	int x;
	int y;
	int emptyCnt;
	int friendCnt;
	
	public Seat(int x, int y, int emptyCnt, int friendCnt) {
		this.x = x;
		this.y = y;
		this.emptyCnt = emptyCnt;
		this.friendCnt = friendCnt;
	}
}

public class BOJ21608 {
	static int N, studentNum;
	static int[][] map;
	static HashMap<Integer, int[]> hashMap;
	static int[] dx = {1, 0, -1, 0}; //우하좌상
	static int[] dy = {0, 1, 0, -1};
	static int score = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); // 교실 크기
		studentNum = N * N;
		map = new int[N][N];
		hashMap = new HashMap<>();
		
		//입력받기
		for(int i = 0; i < studentNum; i++) {
			st = new StringTokenizer(br.readLine());
			
			int student = Integer.parseInt(st.nextToken());
			int[] likeFriends = new int[4];
			for(int j = 0; j < 4; j++) {
				likeFriends[j] = Integer.parseInt(st.nextToken());
			}
			
			//(1) 좋아하는 친구 저장
			hashMap.put(student, likeFriends);
			
			//(2) 학생 자리 앉히기
			putStudentSeat(student);
		}
		
		// (3)  만족도 구하기
		getScore();
		System.out.println(score);
		
	} //main 끝
	
	//좋아하는 학생이 인접한 자리 구하는 함수
	static void putStudentSeat(int student) {
		int[] friends = hashMap.get(student); //해당 학생이 좋아하는 친구 배열
		ArrayList<Seat> seats = new ArrayList<>(); //학생이 앉을 수 있는 자리(학생 마다 업데이트 해줘야함)
		
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < N; x++) {
				int friendCnt = 0;
				int emptyCnt = 0;
				
				//주변 위치 파악
				for(int d = 0; d < 4; d++) {
					int nx = x + dx[d];
					int ny = y + dy[d];
					
					if(nx < 0 || ny < 0 || nx >= N || ny >= N) {
						continue;
					}
					
					//좋아하는 친구가 있는지 확인
					for(int f = 0; f < 4; f++) {
						if(map[ny][nx] == friends[f]) friendCnt++;
					}
					// 빈 공간인지 확인
					if(map[ny][nx] == 0) emptyCnt++;
				}
				// 자리 찾아봤으니 Seat 클래스에 저장
				seats.add(new Seat(x, y, emptyCnt, friendCnt));
			}
		}
        /**
         * 조건
         * 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
         * 1을 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
         * 2를 만족하는 칸도 여러 개인 경우에는 행의 번호가 가장 작은 칸으로, 그러한 칸도 여러 개이면 열의 번호가 가장 작은 칸으로 자리를 정한다.
         */
		seats.sort((s1, s2) -> {
			if(s1.friendCnt == s2.friendCnt) {
				if(s1.emptyCnt == s2.emptyCnt) {
					if(s1.y == s2.y) {
						return s1.x - s2.x;
					}
					return s1.y - s2.y;
				}
				return s2.emptyCnt - s1.emptyCnt;
			}
			return s2.friendCnt - s1.friendCnt;
		});
		
		//학생 자리 업데이트 해주기
		for(Seat seat : seats) {
			if(map[seat.y][seat.x] != 0) continue;
			map[seat.y][seat.x] = student;
			return;
		}
		
	}
	
	static void getScore() {
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < N; x++) {
				
				int cnt = 0;
				int[] friends = hashMap.get(map[y][x]);
				
				//네 방향으로 친구 위치 확인하기
				for(int d = 0; d < 4; d++) {
					int ny = y + dy[d];
					int nx = x + dx[d];
					
					if(nx < 0 || ny < 0 || nx >= N || ny >= N) {
						continue;
					}
					
					//옆에 좋아하는 친구가 있는지 판단하기
					for(int f = 0; f < 4; f++) {
						if(map[ny][nx] == friends[f]) {
							cnt++;
						}
					}
				}
			      /**
                 * 좋아하는 학생 수 별 점수 : (0-0), (1-1), (2-10), (3-100), (4-1000)
                 */
				switch(cnt) {
					case 1: score += 1; break;
					case 2: score += 10; break;
					case 3: score += 100; break;
					case 4: score += 1000; break;
				}
			}
		}
	}
}




