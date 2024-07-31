#include <iostream>
#include <vector>
#include <stack>
#include <string>

using namespace std;


string solution(int n) {
	stack<char> st;

	while (true) {
		if (n == 1) {
			st.push('1');
			break;
		}
		st.push('0' + n % 2);
		n = n/2;
	}
	
	string str = "";
	while (!st.empty()) {
		str += st.top();
		st.pop();
	}
	return str;

}

int main() {
	
	cout << solution(10) << '\n';
	cout << solution(27) << '\n';
	cout << solution(12345) << '\n';


	return 0;
}