#include <iostream>
#include <vector>

using namespace std;

int tc, d, w, k;
int board[14][21];
int modify[14][21];
int status[3] = { 0, 1, -1 }; //A B X
int output[14];
int answer = 14;


bool check(int board[14][21]) {

	for (int i = 0; i < w; i++) {
		int cnt = 1;
		int mx = 0;
		for (int j = 1; j < d; j++) {
			if (board[j - 1][i] == board[j][i])
				cnt++;
			else {
				if (cnt > mx)
					mx = cnt;
				cnt = 1;
			}
		}

		if (cnt > mx)
			mx = cnt;

		if (mx < k) {
			return false;
		}
	}
	return true;
}


void permu(int cur, int chem) {

	//수열 완성된 경우
	if (cur == d) {
		//보호 필름 수열 따라 약물 시행
		for (int i = 0; i < d; i++) {
			if (output[i] != -1) {
				for (int j = 0; j < w; j++) {
					modify[i][j] = output[i];
				}
			}
			else {
				for (int j = 0; j < w; j++) {
					modify[i][j] = board[i][j];
				}
			}
		}

		//해당 필름이 기준 합격인지 판단
		if (check(modify)) {
			if (answer > chem)
				answer = chem;
		}
		return;
	}

	//수열 만들기
	for (int i = 0; i < 3; i++) {
		output[cur] = status[i];
		if (output[cur] != -1) { //약품 주입한 경우
			if(answer > chem + 1) //가지치기
				permu(cur + 1, chem + 1);
		}
		else { //약품 주입하지 않은 경우
			permu(cur + 1, chem);
		}
	}
}


int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> tc;


	for (int t = 1; t <= tc; t++) {

		//get input
		cin >> d >> w >> k;
		for (int i = 0; i < d; i++) {
			for (int j = 0; j < w; j++) {
				cin >> board[i][j];
			}
		}
		answer = d;

		if (check(board)) {

			cout << "#" << t << " " << 0 << '\n';

		}
		else {
			//make permutation
			permu(0, 0);

			cout << "#" << t << " " << answer << '\n';
		}
	}

	return 0;
}