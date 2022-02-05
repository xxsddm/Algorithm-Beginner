// https://www.luogu.com.cn/problem/P5357

#include "cstring"
#include "queue"
#include "iostream"

using namespace std;

class ACautomaton {
private:
	struct Node {
		int count = 0, length = 0, indegree = 0;
		vector<int> idxs;
		Node *fail, *next[26];
		Node() {
			for (int i = 0; i < 26; i++) {
				next[i] = nullptr;
			}
		}
	};

	Node *root = new Node(), *dummyhead = new Node();

public:
	ACautomaton() {
		root->fail = dummyhead;
		for (int i = 0; i < 26; i++) {
			dummyhead->next[i] = root;
		}
	}

	vector<Node*> nodes;

	void add(string &word, int newIdx) {
		int length = (int) word.size();
		Node *node = root;
		for (int i = 0; i < length; i++) {
			int idx = word[i] - 'a';
			if (node->next[idx] == nullptr) {
				node->next[idx] = new Node();
				nodes.push_back(node->next[idx]);
				node->next[idx]->length = node->length + 1;
			}
			node = node->next[idx];
		}
		node->idxs.push_back(newIdx);
	}

	void preprocess() {     // BFS(按照字符串长度遍历, 计算fail)
		queue<Node*> q;
		q.push(root);
		while (!q.empty()) {
			Node *node = q.front(), *prevFail = node->fail;
			q.pop();
			for (int i = 0; i < 26; i++) {
				if (node->next[i] == nullptr) {
					node->next[i] = prevFail->next[i];
					continue;
				}
				node->next[i]->fail = prevFail->next[i];
				q.push(node->next[i]);
			}
		}
	}

	void calculate(string &word) {
		int length = (int) word.size();
		Node *node = root;
		for (int i = 0; i < length; i++) {
			node = node->next[word[i] - 'a'];
			node->count++;
		}
	}

	void count(int *counter) {	// BFS(利用拓扑关系计算各字符串出现次数)
		queue<Node*> q;
		for (auto & node : nodes) {
			node->fail->indegree++;
		}
		for (auto & node : nodes) {
			if (node->indegree == 0) {
				q.push(node);
			}
		}
		while (!q.empty()) {
			Node *node = q.front();
			q.pop();
			for (int &idx : node->idxs) {
				counter[idx] = node->count;
			}
			node->fail->count += node->count;
			if (--node->fail->indegree == 0) {
				q.push(node->fail);
			}
		}
	}
};

int main() {
	int n;
	ACautomaton ac;
	scanf("%d", &n);
	string words[n];
	int counter[n];
	memset(counter, 0, sizeof(counter));
	for (int i = 0; i < n; i++) {
		cin >> words[i];
		ac.add(words[i], i);
	}
	string word;
	cin >> word;
	ac.preprocess();
	ac.calculate(word);
	ac.count(counter);
	for (int i = 0; i < n; i++) {
		printf("%d\n", counter[i]);
	}
	return 0;
}
