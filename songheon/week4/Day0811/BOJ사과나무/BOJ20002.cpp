#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int n;
int profit[300][300];
int accum[300][300];

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cin >> profit[i][j];
		}
	}

	//make accum
	accum[0][0] = profit[0][0];
	for (int i = 1; i < n; i++) {
		accum[0][i] = accum[0][i - 1] + profit[0][i];
		accum[i][0] = accum[i - 1][0] + profit[i][0];
	}
	for (int i = 1; i < n; i++) {
		for (int j = 1; j < n; j++) {
			accum[i][j] = accum[i - 1][j] + accum[i][j - 1] - accum[i - 1][j - 1] + profit[i][j];
		}
	}
	cout << '\n';
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cout << accum[i][j] << " ";
		}
		cout << '\n';
	}




	int mx = -300000;
	//find max value
	for (int k = 1; k <= n; k++) {
		for (int i = k-1; i < n; i++) {
			for (int j = k-1; j < n; j++) {
				int v = accum[i][j];
				if (i - k >= 0)
					v -= accum[i - k][j];
				if (j - k >= 0)
					v -= accum[i][j - k];
				if (i - k >= 0 && j - k >= 0)
					v += accum[i - k][j - k];
				mx = max(mx, v);
			}
		}
	}
	cout << mx;

	return 0;
}