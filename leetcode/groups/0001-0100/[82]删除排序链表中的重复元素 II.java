//存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除链表中所有存在数字重复情况的节点，只保留原始链表中 没有重复出现 的数字。 
//
// 返回同样按升序排列的结果链表。 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,3,4,4,5]
//输出：[1,2,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [1,1,1,2,3]
//输出：[2,3]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点数目在范围 [0, 300] 内 
// -100 <= Node.val <= 100 
// 题目数据保证链表已经按升序排列 
// 
// Related Topics 链表 双指针 👍 705 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode visualhead = new ListNode(0, head);
        ListNode left = visualhead, right = head;
        while (right != null) {
            // 跳过重复区域(可能有多个重复区域)
            while (right != null && right.next != null && right.val == right.next.val) {
                int temp = right.val;
                while (right != null && right.val == temp) {
                    right = right.next;
                }
            }
            left.next = right;
            left = left.next;
            if (right != null) {
                right = right.next;
            }
        }
        return visualhead.next;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
