#include <iostream>
#include <vector>
#include <stack>
#include <algorithm>

using namespace std;

int r, c, n;
char map[201][201];
int visit[201][201];
int dx[4] = { 0, 0, 1, -1 };
int dy[4] = { -1, 1, 0, 0 };

bool OOB(int x, int y) {
	if (x >= 0 && x < r && y >= 0 && y < c)
		return false;
	return true;
}

void search(int x, int y) {
	
	visit[x][y] = 1;
	for (int i = 0; i < 4; i++) {
		int nx = x + dx[i];
		int ny = y + dy[i];

		if (!OOB(nx, ny) && visit[nx][ny] == 0 && map[nx][ny] == '.') {
			visit[nx][ny] = 1;
		}
	}

}

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> r >> c >> n;

	for (int i = 0; i < r; i++) {
		for (int j = 0; j < c; j++) {
			char tmp;
			cin >> tmp;
			map[i][j] = tmp;
		}
	}
	if (n % 2 == 0) {
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				cout << 'O';
			}
			cout << '\n';
		}
		return 0;
	}
	else if(n > 1){
		int times = 2;
		if (n % 4 == 3)
			times = 1;

		while (times--) {
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					if (map[i][j] == 'O' && visit[i][j] == 0) {
						search(i, j);
					}
				}
			}

			//chaging & initialization
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					if (visit[i][j] == 1)
						map[i][j] = '.';
					else
						map[i][j] = 'O';
					visit[i][j] = 0;
				}
			}
		}
	}

	for (int i = 0; i < r; i++) {
		for (int j = 0; j < c; j++) {
			cout << map[i][j];
		}
		cout << '\n';
	}

	return 0;
}