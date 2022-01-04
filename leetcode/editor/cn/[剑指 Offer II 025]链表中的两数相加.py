# 给定两个 非空链表 l1和 l2 来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。 
# 
#  可以假设除了数字 0 之外，这两个数字都不会以零开头。 
# 
#  
# 
#  示例1： 
# 
#  
# 
#  
# 输入：l1 = [7,2,4,3], l2 = [5,6,4]
# 输出：[7,8,0,7]
#  
# 
#  示例2： 
# 
#  
# 输入：l1 = [2,4,3], l2 = [5,6,4]
# 输出：[8,0,7]
#  
# 
#  示例3： 
# 
#  
# 输入：l1 = [0], l2 = [0]
# 输出：[0]
#  
# 
#  
# 
#  提示： 
# 
#  
#  链表的长度范围为 [1, 100] 
#  0 <= node.val <= 9 
#  输入数据保证链表代表的数字无前导 0 
#  
# 
#  
# 
#  进阶：如果输入链表不能修改该如何处理？换句话说，不能对列表中的节点进行翻转。 
# 
#  
# 
#  注意：本题与主站 445 题相同：https://leetcode-cn.com/problems/add-two-numbers-ii/ 
#  Related Topics 栈 链表 数学 
#  👍 0 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def addTwoNumbers(self, l1: ListNode, l2: ListNode) -> ListNode:
        heads = [l1, l2]

        for i in range(2):
            before, temp = heads[i], heads[i].next
            before.next = None
            while temp:
                temptemp = temp.next
                temp.next = before
                before = temp
                temp = temptemp
            heads[i] = before

        node1, node2 = heads
        num = node1.val + node2.val
        add = num // 10
        after = ListNode(val=num % 10)
        node1, node2 = node1.next, node2.next

        while add > 0 or node1 or node2:
            num = add
            if node1:
                num += node1.val
                node1 = node1.next
            if node2:
                num += node2.val
                node2 = node2.next
            add = num // 10
            after = ListNode(val=num % 10, next=after)

        return after

# leetcode submit region end(Prohibit modification and deletion)
