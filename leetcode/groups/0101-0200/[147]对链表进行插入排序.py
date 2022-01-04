# 对链表进行插入排序。 
# 
#  
# 插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。 
# 每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。 
# 
#  
# 
#  插入排序算法： 
# 
#  
#  插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。 
#  每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。 
#  重复直到所有输入数据插入完为止。 
#  
# 
#  
# 
#  示例 1： 
# 
#  输入: 4->2->1->3
# 输出: 1->2->3->4
#  
# 
#  示例 2： 
# 
#  输入: -1->5->3->4->0
# 输出: -1->0->3->4->5
#  
#  Related Topics 链表 排序 👍 420 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next

class Solution:
    def insertionSortList(self, head: ListNode) -> ListNode:
        if head is None or head.next is None:
            return head
        slow = fast = head
        while fast.next is not None and fast.next.next is not None:
            fast = fast.next.next
            slow = slow.next
        righthead = slow.next
        slow.next = None
        return self.merge(self.insertionSortList(head), self.insertionSortList(righthead))

    def merge(self, left, right):
        visualhead = prev = ListNode()
        while True:
            if left:
                if right:
                    if left.val <= right.val:
                        prev.next = left
                        prev = prev.next
                        left = left.next
                    else:
                        prev.next = right
                        prev = prev.next
                        right = right.next
                else:
                    prev.next = left
                    prev = prev.next
                    left = left.next
            else:
                if right:
                    prev.next = right
                    prev = prev.next
                    right = right.next
                else:
                    break
        return visualhead.next

# leetcode submit region end(Prohibit modification and deletion)
