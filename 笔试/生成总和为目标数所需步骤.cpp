#include <iostream>
#include "vector"
#include "queue"

using namespace std;

int countAdd(int target) {  // 每步+1或+n(n为当前步数)
    if (target == 0) {
        return 0;
    }
    queue<int> container;
    vector<bool> visited(target + 1);
    container.push(0);
    visited[0] = true;
    int count = 1;
    while (true) {
        int size = (int) container.size();
        for (int i = 0; i < size; i++) {
            int num = container.front();
            int nextNum1 = num + 1, nextNum2 = num + count;
            container.pop();
            if (nextNum1 == target || nextNum2 == target) {
                return count;
            }
            if (nextNum1 < target && !visited[nextNum1]) {
                visited[nextNum1] = true;
                container.push(nextNum1);
            }
            if (nextNum2 < target && !visited[nextNum2]) {
                visited[nextNum2] = true;
                container.push(nextNum2);
            }
        }
        count++;
    }
}

int main() {
    cout << countAdd(100000) << endl;
}

