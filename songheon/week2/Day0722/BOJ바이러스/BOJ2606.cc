#include <iostream>
#include <vector>
#include <queue>
using namespace std;

vector<int> adj[101];
int checked[101];

int main() {
	int n;
	int link;

	cin >> n >> link;

	for (int i = 0; i < link; i++) {
		int a, b; //start, destination
		cin >> a >> b;

		adj[a].push_back(b);
		adj[b].push_back(a);
	}

	queue<int> q;
	q.push(1);
	checked[1] = 1;
	int ans = 0;
	while (!q.empty()) {
		int tmp = q.front();
		q.pop();

		//tmp와 인접하고, check되지 않은 원소 -> q에 푸쉬
		for (int i : adj[tmp]) {
			if (checked[i] == 0) {
				q.push(i);
				checked[i] = 1;
				ans++;
			}
		}
	}

	cout << ans;


	return 0;
}