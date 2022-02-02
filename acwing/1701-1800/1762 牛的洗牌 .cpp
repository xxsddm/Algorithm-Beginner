// https://www.acwing.com/problem/content/description/1764/

#include "iostream"

using namespace std;

int N;

void move(int *a, int *nums, int *aux) {
	for (int i = 1; i <= N; i++) {
		aux[i] = nums[i];
	}
	for (int i = 1; i <= N; i++) {
		nums[i] = aux[a[i]];
	}
}

int main() {
	scanf("%d", &N);
	int a[N + 1], nums[N + 1], aux[N + 1];
	for (int i = 1; i <= N; i++) {
		scanf("%d", &a[i]);
	}
	for (int i = 1; i <= N; i++) {
		scanf("%d", &nums[i]);
	}
	for (int i = 0; i < 3; i++) {
		move(a, nums, aux);
	}
	for (int i = 1; i <= N; i++) {
		printf("%d\n", nums[i]);
	}
	return 0;
}
