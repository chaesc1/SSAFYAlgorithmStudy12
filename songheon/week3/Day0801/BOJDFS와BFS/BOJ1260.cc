#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
#include <stack>

using namespace std;

int n, m; //node, edge
int adj[1001][1001];
int check[1001];
int start;


void dfs(int start) {

	check[start] = 1;
	cout << start << " ";

	for (int i = 1; i <= n; i++)
		if (adj[start][i] == 1 && check[i] == 0) {
			dfs(i);
		}

}

int main() {

	cin >> n >> m >> start;

	while (m--) {
		int s, e;
		cin >> s >> e;
		adj[s][e] = 1;
		adj[e][s] = 1;
	}

	//dfs
	//dfs(start);

	stack<int> st;
	st.push(start);

	while (!st.empty()) {
		int tmp = st.top();
		if(check[tmp] == 0)
			cout << tmp << " ";
		check[tmp] = 1;
		st.pop();

		for (int i = n; i >= 1; i--) {
			if (adj[tmp][i] == 1 && check[i] == 0) {
				st.push(i);
			}
		}
	}

	cout << '\n';

	//initialization
	for (int i = 1; i <= n; i++) {
		check[i] = 0;
	}



	//bfs
	queue<int> q;
	q.push(start);
	check[start] = 1;

	while (!q.empty()) {
		int tmp = q.front();
		cout << tmp << " ";
		q.pop();

		for (int i = 1; i <= n; i++) {
			if (adj[tmp][i] == 1 && check[i] == 0) {
				q.push(i);
				check[i] = 1;
			}
		}
	}

	return 0;
}