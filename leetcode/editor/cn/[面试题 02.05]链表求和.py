# 给定两个用链表表示的整数，每个节点包含一个数位。 
# 
#  这些数位是反向存放的，也就是个位排在链表首部。 
# 
#  编写函数对这两个整数求和，并用链表形式返回结果。 
# 
#  
# 
#  示例： 
# 
#  输入：(7 -> 1 -> 6) + (5 -> 9 -> 2)，即617 + 295
# 输出：2 -> 1 -> 9，即912
#  
# 
#  进阶：思考一下，假设这些数位是正向存放的，又该如何解决呢? 
# 
#  示例： 
# 
#  输入：(6 -> 1 -> 7) + (2 -> 9 -> 5)，即617 + 295
# 输出：9 -> 1 -> 2，即912
#  
#  Related Topics 递归 链表 数学 👍 87 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None


class Solution:
    def addTwoNumbers(self, l1: ListNode, l2: ListNode) -> ListNode:
        before = 0
        visualhead = prev = ListNode()
        while l1 or l2:
            val = before
            if l1:
                val += l1.val
                l1 = l1.next
            if l2:
                val += l2.val
                l2 = l2.next
            before = val // 10
            prev.next = ListNode(val % 10)
            prev = prev.next
        if before != 0:
            prev.next = ListNode(before)
        return visualhead.next

# leetcode submit region end(Prohibit modification and deletion)
