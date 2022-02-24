// https://atcoder.jp/contests/abc240/tasks/abc240_h

#include "cstring"
#include "vector"
#include "cmath"
#include "iostream"

using namespace std;

struct Node {
	vector<int> idx;
	Node *next[2];
	Node() {
		next[0] = nullptr;
		next[1] = nullptr;
	}
} *root = new Node();

int N, *counter, *dp;

int query(int idx) {	// 树状数组保存截止idx的最大子序列数量
	int temp = 0;
	for (int i = idx; i; i ^= i & -i) {
		temp = max(temp, counter[i]);
	}
	return temp;
}

void change(int idx, int num) {
	for (int i = idx; i <= N; i += i & -i) {
		counter[i] = max(counter[i], num);
	}
}

void dfs(Node *node, int length) {	// 前序遍历, 遍历节点字典序递增
	if (node == nullptr) {
		return;
	}
	for (int i = (int) node->idx.size() - 1; i >= 0; i--) {
		int &idx = node->idx[i];
		int temp = query(idx - length) + 1;
		if (temp > dp[idx]) {
			dp[idx] = temp;
			change(idx, temp);
		}
	}
	dfs(node->next[0], length + 1);
	dfs(node->next[1], length + 1);
}

int main() {	// 树状DP
	scanf("%d", &N);
	char container[N + 2];
	scanf("%s", container + 1);
	int maxLength = (int) sqrt(2 * N + 0.1), ans = 0;
	counter = new int[N + 1], dp = new int[N + 1];
	memset(counter, 0, sizeof(int) * (N + 1));
	memset(dp, 0, sizeof(int) * (N + 1));
	for (int start = 1; start <= N; start++) {
		Node *node = root;
		for (int length = 1, limit = min(N - start + 1, maxLength); length <= limit; length++) {
			int idx = container[start + length - 1] - '0';
			if (node->next[idx] == nullptr) {
				node->next[idx] = new Node();
			}
			node = node->next[idx];
			node->idx.push_back(start + length - 1);
		}
	}
	dfs(root, 0);
	for (int i = 1; i <= N; i++) {
		ans = max(ans, dp[i]);
	}
	printf("%d", ans);
	return 0;
}
