import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Solution {

	static int tc;
	static int h, w;
	static char[][] map;
	static int n;
	static String command;
	static Player player;
	static int dx[] = { -1, 0, 1, 0 };
	static int dy[] = { 0, 1, 0, -1 }; // 상 우 하 좌 (시계 방향)
	static HashMap<Character, Integer> type;
	static HashMap<Character, Integer> direction;
	static char pdir[] = {'^', '>', 'v', '<'};
	static StringBuilder sb;

	static class Player {
		int x;
		int y;
		int dir;

		public Player(int x, int y, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		type = new HashMap<Character, Integer>();
		direction = new HashMap<Character, Integer>();
		
		type.put('U', 0);
		type.put('R', 1);
		type.put('D', 2);
		type.put('L', 3);
		
		direction.put('^', 0);
		direction.put('>', 1);
		direction.put('v', 2);
		direction.put('<', 3);
		
		tc = Integer.parseInt(br.readLine());

		for (int t = 1; t <= tc; t++) {

			// 입력
			StringTokenizer st = new StringTokenizer(br.readLine());
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());

			map = new char[h][w];

			String tmp;
			for (int i = 0; i < h; i++) {
				tmp = br.readLine();
				for (int j = 0; j < w; j++) {
					map[i][j] = tmp.charAt(j);
					if (map[i][j] == '^' || map[i][j] == '>' || map[i][j] == 'v'|| map[i][j] == '<')
						player = new Player(i, j, direction.get(map[i][j]));
					
				}
			}
			n = Integer.parseInt(br.readLine());
			command = br.readLine();
			
			sb.append("#").append(t).append(" ");

			// command대로 실행
			gameStart();
		}
		System.out.print(sb);
	}

	static void gameStart() {
		for (int k = 0; k < n; k++) {
			char c = command.charAt(k);
			
			if(c != 'S') {
				int tx, ty;
				int ctype = type.get(c);
				player.dir = ctype;
				tx = player.x + dx[ctype];
				ty = player.y + dy[ctype];
				
				if(tx < 0 || tx >= h || ty < 0 || ty >= w) {
					continue;
				}
				
				if(map[tx][ty] == '.') {
					map[player.x][player.y] = '.';
					player.x = tx;
					player.y = ty;
				}
			}
			else {
				int curdir = player.dir;
				int tx = player.x;
				int ty = player.y;
				while(true) {
					tx += dx[curdir];
					ty += dy[curdir];
					
					if(tx < 0 || tx >= h || ty < 0 || ty >= w)
						break;
					
					if(map[tx][ty] == '*') {
						map[tx][ty] = '.';
						break;
					}
					if(map[tx][ty] == '#') {
						break;
					}
				}
			}
		}
		//정답 배열 저장
		map[player.x][player.y] = pdir[player.dir];
		for(int i = 0; i < h; i++) {
			for(int j = 0; j < w; j++) {
				sb.append(map[i][j]);
			}
			sb.append('\n');
		}
	}
	
	
}