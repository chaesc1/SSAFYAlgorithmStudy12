#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

int n;
int board[101][101];
int check[101][101];
int dx[4] = { 0, 1, 0, -1 };
int dy[4] = { 1, 0, -1, 0 };

bool OOB(int x, int y) {
	if (x >= 0 && x < n
		&& y >= 0 && y < n) {
		return false;
	}
	return true;
}

void bfs(int x, int y, int h) {
	queue<pair<int, int>> q;
	q.push({ x, y });
	check[x][y] = 1;

	while (!q.empty()) {
		int tx = q.front().first;
		int ty = q.front().second;
		q.pop();

		for (int i = 0; i < 4; i++) {
			int nx = tx + dx[i];
			int ny = ty + dy[i];

			if (!OOB(nx, ny)
				&& check[nx][ny] == 0
				&& board[nx][ny] > h) {
				q.push({ nx, ny });
				check[nx][ny] = 1;
			}
		}

	}

}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	
	cin >> n;
	
	int mn = 101;
	int mx = 0;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			int tmp;
			cin >> tmp;
			board[i][j] = tmp;
			mn = min(mn, tmp);
			mx = max(mx, tmp);
		}
	}

	int ans = 1;
	for (int h = mn; h < mx; h++) { //높이가 h인 지점까지 잠김 -> h보다 높은 지점만 통과
		//safe 구역 몇 개인지 찾아야 함
		int safe = 0;

		//initialization
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				check[i][j] = 0;
			}
		}

		//count the zones
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (check[i][j] == 0 && board[i][j] > h) {
					bfs(i, j, h);
					safe++;
				}
			}
		}

		ans = max(ans, safe);

	}

		cout << ans;

	return 0;
}