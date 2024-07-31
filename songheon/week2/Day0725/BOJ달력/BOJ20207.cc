#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int n; //작업 개수
vector<pair<int, int>> works;
int start, des; //연속 작업 시작, 종료 시간
int h; //달력의 두께
vector<int> et; //현재 진행 작업들의 종료 시간
int answer;


int compare(pair<int, int> a, pair<int, int> b) {
	if (a.first == b.first) {
		return b.second < a.second;
	}
	return a.first < b.first;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	//작업 업데이트
	cin >> n;
	for (int i = 0; i < n; i++) {
		int a, b;
		cin >> a >> b;
		works.push_back({ a, b });
	}

	sort(works.begin(), works.end(), compare);
	
	//초기 상태 (첫 작업 시작)
	et.push_back(works[0].second);
	start = works[0].first;
	des = works[0].second;
	h = 1;

	for (int i = 1; i < n; i++) {
		//종료시간 저장한 벡터 업데이트
		if (!et.empty()) {
			sort(et.begin(), et.end());
			for (int t = et.size() - 1; t >= 0; t--) {
				if (et[t] < works[i].first) {
					et.erase(et.begin() + t);
				}
			}
		}

		if (et.empty()) {
			//연속 작업 x : 코팅지 붙이기
			if (works[i].first - des > 1) {
				answer += (des - start + 1) * h;
				start = works[i].first;
				des = 0;
				h = 0;
			}

		}

		//작업
		et.push_back(works[i].second);
		des = max(des, works[i].second);
		h = max(h, (int)et.size());


	}

	answer += (des - start + 1) * h;

	cout << answer;


	return 0;
}