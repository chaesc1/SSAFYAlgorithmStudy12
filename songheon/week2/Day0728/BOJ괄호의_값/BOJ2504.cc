#include <iostream>
#include <vector>
#include <stack>

using namespace std;

int nest[30];

bool check(string str) {

	//is this right?
	stack<char> st;
	st.push(str[0]);

	for (int i = 1; i < str.size(); i++) {
		//여는 괄호
		if (str[i] == '(' || str[i] == '[') {
			st.push(str[i]);
			continue;
		}
		//닫힌 괄호
		else {
			if (st.empty()) {
				return false;
			}
			else {
				if (str[i] == ')') {
					if (st.top() == '(') {
						st.pop();
						continue;
					}
				}
				else if (str[i] == ']') {
					if (st.top() == '[') {
						st.pop();
						continue;
					}
				}
				else {
					return false;
				}
			}
		}

	}
	if (st.empty())
		return true;
	else
		return false;
}

void init() {
	for (int i = 0; i < 30; i++) {
		nest[i] = 0;
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	string str;
	cin >> str;

	//올바른 문자열 체크
	if (!check(str)) {
		cout << 0;
		return 0;

	}

	//값 계산
	init();
	stack<int> st;
	st.push(str[0]);
	int score = 0;
	for (int i = 1; i < str.size(); i++) {

		if (st.empty()) {
			score += nest[1];
			init();
		}
		//여는 괄호
		if (str[i] == '(' || str[i] == '[') {
			st.push(str[i]);
			continue;
		}

		//닫힌 괄호
		if (nest[st.size() + 1] != 0) {
			if (str[i] == ')') {
				nest[st.size()] += nest[st.size() + 1] * 2;
			}
			else if(str[i] == ']') {
				nest[st.size()] += nest[st.size() + 1] * 3;
			}
			nest[st.size() + 1] = 0;
		}
		else {
			if (str[i] == ')') {
				nest[st.size()] += 2;
			}
			else if(str[i] == ']') {
				nest[st.size()] += 3;
			}
		}
		st.pop();
	}

	score += nest[1];
	cout << score;


	return 0;
}

