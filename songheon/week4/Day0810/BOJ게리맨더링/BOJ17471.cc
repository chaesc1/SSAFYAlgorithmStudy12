#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

const int MAX = 11;

int people[MAX];
vector<int>node[MAX];
bool selected[MAX];
bool visit[MAX];

int n;

int ans = 987654321;


bool isConnected(vector<int>& v, bool flag) {
    for (int i = 1; i <= n; i++) {
        visit[i] = false;
    }
    queue<int> q;
    visit[v[0]] = true;
    q.push(v[0]);
    int cnt = 1;

    while (!q.empty()) {
        int x = q.front();
        q.pop();

        for (auto value : node[x]) { // 노드안에있는 값 다 꺼내서 탐색할 곳 큐에 넣어주기

            if (selected[value] != flag || visit[value]) continue;
            cnt++;
            visit[value] = true;	// 이미 갔던 노드는 못가도록 check표시
            q.push(value);
        }
    }

    if (v.size() == cnt) return true; // 탐색한 노드 개수랑, 구역의 노드 개수가 같다면 모두다 연결되어있다는 의미
    else return false;
}

bool isOk() {
    vector<int> a;
    vector<int> b;

    for (int i = 1; i <= n; i++) {
        if (selected[i]) a.push_back(i);
        else b.push_back(i);
    }

    if (a.empty() || b.empty()) return false; // 둘 중 하나라도 비어있다면, 노드의 개수가 0이라는 의미

    if (!isConnected(a, true)) return false; // check표시한 구역이 연결되어있는지 검사 flag = true

    if (!isConnected(b, false)) return false; // check표시가 안되어있는 구역이 연결되어있는지 검사 flag = false

    return true;


}
void calcu() {
    int a_sum = 0;
    int b_sum = 0;

    for (int i = 1; i <= n; i++) {
        if (selected[i]) a_sum += people[i]; // 선택된 구역
        else b_sum += people[i]; 			// 선택이 안된 구역으로 나누어준다.
    }

    ans = min(ans, abs(a_sum - b_sum));


}

void dfs(int start, int depth) {

    if (depth >= 1) { // 1개 이상이어도 시뮬레이션을 돌려본다. 
        if (isOk()) {
            calcu();
        }
    }


    for (int i = start; i <= n; i++) {
        if (selected[i]) continue;
        selected[i] = true;
        dfs(i + 1, depth + 1);
        selected[i] = false;
    }
}

void solution() {
    cin >> n;

    for (int i = 1; i <= n; i++) {
        cin >> people[i];
    }
    for (int i = 1; i <= n; i++) {
        int cnt;
        cin >> cnt;
        for (int j = 0; j < cnt; j++) {
            int tmp;
            cin >> tmp;
            node[i].push_back(tmp); // 노드 양방향으로 연결시켜주기
            node[tmp].push_back(i); // 노드 양방향으로 연결시켜주기
        }
    }

    dfs(1, 0); // DFS 조합 만들기




    if (ans == 987654321) cout << -1 << '\n';
    else cout << ans << '\n';
}
int main() {

    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    solution();

    return 0;
}