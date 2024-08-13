#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int tc, n, m, k;
int board[351][351];
int dx[4] = { 0, 0, -1, 1 };
int dy[4] = { 1, -1, 0, 0 };

struct cell {
	int vital;
	int act;
	int non_act;
	int status; // 0: non_act, 1: act, 2: dead
	int x, y;

	cell(int t_vital, int t_act, int t_non_act, int t_status, int t_x, int t_y)
		:vital(t_vital), act(t_act), non_act(t_non_act), status(t_status), x(t_x), y(t_y) {};
};

struct compare {
	bool operator()(cell a, cell b) {
		return a.vital < b.vital;
	}
};

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> tc;
	for (int t = 1; t <= tc; t++) {
		priority_queue<cell, vector<cell>, compare> pq;
		cin >> n >> m >> k;

		// board의 중간 좌표 : (n + k) / 2 , (m + k) / 2
		// board 시작 좌표 : k / 2 + 1 , k / 2 + 1
		int start = k / 2 + 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				cin >> board[start + i][start + j];
				if (board[start + i][start + j] != 0) {
					int vital = board[start + i][start + j];
					cell tmp(vital, vital, vital, 0, start + i, start + j);
					pq.push(tmp);
				}
			}
		}

		for (int i = 0; i < k; i++) {
			queue<cell> q;
			while (!pq.empty()) {
				cell cur = pq.top();
				pq.pop();

				if (cur.status == 0) { //비활성화
					cur.non_act--;
					if (cur.non_act == 0)
						cur.status = 1;
					q.push(cur);
				}
				else if (cur.status == 1) { //활성화
					cur.act--;
					//증식
					for (int i = 0; i < 4; i++) {
						int tx = cur.x + dx[i];
						int ty = cur.y + dy[i];

						if (board[tx][ty] == 0) {
							board[tx][ty] = cur.vital;
							cell tmp(cur.vital, cur.vital, cur.vital, 0, tx, ty);
							q.push(tmp);
						}
					}
					if (cur.act != 0) { //아직 죽지 않은 상태
						q.push(cur);
					}
					else { //죽은 상태
						cur.status = 2;
					}
				}
			}

			while (!q.empty()) {
				pq.push(q.front());
				q.pop();
			}
		}
		cout << "#" << t << " " << pq.size() << '\n';

		//배열 초기화
		for (int i = 0; i < n + k + 1; i++) {
			for (int j = 0; j < m + k + 1; j++) {
				board[i][j] = 0;
			}
		}
	}

	return 0;
}