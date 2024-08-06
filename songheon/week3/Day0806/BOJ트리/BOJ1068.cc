#include <iostream>
#include <vector>

using namespace std;

int n, target;
vector<int> tree[51];
int cnt;
int root;


void search(int start, int target) {
	

	for (int i = 0; i < tree[start].size(); i++) {
		if (tree[start][i] == target) {
			tree[start].erase(tree[start].begin() + i);
			return;
		}
		search(tree[start][i], target);
	}

}

void counting(int start) {


	if (tree[start].size() == 0) {
		cnt++;
	}

	for (int i = 0; i < tree[start].size(); i++) {
		counting(tree[start][i]);
	}

}


int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n;
	
	//making tree
	for (int i = 0; i < n; i++) {
		int p;
		cin >> p;

		if (p != -1) {
			tree[p].push_back(i);
		}
		else {
			root = i;
		}
	}

	//search target
	cin >> target;

	if (target == root) {
		cout << 0;
		return 0;
	}
	search(root, target);

	//count leaves
	counting(root);


	cout << cnt;

	return 0;
}