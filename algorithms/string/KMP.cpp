#include "cstring"
#include "iostream"

int *next;
char word1[1000001], word2[1000001];

int main() {	// 洛谷P3375测试通过
	scanf("%s", word1);
	scanf("%s", word2);
	int length1 = strlen(word1), length2 = strlen(word2);
	next = new int[length2];
	next[0] = 0;
	// i, j为后缀和前缀待匹配位置
	for (int i = 1, j = 0; i < length2; i++) {
		// 不匹配则前缀指针退回匹配区域(j - 1)的前缀位置next[j - 1]
		while (j && word2[i] != word2[j]) {
			j = next[j - 1];
		}
		next[i] = word2[i] == word2[j] ? ++j : 0;
	}
	if (length1 >= length2) {
		for (int i = 0, j = 0; i < length1; i++) {
			while (j && word1[i] != word2[j]) {
				j = next[j - 1];
			}
			if (word1[i] == word2[j]) {
				if (++j == length2) {
					printf("%d\n", i - length2 + 2);
					j = next[length2 - 1];
				}
			}
		}
	}
	for (int i = 0; i < length2; i++) {
		printf("%d ", next[i]);
	}
	return 0;
}