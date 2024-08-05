#include <iostream>
#include <vector>

using namespace std;

int n, m;
vector<int> adj[2001];
int visit[2001];
bool connected = false;

void dfs(int start, int cnt) {

	if (cnt == 5) {
		connected = true;
		return;
	}
	for (int i = 0; i < adj[start].size(); i++) {
		int cur = adj[start][i];
		if (visit[cur] == 0) {
			visit[start] = 1;
			dfs(cur, cnt + 1);
			visit[cur] = 0;
		}
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n >> m;

	for (int i = 0; i < m; i++) {
		int a, b;
		cin >> a >> b;
		adj[a].push_back(b);
		adj[b].push_back(a);
	}

	for (int i = 0; i < n; i++) {
		visit[i] = 1;
		dfs(i, 1);
		visit[i] = 0;
		if (connected) {
			cout << 1;
			return 0;
		}
	}
	cout << 0;
	

	return 0;
}