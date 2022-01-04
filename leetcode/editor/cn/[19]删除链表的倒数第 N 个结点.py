# 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。 
# 
#  进阶：你能尝试使用一趟扫描实现吗？ 
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：head = [1,2,3,4,5], n = 2
# 输出：[1,2,3,5]
#  
# 
#  示例 2： 
# 
#  
# 输入：head = [1], n = 1
# 输出：[]
#  
# 
#  示例 3： 
# 
#  
# 输入：head = [1,2], n = 1
# 输出：[1]
#  
# 
#  
# 
#  提示： 
# 
#  
#  链表中结点的数目为 sz 
#  1 <= sz <= 30 
#  0 <= Node.val <= 100 
#  1 <= n <= sz 
#  
#  Related Topics 链表 双指针 
#  👍 1485 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def removeNthFromEnd(self, head: ListNode, n: int) -> ListNode:
        slow = fast = visualhead = ListNode(next=head)
        for i in range(n + 1):  # 使 fast == None 时, slow为倒数第n+1个节点, 便于跳过倒数第n个节点
            fast = fast.next
        while fast:
            fast = fast.next
            slow = slow.next
        slow.next = slow.next.next
        return visualhead.next
# leetcode submit region end(Prohibit modification and deletion)
