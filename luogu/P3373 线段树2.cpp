// https://www.luogu.com.cn/problem/P3373

#include "cstring"
#include "iostream"

int p, *nums, *lazyAdd, *lazyMul;

void push(int &node, int &start, int &end) {
	if (lazyMul[node] == 1 && lazyAdd[node] == 0) {
		return;
	}
	int mid = (start + end) >> 1;
	int nextNode1 = node << 1, nextNode2 = nextNode1 + 1;
	if (lazyMul[node] != 1) {
		nums[nextNode1] = (long long) nums[nextNode1] * lazyMul[node] % p;
		nums[nextNode2] = (long long) nums[nextNode2] * lazyMul[node] % p;
		lazyMul[nextNode1] = (long long) lazyMul[nextNode1] * lazyMul[node] % p;
		lazyMul[nextNode2] = (long long) lazyMul[nextNode2] * lazyMul[node] % p;
	}
	if (lazyAdd[node]) {
		nums[nextNode1] = (nums[nextNode1] + (long long) (mid - start + 1) * lazyAdd[node]) % p;
		nums[nextNode2] = (nums[nextNode2] + (long long) (end - mid) * lazyAdd[node]) % p;
	}
	lazyAdd[nextNode1] = ((long long) lazyAdd[nextNode1] * lazyMul[node] + lazyAdd[node]) % p;
	lazyAdd[nextNode2] = ((long long) lazyAdd[nextNode2] * lazyMul[node] + lazyAdd[node]) % p;
	lazyMul[node] = 1;
	lazyAdd[node] = 0;
}

void add(int node, int start, int end, int left, int right, int delta) {
	if (start > right || end < left) {
		return;
	}
	if (start >= left && end <= right) {
		nums[node] = (nums[node] + (long long) (end - start + 1) * delta) % p;
		lazyAdd[node] = (lazyAdd[node] + delta) % p;
		return;
	}
	push(node, start, end);
	int mid = (start + end) >> 1;
	int nextNode1 = node << 1, nextNode2 = nextNode1 + 1;
	add(nextNode1, start, mid, left, right, delta);
	add(nextNode2, mid + 1, end, left, right, delta);
	nums[node] = (nums[nextNode1] + nums[nextNode2]) % p;
}

void mul(int node, int start, int end, int left, int right, int k) {
	if (start > right || end < left) {
		return;
	}
	if (start >= left && end <= right) {
		nums[node] = (long long) nums[node] * k % p;
		lazyMul[node] = (long long) lazyMul[node] * k % p;
		lazyAdd[node] = (long long) lazyAdd[node] * k % p;
		return;
	}
	push(node, start, end);
	int mid = (start + end) >> 1;
	int nextNode1 = node << 1, nextNode2 = nextNode1 + 1;
	mul(nextNode1, start, mid, left, right, k);
	mul(nextNode2, mid + 1, end, left, right, k);
	nums[node] = (nums[nextNode1] + nums[nextNode2]) % p;
}

int query(int node, int start, int end, int left, int right) {
	if (start > right || end < left) {
		return 0;
	}
	if (start >= left && end <= right) {
		return nums[node];
	}
	push(node, start, end);
	int mid = (start + end) >> 1;
	int nextNode1 = node << 1, nextNode2 = nextNode1 + 1;
	return (query(nextNode1, start, mid, left, right) +
	        query(nextNode2, mid + 1, end, left, right)) % p;
}

int main() {
	int n, m;
	scanf("%d %d %d", &n, &m, &p);
	nums = new int[n << 2];
	lazyAdd = new int[n << 2];
	lazyMul = new int[n << 2];
	memset(nums, 0, sizeof(int) * (n << 2));
	memset(lazyAdd, 0, sizeof(int) * (n << 2));
	for (int i = 1, limit = n << 2; i < limit; i++) {
		lazyMul[i] = 1;
	}
	for (int i = 1; i <= n; i++) {
		long long num;
		scanf("%lld", &num);
		add(1, 1, n, i, i, (int) (num % p));
	}
	for (int i = 0; i < m; i++) {
		int op, x, y;
		scanf("%d", &op);
		if (op == 3) {
			scanf("%d %d", &x, &y);
			printf("%d\n", query(1, 1, n, x, y));
			continue;
		}
		long long k;
		scanf("%d %d %lld", &x, &y, &k);
		int kmod = k % p;
		if (op == 1) {
			mul(1, 1, n, x, y, kmod);
		} else {
			add(1, 1, n, x, y, kmod);
		}
	}
	return 0;
}
