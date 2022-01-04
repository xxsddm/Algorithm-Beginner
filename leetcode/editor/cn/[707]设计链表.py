# 设计链表的实现。您可以选择使用单链表或双链表。单链表中的节点应该具有两个属性：val 和 next。val 是当前节点的值，next 是指向下一个节点的指针
# /引用。如果要使用双向链表，则还需要一个属性 prev 以指示链表中的上一个节点。假设链表中的所有节点都是 0-index 的。 
# 
#  在链表类中实现这些功能： 
# 
#  
#  get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回-1。 
#  addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。 
#  addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。 
#  addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val 的节点。如果 index 等于链表的长度，则该节点将附加
# 到链表的末尾。如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。 
#  deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。 
#  
# 
#  
# 
#  示例： 
# 
#  MyLinkedList linkedList = new MyLinkedList();
# linkedList.addAtHead(1);
# linkedList.addAtTail(3);
# linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
# linkedList.get(1);            //返回2
# linkedList.deleteAtIndex(1);  //现在链表是1-> 3
# linkedList.get(1);            //返回3
#  
# 
#  
# 
#  提示： 
# 
#  
#  所有val值都在 [1, 1000] 之内。 
#  操作次数将在 [1, 1000] 之内。 
#  请不要使用内置的 LinkedList 库。 
#  
#  Related Topics 设计 链表 
#  👍 263 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Node:
    def __init__(self, val=0, prev=None, next=None):    # 双链表
        self.val = val
        self.prev = prev
        self.next = next


class MyLinkedList:
    def __init__(self):  # 要求是真的多, 得审题
        """
        Initialize your data structure here.
        """
        self.visualhead = Node()
        self.visualtail = Node()
        self.visualhead.next = self.visualtail
        self.visualtail.prev = self.visualhead
        self.size = 0

    def get(self, index: int) -> int:
        """
        Get the value of the index-th node in the linked list. If the index is invalid, return -1.
        """
        if index < 0 or index >= self.size:
            return -1
        elif 1 <= index + 1 <= self.size // 2:
            temp = self.visualhead.next
            for counter in range(index):
                temp = temp.next
            return temp.val
        else:
            temp = self.visualtail.prev
            for counter in range(self.size, index + 1, -1):
                temp = temp.prev
            return temp.val

    def addAtHead(self, val: int) -> None:
        """
        Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
        """
        node = Node(val, self.visualhead, self.visualhead.next)
        self.visualhead.next.prev = node
        self.visualhead.next = node
        self.size += 1

    def addAtTail(self, val: int) -> None:
        """
        Append a node of value val to the last element of the linked list.
        """
        node = Node(val, self.visualtail.prev, self.visualtail)
        self.visualtail.prev.next = node
        self.visualtail.prev = node
        self.size += 1

    def addAtIndex(self, index: int, val: int) -> None:
        """
        Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
        """
        node = Node(val)
        if index < 0:
            self.addAtHead(val)
        elif 1 <= index + 1 <= self.size // 2:
            before = self.visualhead
            for counter in range(index):
                before = before.next
            node.prev = before
            node.next = before.next
            before.next.prev = node
            before.next = node
            self.size += 1
        elif index <= self.size:
            after = self.visualtail
            for counter in range(self.size, index, -1):
                after = after.prev
            node.prev = after.prev
            node.next = after
            after.prev.next = node
            after.prev = node
            self.size += 1
        # else:
        #     raise IndexError('换个index吧兄弟')    # 这题不让报错

    def deleteAtIndex(self, index: int) -> None:
        """
        Delete the index-th node in the linked list, if the index is valid.
        """
        if 1 <= index + 1 <= self.size // 2:
            before = self.visualhead
            for counter in range(index):
                before = before.next
            before.next = before.next.next
            before.next.prev = before
            self.size -= 1
        elif 0 <= index < self.size:
            after = self.visualtail
            for counter in range(self.size, index + 1, -1):
                after = after.prev
            after.prev = after.prev.prev
            after.prev.next = after
            self.size -= 1
        # else:
        #     raise IndexError('换个index吧兄弟')    # 这题不让报错


# Your MyLinkedList object will be instantiated and called as such:
# obj = MyLinkedList()
# param_1 = obj.get(index)
# obj.addAtHead(val)
# obj.addAtTail(val)
# obj.addAtIndex(index,val)
# obj.deleteAtIndex(index)
# leetcode submit region end(Prohibit modification and deletion)
