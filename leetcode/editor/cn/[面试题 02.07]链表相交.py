# 给定两个（单向）链表，判定它们是否相交并返回交点。请注意相交的定义基于节点的引用，而不是基于节点的值。换句话说，如果一个链表的第k个节点与另一个链表的第j个
# 节点是同一节点（引用完全相同），则这两个链表相交。 示例 1： 输入：intersectVal = 8, listA = [4,1,8,4,5], listB 
# = [5,0,1,8,4,5], skipA = 2, skipB = 3 输出：Reference of the node with value = 8 输入
# 解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4
# ,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。 示例 2： 输入：intersectVal = 2, listA = [0
# ,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1 输出：Reference of the node with v
# alue = 2 输入解释：相交节点的值为 2 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为
#  [3,2,4]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。 示例 3： 输入：intersectVal = 0, listA
#  = [2,6,4], listB = [1,5], skipA = 3, skipB = 2 输出：null 输入解释：从各自的表头开始算起，链表 A 为 [
# 2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。 解释：
# 这两个链表不相交，因此返回 null。 注意： 如果两个链表没有交点，返回 null 。 在返回结果后，两个链表仍须保持原有的结构。 可假定整个链表结构中没有循
# 环。 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。 Related Topics 哈希表 链表 双指针 
#  👍 87 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def getIntersectionNode(self, headA: ListNode, headB: ListNode) -> ListNode:
        part1, part2 = headA, headB
        len1, len2 = 0, 0
        while part1:
            part1 = part1.next
            len1 += 1
        while part2:
            part2 = part2.next
            len2 += 1
        part1, part2 = headA, headB
        while len1 != len2:
            if len1 > len2:
                part1 = part1.next
                len1 -= 1
            else:
                part2 = part2.next
                len2 -= 1
        while part1 != part2:
            part1 = part1.next
            part2 = part2.next
        return part1
# leetcode submit region end(Prohibit modification and deletion)


class Solution:
    def getIntersectionNode(self, headA: ListNode, headB: ListNode) -> ListNode:
        part1, part2 = headA, headB
        while part1 != part2:
            if part1:
                part1 = part1.next
            else:
                part1 = headB
            if part2:
                part2 = part2.next
            else:
                part2 = headA
        return part1


class Solution:
    def getIntersectionNode(self, headA: ListNode, headB: ListNode) -> ListNode:
        part1, part2 = headA, headB
        l1, l2 = [], []
        while part1:
            l1.append(part1)
            part1 = part1.next
        while part2:
            l2.append(part2)
            part2 = part2.next
        if l1.__len__() == 0 or l2.__len__() == 0:
            return None
        part1 = l1.pop()
        part2 = l2.pop()
        if part1 != part2:
            return None
        back = part1
        while l1 and l2:
            part1 = l1.pop()
            part2 = l2.pop()
            if part1 != part2:
                return back
            back = part1
        return back
