#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

int n, m, dir;
int sx, sy;
int cnt;
int room[51][51];
int clean[51][51];
int dx[4] = { -1, 0, 1, 0 }; //북 동 남 서
int dy[4] = { 0, 1, 0, -1 };

bool OOB(int x, int y) {
	if (x >= 0 && x < n
		&& y >= 0 && y < m) {
		return false;
	}
	return true;
}


int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n >> m;
	cin >> sx >> sy >> dir;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			int tmp;
			cin >> tmp;
			room[i][j] = tmp;
			clean[i][j] = 0;
		}
	}

	queue<vector<int>> q; //x pos, y pos, direction
	q.push({ sx, sy, dir });
	clean[sx][sy] = 1;
	cnt++;

	while (true) {
		int x = q.front()[0];
		int y = q.front()[1];
		int d = q.front()[2];

		q.pop();

		bool flag = false;
		for (int i = 3; i >= 0; i--) {
			int nd = (d + i) % 4;
			int nx = x + dx[nd];
			int ny = y + dy[nd];

			if (clean[nx][ny] == 0 && room[nx][ny] == 0) {
				q.push({ nx, ny, nd });
				clean[nx][ny] = 1;
				cnt++;
				break;
			}


			if (i == 0) {
				flag = true;
				break;
			}
		}

		if (flag) {
			int nd = (d + 2) % 4;
			int nx = x + dx[nd];
			int ny = y + dy[nd];
			if (room[nx][ny] == 0) {
				q.push({ nx, ny, d });
				if (clean[nx][ny] == 0) {
					clean[nx][ny] = 1;
					cnt++;
				}
			}
			else {
				cout << cnt;
				break;
			}
		}

	}
	

	return 0;
}