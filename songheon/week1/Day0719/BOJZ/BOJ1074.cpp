#include <iostream>
#include <algorithm>
#include <cmath>

using namespace std;

int n, r, c;
int cnt = 0;
int dx[4] = { 0, 0, 1, 1 };
int dy[4] = { 0, 1, 0, 1 };

bool range(int x, int y, int range) {
	if (x <= r && r < x + range
		&& y <= c && c < y + range)
		return true;
	else
		return false;
}

void searchz(int x, int y, int sz) {

	if (x == r && y == c) {
		cout << cnt << '\n';
		return;
	}

	//���� ������ ���� -> ������ �ٿ������� Ž��
	if (range(x, y, sz)) {
		for (int i = 0; i < 4; i++) {
			searchz(x + sz / 2 * dx[i], y + sz / 2 * dy[i], sz / 2);
		}
	}

	//���� ������ ����x -> ���� ������ŭ Ž���ߴ� ����
	else {
		cnt += sz * sz;
	}
}


int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n >> r >> c;
	searchz(0, 0, pow(2, n));

	return 0;
}
