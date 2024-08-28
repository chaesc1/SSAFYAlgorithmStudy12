import java.util.*;
import java.io.*;

public class Main {

	// 입력 받을 때 적의 정보 저장 -> 리스트
	// 적 클래스 : 위치, 타겟 지정
	// 궁수 3명을 m개의 열 중에 어디 놓을지 정하기 -> 조합 mC3
	// 정해진 자리에서 턴 시작
	// 공격 : 각 궁수마다 범위 내의 공격할 적 탐색 후 지정
	// 가장 가까운
	// 왼쪽
	// 적 순회(거꾸로 순회)하면서 지정된 적 제거, answer++
	// 적 리스트 사이즈 체크
	// 적이 없으면 게임 종료
	// 적 있으면 턴 계속 진행
	// 이동 : 각 적이 행 + 1 (위치 정보 수정)
	// 맵 밖으로 나갔다면 해당 적 제거

	static int n, m, d;
	static int map[][];
	static int input[];
	static int output[];
	static int answer;
	static ArrayList<Enemy> orienemies;
	static ArrayList<Enemy> enemies;

	static class Enemy{
		int x;
		int y;
		boolean target = false;

		public Enemy(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();
		d = sc.nextInt();

		map = new int[n][m];
		orienemies = new ArrayList<>();
		output = new int[3];
		input = new int[m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 1) {
					orienemies.add(new Enemy(i, j));
				}
			}
		}
		for (int i = 0; i < m; i++) {
			input[i] = i;
		}
		
		// 궁수 자리 정하기
		combi(0, 0);
		
		System.out.println(answer);

	}

	static void combi(int start, int order) {
		if (order == 3) {
			//궁수자리 지정 완료
			enemies = new ArrayList<>();
			for(int i = 0; i < orienemies.size(); i++) {
				enemies.add(new Enemy(orienemies.get(i).x, orienemies.get(i).y));
			}
			gameStart();
			return;
		}

		for (int i = start; i < m; i++) {
			output[order] = input[i];
			combi(i + 1, order + 1);
		}
	}

	static void gameStart() {

		int tanswer = 0;
		while (true) {
			for(Enemy e : enemies)
				e.target = false;
			
			// 궁수 공격 적 지정
			for (int i = 0; i < 3; i++) {
				int minD = d;
				int minR = m;
				Enemy target = null;

				for (int j = 0; j < enemies.size(); j++) {
					Enemy cur = enemies.get(j);
					int dist = Math.abs(output[i] - cur.y) + n - cur.x;

					// 공격할 적 판단
					if (minD >= dist) {
						if (minD == dist) {
							if (minR > cur.y) {
								target = cur;
								minR = cur.y;
							}
						} else {
							target = cur;
							minR = cur.y;
							minD = dist;
						}
					}
				}

				if (target != null) {
					target.target = true;
				}
			}

			// 적 제거
			for (int i = enemies.size() - 1; i >= 0; i--) {
				if (enemies.get(i).target) {
					tanswer++;
					enemies.remove(i);
					
				}
			}

			// 적 이동
			for (int i = enemies.size() - 1; i >= 0; i--) {
				if (enemies.get(i).x == n - 1) {
					enemies.remove(i);
				}
				else
					enemies.get(i).x++;
			}

			// 게임종료 판단
			if (enemies.size() == 0) {
				answer = Math.max(answer, tanswer);
				break;
			}
		}

	}
}