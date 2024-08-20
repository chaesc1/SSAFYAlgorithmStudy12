#include <iostream>
#include <vector>
#include <queue>
#include <cmath>

using namespace std;

struct Node {
	int x;
	int y;
	int h;

	Node(int t_x, int t_y, int t_h) : x(t_x), y(t_y), h(t_h) {}
};


int t, n, k;
int map[8][8];
bool visit[8][8];
int dx[4] = { -1, 1, 0, 0 };
int dy[4] = { 0, 0, -1, 1 };
int answer;
vector<Node> pole; //봉우리 정보 저장



bool OOB(int x, int y) {
	if (x >= 0 && x < n && y >= 0 && y < n)
		return false;
	return true;
}

void findRoad(Node pole, int len, int cut) {

	answer = max(answer, len);
	
	for (int i = 0; i < 4; i++) {
		int tx = pole.x + dx[i];
		int ty = pole.y + dy[i];

		if (!OOB(tx, ty) && !visit[tx][ty]) {


			if (pole.h > map[tx][ty]) {
				visit[tx][ty] = true;
				findRoad(Node(tx, ty, map[tx][ty]), len + 1, cut);
				visit[tx][ty] = false;
			}

			else {
				if (cut == 0) {
					int tmp = map[tx][ty];
					for (int j = 1; j <= k; j++) {
						map[tx][ty] = --map[tx][ty];
						if (pole.h > map[tx][ty]) {
							visit[tx][ty] = true;
							findRoad(Node(tx, ty, map[tx][ty]), len + 1, cut+1);
							visit[tx][ty] = false;
							break;
						}
					}
					map[tx][ty] = tmp;
				}

			}


		}
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> t;

	for (int tc = 1; tc <= t; tc++) {

		//initialization
		answer = 0;
		pole.clear();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				visit[i][j] = false;
			}
		}

		//get input
		cin >> n >> k;
		int tmp = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				cin >> map[i][j];
				tmp = max(tmp, map[i][j]);
			}
		}
		//make pole info
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] == tmp)
					pole.push_back(Node(i, j, tmp));
			}
		}


		for (int p = 0; p < pole.size(); p++) {
			visit[pole[p].x][pole[p].y] = true;
			findRoad(pole[p], 1, 0);
			visit[pole[p].x][pole[p].y] = false;
		}

		cout << "#" << tc << " " << answer << '\n';
	}


	return 0;
}