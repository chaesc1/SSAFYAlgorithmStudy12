#include <iostream>
#include <vector>
#include <stack>
#include <string>

using namespace std;


int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	
	string pos;
	cin >> pos;
	char pre = '.'; //이전 괄호
	char cur = '.'; //현재 괄호
	int cnt = 0; //현재 막대기 개수
	int answer = 0; //총 막대기 개수

	for (int i = 0; i < pos.size(); i++) {
		cur = pos[i];
		if (cur == '(') {
			cnt++;
		}
		else if (cur == ')') {
			cnt--;
			if (pre == '(') {
				answer += cnt;
			}
			else if (pre == ')') {
				answer++;
			}
		}
		pre = pos[i];
	}

	cout << answer;

	return 0;
}