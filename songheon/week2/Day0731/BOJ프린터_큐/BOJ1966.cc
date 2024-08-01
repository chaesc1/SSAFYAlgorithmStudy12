#include <iostream>
#include <queue>
#include <algorithm>

using namespace std;

int n;
int idx;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	int tc;
	cin >> tc;
	while (tc--) {

		cin >> n;
		cin >> idx;

		queue<pair<int, int>> q;
		vector<int> priority;

		for (int i = 0; i < n; i++) {
			int tmp;
			cin >> tmp;
			q.push({ i, tmp });
			priority.push_back(tmp);
		}

		sort(priority.begin(), priority.end(), greater<>());

		int time = 1;
		while (!priority.empty()) {
			if (priority.front() != q.front().second) {
				q.push(q.front());
			}
			else {
				if (q.front().first == idx) {
					cout << time << '\n';
					//return 0;
				}
				priority.erase(priority.begin());
				time++;
			}
			q.pop();
		}	

	}


	
	return 0;
}