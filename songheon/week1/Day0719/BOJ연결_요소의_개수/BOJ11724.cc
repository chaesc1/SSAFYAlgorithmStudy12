#include <iostream>
#include <vector>
#include <queue>

using namespace std;


//방향이 없는 그래프 -> 인접리스트, 노드 별 bfs/dfs
int n, m; //노드 수, 엣지 수
vector<int> adj[1001]; //인접리스트
int check[1001];

void bfs(int start) {
	queue<int> q;
	q.push(start);
	check[start] = 1;

	while (!q.empty()) {
		int tmp = q.front();
		q.pop();

		for (int v : adj[tmp]) {
			if (check[v] == 0) {
				q.push(v);
				check[v] = 1;
			}
		}
	}
}

int main() {
	
	cin >> n >> m;
	
	for (int i = 0; i < m; i++) {
		int s, d;
		cin >> s >> d;
		adj[s].push_back(d);
		adj[d].push_back(s);
	}

	int answer = 0;
	for (int i = 1; i <= n; i++) {
		if (check[i] != 0)
			continue;

		bfs(i);
		answer++;
	}

	cout << answer;

	
	return 0;
}