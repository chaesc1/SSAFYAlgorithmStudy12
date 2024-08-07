#include <iostream>
#include <vector>

using namespace std;

int n;
int cnt;
int board[16];

bool possible(int cur, int tmp, int board[]) {
	for (int i = 0; i < cur; i++) {
		if (tmp == board[i] 
			|| abs(cur - i) == abs(tmp - board[i])) { //같은 열에 있거나 대각선에 있는 경우
			return false;
		}
	}
	return true;
}

void nqueen(int cur, int board[]) {
	if (cur == n) { //모든 행의 퀸을 다 채운 경우
		cnt++;
	}
	else {
		for (int i = 0; i < n; i++) { //현재 행에서 퀸을 둘 수 있는 위치 탐색
			if (possible(cur, i, board)) { //해당 위치가 공격받지 않는지 확인
				board[cur] = i; //가능한 자리에 퀸 위치
				nqueen(cur + 1, board); //다음 행에서 같은 문제 시행
			}
		}
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n;

	nqueen(0, board); //현재 퀸을 채울 행, 퀸의 위치 정보 저장하는 맵
	
	cout << cnt;

	return 0;
}