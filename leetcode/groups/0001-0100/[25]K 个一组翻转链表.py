# 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。 
# 
#  k 是一个正整数，它的值小于或等于链表的长度。 
# 
#  如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。 
# 
#  进阶： 
# 
#  
#  你可以设计一个只使用常数额外空间的算法来解决此问题吗？ 
#  你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。 
#  
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：head = [1,2,3,4,5], k = 2
# 输出：[2,1,4,3,5]
#  
# 
#  示例 2： 
# 
#  
# 输入：head = [1,2,3,4,5], k = 3
# 输出：[3,2,1,4,5]
#  
# 
#  示例 3： 
# 
#  
# 输入：head = [1,2,3,4,5], k = 1
# 输出：[1,2,3,4,5]
#  
# 
#  示例 4： 
# 
#  
# 输入：head = [1], k = 1
# 输出：[1]
#  
# 
#  
#  
# 
#  提示： 
# 
#  
#  列表中节点的数量在范围 sz 内 
#  1 <= sz <= 5000 
#  0 <= Node.val <= 1000 
#  1 <= k <= sz 
#  
#  Related Topics 递归 链表 👍 1264 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next


class Solution:
    def reverseKGroup(self, head: ListNode, k: int) -> ListNode:
        visualhead = prev = ListNode(0, head)

        while prev.next:
            temp = prev  # 前面部分的最后节点 temp
            start = prev.next
            count = 0
            while count < k and prev.next:  # 找出k个节点, 待反转部分为 start~prev
                prev = prev.next
                count += 1
            if count != k:
                return visualhead.next
            nextpart = prev.next  # 后面部分的头结点 nextpart

            # 反转start~prev
            before = None
            tempstart = start
            while start != prev:
                nextstart = start.next
                start.next = before
                before = start
                start = nextstart
            prev.next = before
            temp.next = prev
            tempstart.next = nextpart
            prev = tempstart

        return visualhead.next

# leetcode submit region end(Prohibit modification and deletion)
