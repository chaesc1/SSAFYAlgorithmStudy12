#include <iostream>
#include <vector>
#include <cmath>

using namespace std;

int tc, n;
int input[9];
int visit[9];
int answer;
int sum;


int factorial(int k) {
	int val = 1;
	for (int i = k; k > 1; k --) {
		val *= k;
	}
	return val;
}

void permu(int order, int lw, int rw) {

	if (order == n) {
		answer++;
		return;
	}

	//이미 왼쪽에 올려진 무게가 남아있는 추의 무게 합보다 큰 경우
	//남겨진 추들은 양쪽 모두 선택 가능
	//재귀 탐색하지 않아도 경우의 수 파악 가능 
	//!(n - cnt) * pow(2, n-cnt)
	if (lw >= sum - lw) { 
		answer += (factorial(n - order) * pow(2, n - order));
		return;
	}


	for (int i = 0; i < n; i++) {
		if (!visit[i]) {
			//output[order] = input[i];
			visit[i] = true;
			if (rw + input[i] <= lw) {
				permu(order + 1, lw, rw + input[i]);
			}
			permu(order + 1, lw + input[i], rw);
			visit[i] = false;
		}
	}


}


int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> tc;
	for (int t = 1; t <= tc; t++) {
		answer = 0; sum = 0;

		cin >> n;
		for (int i = 0; i < n; i++) {
			cin >> input[i];
			sum += input[i];
		}

		//make permutation
		permu(0, 0, 0);

		cout << "#" << t << " " << answer << '\n';
	}

	return 0;
}