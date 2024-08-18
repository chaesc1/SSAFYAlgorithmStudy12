#include <iostream>
#include <vector>
#include <cmath>

using namespace std;

int t, n;
int synergy[16][16];
int visit[16][16];
int food[16];
bool pick[16];
int answer;


void calc() {
	int a = 0, b = 0;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (synergy[i][j] != 0 && visit[i][j] == 2) {
				a += synergy[i][j];
			}
			else if (synergy[i][j] != 0 && visit[i][j] == -2) {
				b += synergy[i][j];
			}
		}
	}

	answer = min(answer, abs(a - b));
}

void combi(int start, int order) {

	if (order == n / 2) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				visit[i][j] = 0;
			}
		}
		for (int i = 0; i < n; i++) {
			if (pick[i]) {
				for (int k = 0; k < n; k++) {
					visit[i][k]++;
					visit[k][i]++;
				}
			}
			else {
				for (int k = 0; k < n; k++) {
					visit[i][k]--;
					visit[k][i]--;
				}
			}
		}
		calc();
		return;

	}


	for (int i = start; i < n; i++) {
		if (!pick[i]) {
			pick[i] = true;
			combi(i + 1, order + 1);
			pick[i] = false;
		}
	}
}


int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	for (int i = 0; i < 16; i++) {
		food[i] = i;
		pick[i] = false;
	}

	cin >> t;

	for (int tc = 1; tc <= t; tc++) {

		answer = 987654321;


		cin >> n;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				cin >> synergy[i][j];
			}
		}


		combi(0, 0);

		cout << "#" << tc << " " << answer << '\n';
	}


	return 0;
}