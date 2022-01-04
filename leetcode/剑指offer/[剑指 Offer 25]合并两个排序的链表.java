//输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。 
//
// 示例1： 
//
// 输入：1->2->4, 1->3->4
//输出：1->1->2->3->4->4 
//
// 限制： 
//
// 0 <= 链表长度 <= 1000 
//
// 注意：本题与主站 21 题相同：https://leetcode-cn.com/problems/merge-two-sorted-lists/ 
// Related Topics 递归 链表 
// 👍 151 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode visualhead = new ListNode(0), before = visualhead;
        ListNode temp1 = l1, temp2 = l2;

        while (temp1 != null && temp2 != null){
            if (temp1.val < temp2.val){
                before.next = temp1;
                temp1 = temp1.next;
            }
            else {
                before.next = temp2;
                temp2 = temp2.next;
            }
            before = before.next;
        }

        while (temp1 != null){
            before.next = temp1;
            temp1 = temp1.next;
            before = before.next;
        }

        while (temp2 != null){
            before.next = temp2;
            temp2 = temp2.next;
            before = before.next;
        }

        return visualhead.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
