# 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。 
# 
#  请你将两个数相加，并以相同形式返回一个表示和的链表。 
# 
#  你可以假设除了数字 0 之外，这两个数都不会以 0 开头。 
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：l1 = [2,4,3], l2 = [5,6,4]
# 输出：[7,0,8]
# 解释：342 + 465 = 807.
#  
# 
#  示例 2： 
# 
#  
# 输入：l1 = [0], l2 = [0]
# 输出：[0]
#  
# 
#  示例 3： 
# 
#  
# 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
# 输出：[8,9,9,9,0,0,0,1]
#  
# 
#  
# 
#  提示： 
# 
#  
#  每个链表中的节点数在范围 [1, 100] 内 
#  0 <= Node.val <= 9 
#  题目数据保证列表表示的数字不含前导零 
#  
#  Related Topics 递归 链表 数学 
#  👍 6443 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next

class Solution:
    def addTwoNumbers(self, l1: ListNode, l2: ListNode) -> ListNode:
        visualhead = ListNode()
        before = visualhead
        add = 0
        while l1 or l2:
            val = add
            if l1:
                val += l1.val
                l1 = l1.next
            if l2:
                val += l2.val
                l2 = l2.next
            add = val // 10
            val = val % 10
            before.next = ListNode(val=val, next=None)
            before = before.next
        if add > 0:
            before.next = ListNode(val=add, next=None)
        return visualhead.next
# leetcode submit region end(Prohibit modification and deletion)

class Solution:
    def addTwoNumbers(self, l1: ListNode, l2: ListNode) -> ListNode:
        temp1 = l1
        temp2 = l2
        list1 = []
        list2 = []
        num = 0
        while temp1:
            list1.append(temp1.val)
            temp1 = temp1.next
        while temp2:
            list2.append(temp2.val)
            temp2 = temp2.next

        while len(list1) >= 1 and len(list2) >= 1:
            if len(list1) == len(list2):
                num *= 10
                num += list1.pop() + list2.pop()
            elif len(list1) > len(list2):
                num *= 10
                num += list1.pop()
            elif len(list1) < len(list2):
                num *= 10
                num += list2.pop()
            else:
                break

        val = num % 10
        num = num // 10
        first = ListNode(val=val)   # 头节点
        temp3 = first
        while num >= 1:
            val = num % 10
            num = num // 10
            temp3.next = ListNode(val=val)   # 下一个节点
            temp3 = temp3.next
        return first