#include <iostream>
#include <vector>
#include <stack>
#include <algorithm>

using namespace std;

int n, m, k;
int map[101][101];
int visited[101][101];
vector<pair<int, int>> tr;
int dx[4] = { 0, 0, 1, -1 };
int dy[4] = { 1, -1, 0, 0 };

bool OOB(int x, int y) {
	if (x >= 1 && x <= n && y >= 1 && y <= m)
		return false;
	return true;
}



int dfs(int x, int y) {
	stack<pair<int, int>> st;
	st.push({ x, y });
	visited[x][y] = 1;


	int mx = 1;
	while (!st.empty()) {
		int tx = st.top().first;
		int ty = st.top().second;
		st.pop();

		for (int i = 0; i < 4; i++) {
			int nx = tx + dx[i];
			int ny = ty + dy[i];

			if (!OOB(nx, ny) && map[nx][ny] == 1 && visited[nx][ny] == 0) {
				st.push({ nx, ny });
				visited[nx][ny] = 1;
				mx++;
			}
		}
	}

	return mx;
}


int main() {
	
	cin >> n >> m >> k;


	for (int i = 0; i < k; i++) {
		int a, b;
		cin >> a >> b;
		map[a][b] = 1;
		tr.push_back({ a, b });
	}

	int answer = 0;
	for (pair<int, int> cur : tr) {
		if (visited[cur.first][cur.second] == 0) {
			answer = max(answer, dfs(cur.first, cur.second));
		}
	}

	cout << answer;





	return 0;
}