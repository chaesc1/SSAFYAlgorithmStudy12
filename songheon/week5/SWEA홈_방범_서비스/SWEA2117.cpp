#include <iostream>
#include <vector>
#include <cmath>

using namespace std;

int tc;
int n, m;
int map[20][20];
int answer;
vector<pair<int, int>> house; //the information for house pos

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);


	cin >> tc;

	for (int t = 1; t <= tc; t++) {
		//make the information field
		house.clear();
		cin >> n >> m;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				cin >> map[i][j];

				if (map[i][j] == 1)
					house.push_back({ i, j });
			}
		}

		answer = 1;
		int k = 2;
		int price = 0;

		//searching by k (k >= 2)
		while (true) {
			price = k * k + (k - 1) * (k - 1);
			if (price > house.size() * m || k > n + 1)
				break;

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {

					int hn = 0;
					//current pos(the center of range) : map[i][j]
					for (int h = 0; h < house.size(); h++) {
						int hx = house[h].first;
						int hy = house[h].second;

						if (abs(i - hx) + abs(j - hy) < k) { //checking the houses in the range
							hn++;
						}
					}

					if(price <= hn * m)
						answer = max(hn, answer);
				}
			}


			k++;
		}
		
		cout << "#" << t << " " << answer;
	}


	return 0;
}