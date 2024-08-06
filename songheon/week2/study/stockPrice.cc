#include <string>
#include <vector>
#include <queue>

using namespace std;

vector<int> solution(vector<int> prices) {
    vector<int> answer;
    
    for(int i = 0; i < prices.size(); i++){
        queue<int> q;
        for(int j = i; j < prices.size(); j++){
            if(q.empty() || q.front() <= prices[j])
                q.push(prices[j]);
            else{
                answer.push_back(q.size());
                break;
            }
            if(j == prices.size()-1)
            answer.push_back(q.size()-1);
        }
        
    }
    return answer;
}