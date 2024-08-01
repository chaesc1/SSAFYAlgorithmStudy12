#include <iostream>
#include <vector>
#include <algorithm>
#include <string>
#include <map>

using namespace std;

int n;
map<string, int> cnt;



int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n;
	for (int i = 0; i < n; i++) {
		string full;
		cin >> full;
		string extend;
		bool tmp = false;
		for (char c : full) {
			if (c == '.') {
				tmp = true;
				continue;
			}
			if (tmp) {
				extend += c;
			}
		}
		
		if (cnt.find(extend) != cnt.end()) {
			cnt.at(extend)++;
		}
		else {
			cnt.insert({ extend, 1 });
		}
	}

	for (pair<string, int> p : cnt) {
		cout << p.first << " " << p.second << '\n';
	}

	

	return 0;
}