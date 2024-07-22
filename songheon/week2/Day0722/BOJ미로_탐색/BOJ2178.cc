#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
#include <string>

using namespace std;

int n, m;
int dx[4] = { -1, 0, 1, 0 };
int dy[4] = { 0, 1, 0, -1 };
int board[101][101];
int check[101][101];


bool OOB(int x, int y) {
	if (x >= 0 && x < n
		&& y >= 0 && y < m)
		return false;
	return true;
}


int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n >> m;

	for (int i = 0; i < n; i++) {
		string str;
		cin >> str;
		for (int j = 0; j < m; j++) {
			board[i][j] = str[j] - '0';
			check[i][j] = 0;
		}
	}


	//bfs
	//(0, 0) ~ (n-1, m-1)
	queue <vector<int>> q;
	q.push({ 0, 0, 1 });
	check[0][0] = 1;

	while (!q.empty()) {
		int x = q.front()[0];
		int y = q.front()[1];
		int cnt = q.front()[2];

		q.pop();

		if (x == n - 1 && y == m - 1) {
			cout << cnt;
			break;
		}

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (!OOB(nx, ny)
				&& board[nx][ny] == 1
				&& check[nx][ny] == 0) {
				q.push({ nx, ny, cnt + 1 });
				check[nx][ny] = 1;
			}
		}
	}

	return 0;
}