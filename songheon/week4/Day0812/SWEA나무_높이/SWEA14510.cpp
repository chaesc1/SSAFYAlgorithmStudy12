#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int tc;
int n;
int height[101];

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> tc;

	for(int t = 1; t <= tc; t++) {
		cin >> n;

		int mh = 0;
		for (int i = 0; i < n; i++) {
			cin >> height[i];
			mh = max(mh, height[i]);
		}

		int odd = 0, even = 0;
		for (int i = 0; i < n; i++) {
			odd += (mh - height[i]) % 2;
			even += (mh - height[i]) / 2;
		}
		if (even > odd) {
			while (abs(even - odd) > 1) {
				even -= 1;
				odd += 2;
			}
		}

		if (even > odd) {
			cout << "#" << t << " " << even * 2 << "\n";
		}
		else if (odd > even) {
			cout << "#" << t << " " << odd * 2 - 1 << "\n";;
		}
		else {
			cout << "#" << t << " " << even + odd << "\n";
		}
	}

	return 0;
}