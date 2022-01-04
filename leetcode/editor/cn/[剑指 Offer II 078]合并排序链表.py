# 给定一个链表数组，每个链表都已经按升序排列。 
# 
#  请将所有链表合并到一个升序链表中，返回合并后的链表。 
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：lists = [[1,4,5],[1,3,4],[2,6]]
# 输出：[1,1,2,3,4,4,5,6]
# 解释：链表数组如下：
# [
#   1->4->5,
#   1->3->4,
#   2->6
# ]
# 将它们合并到一个有序链表中得到。
# 1->1->2->3->4->4->5->6
#  
# 
#  示例 2： 
# 
#  
# 输入：lists = []
# 输出：[]
#  
# 
#  示例 3： 
# 
#  
# 输入：lists = [[]]
# 输出：[]
#  
# 
#  
# 
#  提示： 
# 
#  
#  k == lists.length 
#  0 <= k <= 10^4 
#  0 <= lists[i].length <= 500 
#  -10^4 <= lists[i][j] <= 10^4 
#  lists[i] 按 升序 排列 
#  lists[i].length 的总和不超过 10^4 
#  
# 
#  
# 
#  注意：本题与主站 23 题相同： https://leetcode-cn.com/problems/merge-k-sorted-lists/ 
#  Related Topics 链表 分治 堆（优先队列） 归并排序 
#  👍 0 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def mergeKLists(self, lists: List[ListNode]) -> ListNode:
        before = visualhead = ListNode()
        count = sum(node is not None for node in lists)
        index = 0
        while count > 0:
            num = math.inf
            for i in range(len(lists)):
                if lists[i] and lists[i].val < num:
                    num = lists[i].val
                    index = i
            before.next = lists[index]
            lists[index] = lists[index].next
            before = before.next
            if not lists[index]:
                count -= 1
        return visualhead.next

# leetcode submit region end(Prohibit modification and deletion)
from queue import PriorityQueue


class Solution:
    def mergeKLists(self, lists):
        before = visualhead = ListNode()
        container = PriorityQueue()
        for node in lists:
            while node:
                container.put((node.val, node))
                node = node.next
        while container.qsize() > 0:
            before.next = container.get()[1]
            before = before.next
        return visualhead.next
