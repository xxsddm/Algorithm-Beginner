// http://oj.daimayuan.top/course/11/problem/855

#include "cstring"
#include "iostream"

int main() {
	int n, length = 60, mod = 1e9 + 7;
	scanf("%d", &n);
	int limit = 0, counter[length];
	memset(counter, 0, sizeof(counter));
	for (int i = 0; i < n; i++) {
		long long num;
		scanf("%lld", &num);
		int j = 0;
		while (num) {
			if (num & 1) {
				counter[j]++;	// 记录各位置取1次数
			}
			num >>= 1;
			j++;
		}
		if (j > limit) {
			limit = j;
		}
	}
	int ans = 0;
	long long temp = 1;
	for (int i = 0; i < limit; i++, temp <<= 1) {
		if (temp >= mod) {
			temp -= mod;
		}
		if (counter[i] && counter[i] != n) {
			ans = ans + (temp * ((long long) counter[i] * (n - counter[i]) % mod)) % mod;
			if (ans >= mod) {
				ans -= mod;
			}
		}
	}
	printf("%d", ans);
	return 0;
}
