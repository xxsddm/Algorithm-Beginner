#include <iostream>
#include "vector"
#include "list"

using namespace std;

int findNums(vector<int>& nums, int limit) {
    if (limit <= 0 || nums.empty()) {
        return 0;
    }
    int count = 0, slow = 0, fast = 1, length = (int) nums.size();
    list<int> maxcontainer, mincontainer;   // maxcontainer从大到小,mincontainer从小到大(记录值)
    maxcontainer.push_back(nums[0]);
    mincontainer.push_back(nums[0]);
    while (fast < length) {
        // 加入fast(加入后差值小于limit则继续添加)
        // 不可继续加入时最新引进的是fast-1
        while (fast < length && maxcontainer.front() - mincontainer.front() < limit) {
            // [slow,fast-1]可行,则以fast-1结尾的子数组数量(slow<=fast-1恒成立)
            count += fast - slow;
            int temp = nums[fast];
            while (!maxcontainer.empty() && temp > maxcontainer.back()) {
                maxcontainer.pop_back();
            }
            while (!mincontainer.empty() && temp < mincontainer.back()) {
                mincontainer.pop_back();
            }
            maxcontainer.push_back(temp);
            mincontainer.push_back(temp);
            fast++;
        }
        // slow<fast-1时maxcontainer和mincontainer不为空,且[slow,fast-1]差值不小于limit,移除slow
        while (slow < fast - 1 && maxcontainer.front() - mincontainer.front() >= limit) {
            if (maxcontainer.front() == nums[slow]) {
                maxcontainer.pop_front();
            }
            if (mincontainer.front() == nums[slow]) {
                mincontainer.pop_front();
            }
            slow++;
        }
    }
    // fast到达末端时单独考虑
    count += fast - slow;
    return count;
}


int main() {
    vector<int> input = {3,9,7,10,11,12,6,4,3,5};
    cout << findNums(input, 3) << endl;
}
