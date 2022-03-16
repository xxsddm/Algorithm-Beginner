# 请你设计一个用于存储字符串计数的数据结构，并能够返回计数最小和最大的字符串。 
# 
#  实现 AllOne 类： 
# 
#  
#  AllOne() 初始化数据结构的对象。 
#  inc(String key) 字符串 key 的计数增加 1 。如果数据结构中尚不存在 key ，那么插入计数为 1 的 key 。 
#  dec(String key) 字符串 key 的计数减少 1 。如果 key 的计数在减少后为 0 ，那么需要将这个 key 从数据结构中删除。测试用例
# 保证：在减少计数前，key 存在于数据结构中。 
#  getMaxKey() 返回任意一个计数最大的字符串。如果没有元素存在，返回一个空字符串 "" 。 
#  getMinKey() 返回任意一个计数最小的字符串。如果没有元素存在，返回一个空字符串 "" 。 
#  
# 
#  
# 
#  示例： 
# 
#  
# 输入
# ["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", 
# "getMinKey"]
# [[], ["hello"], ["hello"], [], [], ["leet"], [], []]
# 输出
# [null, null, null, "hello", "hello", null, "hello", "leet"]
# 
# 解释
# AllOne allOne = new AllOne();
# allOne.inc("hello");
# allOne.inc("hello");
# allOne.getMaxKey(); // 返回 "hello"
# allOne.getMinKey(); // 返回 "hello"
# allOne.inc("leet");
# allOne.getMaxKey(); // 返回 "hello"
# allOne.getMinKey(); // 返回 "leet"
#  
# 
#  
# 
#  提示： 
# 
#  
#  1 <= key.length <= 10 
#  key 由小写英文字母组成 
#  测试用例保证：在每次调用 dec 时，数据结构中总存在 key 
#  最多调用 inc、dec、getMaxKey 和 getMinKey 方法 5 * 10⁴ 次 
#  
#  Related Topics 设计 哈希表 链表 双向链表 👍 203 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Node:
    def __init__(self, count=0, prev=None, next=None):
        self.count = count
        self.word = set()
        self.prev = prev
        self.next = next

    def delete(self):
        self.prev.next = self.next
        self.next.prev = self.prev


class AllOne:
    def __init__(self):
        self.head = Node()
        self.tail = Node()
        self.word2node = dict()
        self.head.next = self.tail
        self.tail.prev = self.head

    def inc(self, key: str) -> None:
        prevNode = self.word2node[key] if key in self.word2node else self.head
        if prevNode != self.head:
            prevNode.word.remove(key)
        if prevNode.next.count != prevNode.count + 1:
            node = Node(prevNode.count + 1, prevNode, prevNode.next)
            node.word.add(key)
            prevNode.next.prev = node
            prevNode.next = node
        else:
            prevNode.next.word.add(key)
        self.word2node[key] = prevNode.next
        if prevNode != self.head and len(prevNode.word) == 0:
            prevNode.delete()

    def dec(self, key: str) -> None:
        nextNode = self.word2node[key]
        nextNode.word.remove(key)
        if nextNode.count == 1:
            self.word2node.pop(key)
            if len(nextNode.word) == 0:
                nextNode.delete()
            return
        if nextNode.prev.count + 1 != nextNode.count:
            node = Node(nextNode.count - 1, nextNode.prev, nextNode)
            node.word.add(key)
            nextNode.prev.next = node
            nextNode.prev = node
        else:
            nextNode.prev.word.add(key)
        self.word2node[key] = nextNode.prev
        if len(nextNode.word) == 0:
            nextNode.delete()

    def getMaxKey(self) -> str:
        if self.head.next == self.tail:
            return ""
        for word in self.tail.prev.word:
            return word

    def getMinKey(self) -> str:
        if self.head.next == self.tail:
            return ""
        for word in self.head.next.word:
            return word

# Your AllOne object will be instantiated and called as such:
# obj = AllOne()
# obj.inc(key)
# obj.dec(key)
# param_3 = obj.getMaxKey()
# param_4 = obj.getMinKey()
# leetcode submit region end(Prohibit modification and deletion)
