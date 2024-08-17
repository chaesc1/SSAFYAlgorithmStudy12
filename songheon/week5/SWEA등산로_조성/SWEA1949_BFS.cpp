//#include <iostream>
//#include <vector>
//#include <queue>
//#include <cmath>
//
//using namespace std;
//
//struct Node {
//	int x;
//	int y;
//	int h;
//	int len;
//
//	Node(int t_x, int t_y, int t_h, int t_len) : x(t_x), y(t_y), h(t_h), len(t_len) {}
//};
//
//
//int t, n, k;
//int map[8][8];
//int dx[4] = { -1, 1, 0, 0 };
//int dy[4] = { 0, 0, -1, 1 };
//int answer;
//vector<Node> pole; //봉우리 정보 저장
//
//
//
//bool OOB(int x, int y) {
//	if (x >= 0 && x < n && y >= 0 && y < n)
//		return false;
//	return true;
//}
//
//int findRoad() {
//	int mx = 0;
//	for (int p = 0; p < pole.size(); p++) {
//		//bfs
//		queue<Node> q;
//		q.push(pole[p]);
//
//
//		while (!q.empty()) {
//			Node cur = q.front();
//			q.pop();
//
//			int nx = cur.x;
//			int ny = cur.y;
//			int nh = cur.h;
//			int nlen = cur.len;
//
//			int cnt = 0;
//			for (int i = 0; i < 4; i++) {
//				int tx = nx + dx[i];
//				int ty = ny + dy[i];
//
//				if (!OOB(tx, ty) && map[tx][ty] < nh) {
//					q.push(Node(tx, ty, map[tx][ty], nlen + 1));
//					cnt++;
//					continue;
//				}
//
//				if (i == 3 && cnt == 0) {
//					//route가 끝나는 지점
//					mx = max(mx, nlen);
//				}
//
//			}
//
//		}
//	}
//
//	return mx;
//}
//
//int main() {
//	ios_base::sync_with_stdio(false);
//	cin.tie(NULL);
//	cout.tie(NULL);
//
//	cin >> t;
//
//	for (int tc = 1; tc <= t; tc++) {
//
//		//initialization
//		answer = 0;
//		pole.clear();
//
//		//get input
//		cin >> n >> k;
//		int tmp = 0;
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < n; j++) {
//				cin >> map[i][j];
//				tmp = max(tmp, map[i][j]);
//			}
//		}
//		//make pole info
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < n; j++) {
//				if (map[i][j] == tmp)
//					pole.push_back(Node(i, j, tmp, 1));
//			}
//		}
//
//		//original status
//		answer = findRoad();
//
//
//		//cutting height
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < n; j++) {
//				//select cutting spot : map[i][j]
//
//				int curpole = -1;
//				for (int p = 0; p < pole.size(); p++) {
//					if (i == pole[p].x && j == pole[p].y) {
//						curpole = p;
//						break;
//					}
//				}
//
//
//				int ori = map[i][j];
//				for (int cut = 1; cut <= k; cut++) {
//					map[i][j]--;
//					if (curpole != -1) {
//						pole[curpole].h = map[i][j];
//					}
//					answer = max(answer, findRoad());
//				}
//				map[i][j] = ori;
//				if (curpole != -1) {
//					pole[curpole].h = map[i][j];
//				}
//
//			}
//		}
//
//
//
//		cout << "#" << tc << " " << answer << '\n';
//	}
//
//
//	return 0;
//}