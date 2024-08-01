#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
#include <string>

using namespace std;

int n;
int dx[4] = { -1, 0, 1, 0 };
int dy[4] = { 0, 1, 0, -1 };
int board[101][101];
int check[101][101];


bool OOB(int x, int y) {
	if (x >= 0 && x < n
		&& y >= 0 && y < n)
		return false;
	return true;
}

int bfs(int x, int y) {
	//bfs
	queue <pair<int, int>> q;
	q.push({x, y});
	check[x][y] = 1;
	int cnt = 1;

	while (!q.empty()) {
		int tx = q.front().first;
		int ty = q.front().second;
		
		q.pop();

		for (int i = 0; i < 4; i++) {
			int nx = tx + dx[i];
			int ny = ty + dy[i];

			if (!OOB(nx, ny)
				&& board[nx][ny] == 1
				&& check[nx][ny] == 0) {
				q.push({ nx, ny });
				check[nx][ny] = 1;
				cnt++;
			}
		}
	}

	return cnt;
}


int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n;

	for (int i = 0; i < n; i++) {
		string str;
		cin >> str;
		for (int j = 0; j < n; j++) {
			board[i][j] = str[j] - '0';
			check[i][j] = 0;
		}
	}

	vector<int> house;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (board[i][j] == 1 && check[i][j] == 0) {
				house.push_back(bfs(i, j));
			}
		}
	}

	sort(house.begin(), house.end());
	cout << house.size() << '\n';
	for (int i = 0; i < house.size(); i++) {
		cout << house[i] << '\n';
	}



	return 0;
}