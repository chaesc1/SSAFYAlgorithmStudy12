#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

char star[10000][10000];
int n;


void draw(int raw, int col, int n) {
	if (n == 3) {
		star[raw][col] = '*';
		star[raw + 1][col - 1] = '*';
		star[raw + 1][col + 1] = '*';
		star[raw + 2][col - 2] = '*';
		star[raw + 2][col + 2] = '*';
		star[raw + 2][col - 1] = '*';
		star[raw + 2][col + 1] = '*';
		star[raw + 2][col] = '*';
		return;

	}
	else {
		int tn = n / 2;
		draw(raw, col, tn);
		draw(raw + tn, col - tn, tn);
		draw(raw + tn, col + tn, tn);
		return;

	}
}


int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n;

	//initialization
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < 2 * n - 1; j++) {
			star[i][j] = ' ';
		}
	}

	//draw stars
	draw(0, n-1, n);

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < 2 * n - 1; j++) {
			cout << star[i][j];
		}
		if (i == n - 1)
			break;
		cout << '\n';
	}

	return 0;
}