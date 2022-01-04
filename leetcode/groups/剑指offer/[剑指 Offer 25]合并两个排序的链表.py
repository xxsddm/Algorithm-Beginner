# 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。 
# 
#  示例1： 
# 
#  输入：1->2->4, 1->3->4
# 输出：1->1->2->3->4->4 
# 
#  限制： 
# 
#  0 <= 链表长度 <= 1000 
# 
#  注意：本题与主站 21 题相同：https://leetcode-cn.com/problems/merge-two-sorted-lists/ 
#  Related Topics 递归 链表 
#  👍 151 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def mergeTwoLists(self, l1: ListNode, l2: ListNode) -> ListNode:
        before = visualhead = ListNode(0)
        temp1, temp2 = l1, l2

        while temp1 and temp2:
            if temp1.val < temp2.val:
                before.next = temp1
                temp1 = temp1.next
            else:
                before.next = temp2
                temp2 = temp2.next
            before = before.next

        while temp1:
            before.next = temp1
            temp1 = temp1.next
            before = before.next

        while temp2:
            before.next = temp2
            temp2 = temp2.next
            before = before.next

        return visualhead.next
# leetcode submit region end(Prohibit modification and deletion)
