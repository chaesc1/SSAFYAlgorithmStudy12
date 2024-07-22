#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main(void) {

    //greedy algorithm

    int n;
    vector<int> v;

    cin >> n;
    for (int i = 0; i < n; i++) {
        int tmp;
        cin >> tmp;
        v.push_back(tmp);
    }

    sort(v.begin(), v.end());

    int ans = v[0];

    for (int i = 1; i < n; i++) {
        v[i] = v[i - 1] + v[i];
        ans += v[i];
    }

    cout << ans << endl;

    return 0;
}