#include <string>
#include <vector>
#include <utility>
#include <queue>

using namespace std;

int solution(int n, vector<vector<int>> computers) {
    //bfs
    //하나의 네트워크부터 시작 -> 자신 노드 x, 이미 지나간 노드 x, 거르고 q 삽입
    //q 삽입하면 방문 노드 표시
    //q 비면 net++;
    //방문 표시 안 된 노드에서 bfs 다시 시작
    int net = 0;
    int vis[201] = {0, }; //방문된 노드 1
    queue<int> q; //방문할 노드 저장
    int cur = 0; //현재 탐색 중인 노드
    
    q.push(0);
    vis[0] = 1;
    
    
    bool end = false;
    while(!end){
        
        if(q.empty()){
            net++;
            for(int i = 0; i < computers.size(); i++){
                if(vis[i] == 0){
                    q.push(i);
                    vis[i] = 1;
                    break;
                }
                else if(i == computers.size() - 1){
                    end = true;
                }
            }
        }
        else{
            cur = q.front();
            q.pop();
            
            for(int i = 0; i < computers.size(); i++){
                if(i != cur && vis[i] != 1 && computers[cur][i] == 1){
                    q.push(i);
                    vis[i] = 1;
                }
            }
        }
    }
    
    
    
    return net;
}