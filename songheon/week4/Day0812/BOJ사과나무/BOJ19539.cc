#include <iostream>
#include <vector>

using namespace std;

int n;
int h[100001];


int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n;
	
	int total = 0;
	int retot = 0;

	for (int i = 0; i < n; i++) {
		cin >> h[i];
		total += h[i];
	}

	if (total % 3 != 0) {
		cout << "NO";
		return 0;
	}
	else {
		for (int i = 0; i < n; i++) {
			retot += h[i] / 2;
		}
	}
	if (retot >= total / 3) {
		cout << "YES";
		return 0;
	}

	cout << "NO";




	return 0;
}