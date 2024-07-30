#include <iostream>
#include <vector>
#include <algorithm>
#include <string>
using namespace std;

int check[101];
string str;
int n;
int len;


void addC(int start, int end) {
	
	if (len >= n)
		return;

	char max = 'Z';
	int idx = n+1;
	for (int i = end - 1; i >= start; i--) {
		if (max >= str[i] && check[i] == 0) {
			max = str[i];
			idx = i;
		}
	}

	check[idx] = 1;
	len++;
	//print
	string tmp = "";
	for (int i = 0; i < n; i++) {
		if(check[i] == 1)
			tmp += str[i];
	}
	if (tmp.size() == n)
		cout << tmp;
	else
		cout << tmp << '\n';

	//recursion
	if(idx + 1 < end)
		addC(idx + 1, end);
	if(start < idx)
		addC(start, idx);
	return;

}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> str;
	n = str.length();

	addC(0, n);
	
	

	return 0;
}