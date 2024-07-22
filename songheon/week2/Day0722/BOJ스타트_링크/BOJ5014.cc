#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

int check[1000001];
int total, cur, goal, up, down;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> total >> cur >> goal >> up >> down;

	queue<pair<int, int>> q; //cur, cnt
	q.push({ cur, 0 });
	check[cur] = 1;

	while (!q.empty()) {
		int tmp = q.front().first;
		int cnt = q.front().second;

		if (tmp == goal) {
			cout << cnt;
			break;
		}

		q.pop();

		if (tmp + up <= total && check[tmp + up] == 0) {
			q.push({ tmp + up, cnt + 1 });
			check[tmp + up] = 1;
		}
		if (tmp - down > 0 && check[tmp - down] == 0) {
			q.push({ tmp - down, cnt + 1 });
			check[tmp - down] = 1;
		}


	}

	if (q.empty()) {
		cout << "use the stairs";
	}




	return 0;
}