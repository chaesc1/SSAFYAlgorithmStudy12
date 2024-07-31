#include<string>
#include <iostream>
#include <stack>

using namespace std;

bool solution(string s)
{
    stack<char> stack;
    bool answer = true;

    for(char c : s){
        if(c == '(')
            stack.push(c);
        else{
            if(stack.empty())
                return false;
            else
                stack.pop();
        }
    }
    if(stack.empty())
        return true;
    else
        return false;
    

    return answer;
}