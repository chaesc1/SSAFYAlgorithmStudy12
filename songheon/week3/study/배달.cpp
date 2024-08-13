#include <iostream>
#include <vector>
#include <queue>

using namespace std;

vector<pair<int, int>> adj[51];
int dist[51];

void dijkstra(int k){
    queue<pair<int, int>> q;
    q.push({1, 0});
    dist[1] = 0;
    
    while(!q.empty()){
        int cur = q.front().first;
        int curdist = q.front().second;
        q.pop();
        
        for(int i = 0; i < adj[cur].size(); i++){
            int next = adj[cur][i].first;
            int nextdist = adj[cur][i].second;
            
            if(curdist + nextdist < dist[next] && 
               curdist + nextdist <= k){
                dist[next] = curdist + nextdist;
                q.push({next, curdist + nextdist});
            }
                
        }
    }
    
}

int solution(int N, vector<vector<int>> road, int K) {
    
    for(int i = 1; i <= N; i++){
        dist[i] = 2e9;
    }
    
    for(int i = 0; i < road.size(); i++){
        int start = road[i][0];
        int end = road[i][1];
        int dist = road[i][2];
        
        adj[start].push_back({end, dist});
        adj[end].push_back({start, dist});
    }
    
    
    int answer = 0;
    dijkstra(K);
    for(int i = 1; i <= N; i++){
        if (dist[i] <= K)
            answer++;
    }
    return answer;
    
    
    
 }