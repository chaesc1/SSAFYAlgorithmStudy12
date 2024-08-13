#include <vector>
#include <queue>

using namespace std;

bool OOB(int x, int y, int n, int m){
    if(x >= 0 && x < n){
        if(y >= 0 && y < m){
            return false;
        }
    }
    return true;
}

int solution(vector<vector<int> > maps)
{ 
    
    int dx[4] = {0, 1, 0, -1};
    int dy[4] = {1, 0, -1, 0};  
    
    //행n,x 열m,y
    int n = maps.size();
    int m = maps[0].size();
    int dist[101][101];

    queue<pair<int, int>> q;
    q.push({0, 0});
    maps[0][0] = 0;
    dist[0][0] = 1;
    
    while(!q.empty()){
        pair<int, int> cur = q.front();
        q.pop();
        
        for(int i = 0; i < 4; i++){
            int movex = cur.first + dx[i];
            int movey = cur.second + dy[i];
            if(!OOB(movex, movey, n, m) && maps[movex][movey] != 0){
                q.push({movex, movey});
                maps[movex][movey] = 0;
                dist[movex][movey] = dist[cur.first][cur.second] + 1;
            }
        }
        
    }
    
    if(dist[n-1][m-1] == 0)
        return -1;
    else
        return dist[n-1][m-1];
    
    
}