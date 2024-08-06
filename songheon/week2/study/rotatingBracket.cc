#include <string>
#include <vector>
#include <stack>

using namespace std;

int solution(string s) {
    
    int answer = 0;
    for(int i = 0; i < s.size(); i++){
        string str;
        int iter = i;
        while(str.size() < s.size()){
            str += s[iter % s.size()];
            iter++;
        }
        
        stack<char> st;
        for(int j = 0; j < str.size(); j++){
            if(str[j] == '(' || str[j] == '{' || str[j] == '[')
                st.push(str[j]);
            else{
                if(st.empty())
                    break;
                if(str[j] == ')'){
                    if(st.top() == '('){
                        st.pop();
                    }
                    else
                        break;
                }
                else if(str[j] == '}'){
                    if(st.top() == '{'){
                        st.pop();
                    }
                    else
                        break;
                }
                else if(str[j] == ']'){
                    if(st.top() == '['){
                        st.pop();
                    }
                    else
                        break;
                }
                
                if(j == str.size()-1){
                    if(st.empty())
                        answer++;
                }
            }
        }  
        
    }   
    
    return answer;
}