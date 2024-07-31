#include <iostream>
#include <vector>
#include <string>

using namespace std;

string str;
vector<char> sound;
char quack[5] = { 'q', 'u', 'a', 'c', 'k' };

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> str;

	int answer = 0, cnt = 0;

	for (int i = 0; i < str.length(); i++) {

		if (sound.size() == 0 && str[i] != 'q') {
			cout << -1;
			return 0;
		}

		int alph; //현재 알파벳의 인덱스
		for (int j = 0; j < 5; j++) {
			if (str[i] == quack[j]) {
				alph = j;
				break;
			}
		}

		if (alph == 0) { //q
			sound.push_back('q');
			cnt++;
			answer = max(answer, cnt);
		}
		else {
			for (int j = 0; j < sound.size(); j++) {
				if (sound[j] == quack[alph - 1]) {
					sound.erase(sound.begin() + j);
					if (alph == 4)
						cnt--;
					else
						sound.push_back(quack[alph]);
					break;
				}
				if (j == sound.size() - 1) {
					cout << -1;
					return 0;
				}
			}

		}
	}

	if (!sound.empty())
		cout << -1;
	else
		cout << answer;

	return 0;


}