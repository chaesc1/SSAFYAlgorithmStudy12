#include <iostream>
#include <vector>
#include <queue>

using namespace std;


struct student {
	int num = -1;
	int friends[4] = { -1, -1, -1, -1 };
};

struct cell {
	int x, y;
	bool take = false;
	int fav = 0;
	int emp = 0;
};

struct compare {
	bool operator()(cell a, cell b) {
		if (a.fav != b.fav) {
			return a.fav < b.fav;
		}
		else {
			if (a.emp != b.emp)
				return a.emp < b.emp;
			else {
				if (a.x != b.x) {
					return a.x > b.x;
				}
				else
					return a.y > b.y;
			}
		}
	}
};

int n;
int map[20][20]; //finally seat number
student students[401];
queue<student> q;
int dx[4] = { -1, 0, 1, 0 };
int dy[4] = { 0, 1, 0, -1 };
priority_queue<cell, vector<cell>, compare> info;


bool OOB(int x, int y) {
	if (x >= 0 && x < n && y >= 0 && y < n)
		return false;
	return true;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n;

	for (int i = 1; i <= n * n; i++) {
		int num, f0, f1, f2, f3;
		cin >> num >> f0 >> f1 >> f2 >> f3;
		students[num] = student();
		students[num].num = num;
		students[num].friends[0] = f0;
		students[num].friends[1] = f1;
		students[num].friends[2] = f2;
		students[num].friends[3] = f3;

		q.push(students[num]);

	}



	while (!q.empty()) {
		student cur = q.front();
		q.pop();

		//initial map info
		while (!info.empty()) {
			info.pop();
		}

		//update map info
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				cell tmp;
				tmp.x = i;
				tmp.y = j;
				if (map[i][j] == 0)
					tmp.take = false;
				else
					tmp.take = true;

				if (tmp.take == false) {
					//4search
					for (int k = 0; k < 4; k++) {
						int tx = tmp.x + dx[k];
						int ty = tmp.y + dy[k];
						if (!OOB(tx, ty)) {
							if (map[tx][ty] == 0)
								tmp.emp++;
							else {
								for (int f = 0; f < 4; f++) {
									if (cur.friends[f] == map[tx][ty]) {
										tmp.fav++;
										break;
									}
								}
							}
						}
					}
					info.push(tmp);
				}

			}
		}
		//find best place
		map[info.top().x][info.top().y] = cur.num;

		////print
		//for (int i = 0; i < n; i++) {
		//	for (int j = 0; j < n; j++) {
		//		cout << map[i][j] << " ";
		//	}
		//	cout << '\n';
		//}

		//cout << '\n';
	}

	/*cout << " test " << '\n';*/

	//만족도 검사
	int answer = 0;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			int cnt = 0;
			for (int k = 0; k < 4; k++) {
				int tx = i + dx[k];
				int ty = j + dy[k];
				if (!OOB(tx, ty)) {
					for (int f = 0; f < 4; f++) {
						if (students[map[i][j]].friends[f] == map[tx][ty]) {
							cnt++;
							break;
						}
					}
				}
			}
			if (cnt == 1)
				answer += 1;
			else if(cnt == 2) {
				answer += 10;
			}
			else if (cnt == 3) {
				answer += 100;
			}
			else if (cnt == 4) {
				answer += 1000;
			}
		}
	}





	cout << answer;



	return 0;
}