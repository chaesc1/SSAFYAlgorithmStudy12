#include <iostream>
#include <vector>
#include <queue>
#include <cmath>

using namespace std;

struct battery {
	int posx;
	int posy;
	int range;
	int value;
	battery(int t_posx, int t_posy, int t_range, int t_value) : posx(t_posx), posy(t_posy), range(t_range), value(t_value) {};
};

struct user {
	int x;
	int y;
	user(int t_x, int t_y) : x(t_x), y(t_y) {};
};

struct compare {
	bool operator()(pair<int, int> a, pair<int, int> b) {
		return a.second < b.second;
	}
};

int tc, answer;
int m, a;
int m1[101], m2[101];
vector<battery> bc;
priority_queue<pair<int, int>, vector<pair<int, int>>, compare> pq1, pq2; //idx, value
int dx[5] = { 0, -1, 0, 1, 0 }; // x, ╩С, ©Л, го, аб
int dy[5] = { 0, 0, 1, 0, -1 };


int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> tc;

	for (int t = 1; t <= tc; t++) {

		//initialization
		user u1(1, 1);
		user u2(10, 10);
		answer = 0;

		cin >> m >> a;
		bc.clear();

		//make user1 move
		m1[0] = 0;
		for (int i = 1; i <= m; i++) {
			cin >> m1[i];
		}

		//make user2 move
		m2[0] = 0;
		for (int i = 1; i <= m; i++) {
			cin >> m2[i];
		}

		//make bettery info
		for (int i = 0; i < a; i++) {
			int t_x, t_y, t_ran, t_val;
			cin >> t_y >> t_x >> t_ran >> t_val;
			bc.push_back(battery(t_x, t_y, t_ran, t_val));
		}


		//time pass
		for (int move = 0; move <= m; move++) {
			u1.x += dx[m1[move]];
			u1.y += dy[m1[move]];
			u2.x += dx[m2[move]];
			u2.y += dy[m2[move]];

			//calculate dist bc ~ user 
			//update queue
			for (int i = 0; i < a; i++) {
				int dist1 = abs(bc[i].posx - u1.x) + abs(bc[i].posy - u1.y);
				if (dist1 <= bc[i].range)
					pq1.push({ i, bc[i].value });
				int dist2 = abs(bc[i].posx - u2.x) + abs(bc[i].posy - u2.y);
				if (dist2 <= bc[i].range)
					pq2.push({ i, bc[i].value });
			}


			//calculate the sum of charging value
			int char1, char2;
			//if two are empty
			if (pq1.empty() && pq2.empty()) {
				char1 = 0; char2 = 0;
			}
			//if one of them is empty
			else if (pq1.empty() && !pq2.empty()) {
				char1 = 0;
				char2 =  pq2.top().second;
			}
			else if (!pq1.empty() && pq2.empty()) {
				char1 = pq1.top().second;
				char2 = 0;
			}
			//both are not empty
			else {
				//if the top value is diff
				if (pq1.top().first != pq2.top().first) {
					char1 = pq1.top().second;
					char2 = pq2.top().second;
				}
				//if the top value is equal
				else {
					//if two length is 1
					if (pq1.size() == 1 && pq2.size() == 1) {
						char1 = pq1.top().second / 2;
						char2 = pq2.top().second / 2;
					}
					//one of them length is bigger than 1
					else if (pq1.size() == 1 && pq2.size() != 1) {
						pq2.pop();
						char1 = pq1.top().second;
						char2 = pq2.top().second;
					}
					else if (pq1.size() != 1 && pq2.size() == 1) {
						pq1.pop();
						char1 = pq1.top().second;
						char2 = pq2.top().second;
					}
					//both of them length is bigger than 1
					else {
						int tmp = pq1.top().second;
						pq1.pop(); pq2.pop();
						if (pq1.top().second >= pq2.top().second) {
							char1 = pq1.top().second;
							char2 = tmp;
						}
						else {
							char1 = tmp;
							char2 = pq2.top().second;
						}
					}
				}
				
			}

			//cout << "user1 " << char1 << ", user2 " << char2 << '\n';
			answer = answer + char1 + char2;

			while (!pq1.empty()) {
				pq1.pop();
			}
			while (!pq2.empty()) {
				pq2.pop();
			}
		}





		cout << "#" << t << " " << answer << '\n';

	}

	return 0;
}