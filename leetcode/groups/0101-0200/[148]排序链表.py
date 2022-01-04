# 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。 
# 
#  进阶： 
# 
#  
#  你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？ 
#  
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：head = [4,2,1,3]
# 输出：[1,2,3,4]
#  
# 
#  示例 2： 
# 
#  
# 输入：head = [-1,5,3,4,0]
# 输出：[-1,0,3,4,5]
#  
# 
#  示例 3： 
# 
#  
# 输入：head = []
# 输出：[]
#  
# 
#  
# 
#  提示： 
# 
#  
#  链表中节点的数目在范围 [0, 5 * 10⁴] 内 
#  -10⁵ <= Node.val <= 10⁵ 
#  
#  Related Topics 链表 双指针 分治 排序 归并排序 👍 1257 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next

class Solution:
    def sortList(self, head: ListNode) -> ListNode:
        if head is None or head.next is None:
            return head
        slow = fast = head
        while fast.next is not None and fast.next.next is not None:
            fast = fast.next.next
            slow = slow.next
        righthead = slow.next
        slow.next = None
        return self.merge(self.sortList(head), self.sortList(righthead))

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
