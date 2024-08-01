#include <iostream>
#include <vector>
#include <stack>

using namespace std;


int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int n;
	cin >> n;

	stack<int> st; //만들어진 수열 저장
	vector<char> ans; //정답 저장 벡터
	st.push(0);
	int num = 1; //현재 스택에 넣을 수


	for (int i = 0; i < n; i++) {
		int seq; //현재 만족해야 할 수
		cin >> seq;

		//sequence의 수가 stack.top()보다 크거나 같은 경우
		if (st.top() <= seq) {
			//sequence 수가 될 때까지 stack에 push
			while (seq != st.top()) {
				st.push(num);
				num++;
				ans.push_back('+');
			}
			//seq == stack.top() 만족한다면 stack에서 pop
			st.pop();
			ans.push_back('-');
		}
		//sequence의 수가 stack.top()보다 작은 경우
		//아직 만족하지 못한 수가 버려져야 하므로 수열을 완성할 수 없음
		else { //seq < st.top()
			cout << "NO";
			return 0;
		}
		
	}

	for (char c : ans) {
		cout << c << '\n';
	}
	return 0;
}