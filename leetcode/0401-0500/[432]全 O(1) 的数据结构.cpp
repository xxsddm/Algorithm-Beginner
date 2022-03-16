//请你设计一个用于存储字符串计数的数据结构，并能够返回计数最小和最大的字符串。 
//
// 实现 AllOne 类： 
//
// 
// AllOne() 初始化数据结构的对象。 
// inc(String key) 字符串 key 的计数增加 1 。如果数据结构中尚不存在 key ，那么插入计数为 1 的 key 。 
// dec(String key) 字符串 key 的计数减少 1 。如果 key 的计数在减少后为 0 ，那么需要将这个 key 从数据结构中删除。测试用例
//保证：在减少计数前，key 存在于数据结构中。 
// getMaxKey() 返回任意一个计数最大的字符串。如果没有元素存在，返回一个空字符串 "" 。 
// getMinKey() 返回任意一个计数最小的字符串。如果没有元素存在，返回一个空字符串 "" 。 
// 
//
// 
//
// 示例： 
//
// 
//输入
//["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", 
//"getMinKey"]
//[[], ["hello"], ["hello"], [], [], ["leet"], [], []]
//输出
//[null, null, null, "hello", "hello", null, "hello", "leet"]
//
//解释
//AllOne allOne = new AllOne();
//allOne.inc("hello");
//allOne.inc("hello");
//allOne.getMaxKey(); // 返回 "hello"
//allOne.getMinKey(); // 返回 "hello"
//allOne.inc("leet");
//allOne.getMaxKey(); // 返回 "hello"
//allOne.getMinKey(); // 返回 "leet"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= key.length <= 10 
// key 由小写英文字母组成 
// 测试用例保证：在每次调用 dec 时，数据结构中总存在 key 
// 最多调用 inc、dec、getMaxKey 和 getMinKey 方法 5 * 10⁴ 次 
// 
// Related Topics 设计 哈希表 链表 双向链表 👍 203 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class AllOne {
public:
    struct Node {
        int count = 0;
        unordered_set<string> words;
        Node *prev = nullptr, *next = nullptr;
    } *head = new Node(), *tail = new Node();
    unordered_map<string, Node*> word2node;

    AllOne() {
        head->next = tail;
        tail->prev = head;
    }

    void inc(string key) {
        Node *prevNode = word2node.count(key) ? word2node[key] : head;
        if (prevNode->next->count != prevNode->count + 1) {
            Node *node = new Node();
            node->prev = prevNode;
            node->next = prevNode->next;
            prevNode->next->prev = node;
            prevNode->next = node;
            node->count = prevNode->count + 1;
        }
        Node *node = prevNode->next;
        node->words.insert(key);
        word2node[key] = prevNode->next;
        if (prevNode == head) {
            return;
        }
        if ((int) prevNode->words.size() == 1) {
            prevNode->prev->next = prevNode->next;
            prevNode->next->prev = prevNode->prev;
            delete prevNode;
            return;
        }
        prevNode->words.erase(key);
    }

    void dec(string key) {
        Node *nextNode = word2node[key];
        if (nextNode->count == 1) {
            word2node.erase(key);
            if ((int) nextNode->words.size() == 1) {
                nextNode->prev->next = nextNode->next;
                nextNode->next->prev = nextNode->prev;
                delete nextNode;
            } else {
                nextNode->words.erase(key);
            }
            return;
        }
        if (nextNode->count != nextNode->prev->count + 1) {
            Node *node = new Node();
            node->next = nextNode;
            node->prev = nextNode->prev;
            nextNode->prev->next = node;
            nextNode->prev = node;
            node->count = nextNode->count - 1;
        }
        Node *node = nextNode->prev;
        node->words.insert(key);
        word2node[key] = node;
        if ((int) nextNode->words.size() == 1) {
            nextNode->prev->next = nextNode->next;
            nextNode->next->prev = nextNode->prev;
            delete nextNode;
            return;
        }
        nextNode->words.erase(key);
    }

    string getMaxKey() {
        if (tail->prev->count == 0) {
            return "";
        }
        return *tail->prev->words.begin();
    }

    string getMinKey() {
        if (head->next->count == 0) {
            return "";
        }
        return *head->next->words.begin();
    }
};

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne* obj = new AllOne();
 * obj->inc(key);
 * obj->dec(key);
 * string param_3 = obj->getMaxKey();
 * string param_4 = obj->getMinKey();
 */
//leetcode submit region end(Prohibit modification and deletion)

class AllOne {
public:
    unordered_map<string, int> counter;
    map<int, unordered_set<string>> container;

    AllOne() {
    }

    void inc(string key) {
        if (!counter.count(key)) {
            counter[key] = 1;
            container[1].insert(key);
            return;
        }
        int prev = counter[key];
        counter[key]++;
        container[prev].erase(key);
        container[prev + 1].insert(key);
        if (container[prev].empty()) {
            container.erase(prev);
        }
    }

    void dec(string key) {
        int prev = counter[key];
        if (prev == 1) {
            counter.erase(key);
            container[1].erase(key);
        } else {
            counter[key]--;
            container[prev].erase(key);
            container[prev - 1].insert(key);
        }
        if (container[prev].empty()) {
            container.erase(prev);
        }
    }

    string getMaxKey() {
        if (container.empty()) {
            return "";
        }
        return *(container.rbegin()->second.begin());
    }

    string getMinKey() {
        if (container.empty()) {
            return "";
        }
        return *(container.begin()->second.begin());
    }
};
