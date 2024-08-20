#include <iostream>
#include <vector>
#include <queue>
#include <cmath>

using namespace std;

int tc, k;
int mag[4][8];
int rot[4], visit[4];



//void rotation(int* arr, int dir) {
//	if (dir == 1) {
//		int tmp = arr[7];
//		for (int i = 7; i > 0; i--) {
//			arr[i] = arr[i - 1];
//		}
//		arr[0] = arr[7];
//	}
//	else if (dir == -1) {
//		int tmp = arr[0];
//		for (int i = 0; i < 7; i++) {
//			arr[i] = arr[i + 1];
//		}
//		arr[7] = arr[0];
//	}
//}


int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> tc;
	for (int t = 1; t <= tc; t++) {

		cin >> k;

		//magnet information
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 8; j++) {
				cin >> mag[i][j];
			}
		}

		//rotation
		while (k--) {

			//initialization
			for (int i = 0; i < 4; i++) {
				rot[i] = 0;
				visit[i] = 0;
			}

			int magnum; //회전할 자석 번호
			int dir; //회전 방향 (1 : 시계, -1 : 반시계)
			cin >> magnum >> dir;
			magnum --; //인덱스라서 조정

			rot[magnum] = dir;

			queue<pair<int, int>> q; //magnum, dir
			q.push({ magnum, dir });
			visit[magnum] = 1;

			while (!q.empty()) {
				pair<int, int> cur = q.front();
				q.pop();

				//좌우 탐색하며 회전 정보 업데이트
				int left = cur.first - 1;
				int right = cur.first + 1;
				int curdir = cur.second;

				//left 확인
				if (left >= 0 && left < 8) {
					if (visit[left] == 0 && mag[cur.first][6] != mag[left][2]) {
						visit[left] = 1;
						rot[left] = -(curdir);
						q.push({ left, rot[left] });
					}
				}
				//right 확인
				if (right >= 0 && right < 8) {
					if (visit[right] == 0 && mag[cur.first][2] != mag[right][6]) {
						visit[right] = 1;
						rot[right] = -(curdir);
						q.push({ right, rot[right] });
					}
				}

			}

			//회전정보따라 회전
			for (int i = 0; i < 4; i++) {
				if (rot[i] == 1) {
					int tmp = mag[i][7];
					for (int j = 7; j > 0; j--) {
						mag[i][j] = mag[i][j - 1];
					}
					mag[i][0] = tmp;
				}
				else if (rot[i] == -1) {
					int tmp = mag[i][0];
					for (int j = 0; j < 7; j++) {
						mag[i][j] = mag[i][j + 1];
					}
					mag[i][7] = tmp;
				}
			}
		}

		int score = 0;

		for (int i = 0; i < 4; i++) {
			if (mag[i][0] == 1)
				score += pow(2, i);
		}





		cout << "#" << t << " " << score << '\n';
	}


	return 0;
}