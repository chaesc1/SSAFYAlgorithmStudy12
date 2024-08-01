#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

int n;
int sp, ep;
vector<int> board[101]; //adj list
int check[101];



int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n;

	cin >> sp >> ep;

	int edges;
	cin >> edges;

	for (int i = 0; i < edges; i++) {
		int a, b;
		cin >> a >> b;
		board[a].push_back(b);
		board[b].push_back(a);
	}
	

	queue<pair<int, int>> q; //node, cnt
	q.push({ sp, 0 });
	check[sp] = 1;

	while (!q.empty()) {
		int node = q.front().first;
		int cnt = q.front().second;

		if (node == ep) {
			cout << cnt;
			break;
		}

		q.pop();

		for (int i = 0; i < board[node].size(); i++) {
			int tmp = board[node][i];
			if (check[tmp] == 0) {
				q.push({ tmp, cnt + 1 });
				check[tmp] = 1;
			}
		}

	}

	if (q.empty())
		cout << -1;



	return 0;
}