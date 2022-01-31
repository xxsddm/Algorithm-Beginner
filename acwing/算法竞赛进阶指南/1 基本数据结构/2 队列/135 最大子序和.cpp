// https://www.acwing.com/problem/content/137/

#include "queue"
#include "iostream"

using namespace std;

int main() {
	int n, m;
	scanf("%d %d", &n, &m);
	int start = 0, pre = 0, ans = 0x80000000;
	int length = m >= n ? 1 : m, container[length];	// 备份前序元素
	queue<int> nums;
	for (int end = 0; end < n; end++) {	// 考虑以当前end元素为末端的满足要求最大子序列和
		if (end - start == m) {
			nums.pop();
			int subprev = 0, idx = 0;
			while (!nums.empty()) {
				int temp = nums.front();
				nums.pop();
				subprev += temp;
				if (subprev <= 0) {
					subprev = 0;
					idx = 0;
				} else {
					container[idx++] = temp;
				}
			}
			pre = subprev;
			start = end - idx;
			for (int i = 0; i < idx; i++) {
				nums.push(container[i]);
			}
		}
		int num;
		scanf("%d", &num);
		nums.push(num);
		pre += num;
		ans = max(ans, pre);
		if (pre <= 0) {
			pre = 0;
			start = end + 1;
			nums = queue<int> ();
		}
	}
	printf("%d", ans);
	return 0;
}
