#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
#include <cmath>

using namespace std;

int tc;
int n; //the number of stores
pair<int, int> house;
pair<int, int> fes;
vector<pair<int, int>> spos;
vector<int> visit;


int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> tc;
	while (tc--) {
		cin >> n;

		cin >> house.first >> house.second;

		spos.clear();
		visit.clear();
		for (int i = 0; i < n; i++) {
			int x, y;
			cin >> x >> y;
			spos.push_back({ x, y });
			visit.push_back(0);
		}
		cin >> fes.first >> fes.second;


		queue<pair<int, int>> q;
		q.push(house);

		bool flag = false;
		while (!q.empty()) {
			int x = q.front().first;
			int y = q.front().second;

			q.pop();

			if (abs(fes.first - x) + abs(fes.second - y) <= 1000) {
				flag = true;
				break;
			}


			for (int i = 0; i < spos.size(); i++) {
				int dist = abs(spos[i].first - x) + abs(spos[i].second - y);
				if (dist <= 1000 && visit[i] == 0) {
					q.push({spos[i].first, spos[i].second});
					visit[i] = 1;
				}
			}
		}

		if (flag) {
			cout << "happy" << '\n';
		}
		else
			cout << "sad" << '\n';
	}

	return 0;
}