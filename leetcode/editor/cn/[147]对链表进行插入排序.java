//对链表进行插入排序。 
//
// 
//插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。 
//每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。 
//
// 
//
// 插入排序算法： 
//
// 
// 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。 
// 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。 
// 重复直到所有输入数据插入完为止。 
// 
//
// 
//
// 示例 1： 
//
// 输入: 4->2->1->3
//输出: 1->2->3->4
// 
//
// 示例 2： 
//
// 输入: -1->5->3->4->0
//输出: -1->0->3->4->5
// 
// Related Topics 链表 排序 👍 420 👎 0


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
    public ListNode insertionSortList(ListNode head) {      // 直接归并算了
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        ListNode slow = head, fast = head, righthead;
        while (fast.next != null && fast.next.next !=null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        righthead = slow.next;
        slow.next = null;
        return merge(insertionSortList(head), insertionSortList(righthead));
    }

    private ListNode merge(ListNode left, ListNode right) {
        ListNode visualhead = new ListNode(), prev = visualhead;
        while (left != null || right != null) {
            if (left != null && right != null) {
                if (left.val <= right.val) {
                    prev.next = left;
                    prev = prev.next;
                    left = left.next;
                }
                else {
                    prev.next = right;
                    prev = prev.next;
                    right = right.next;
                }
            } else if (right == null) {
                prev.next = left;
                prev = prev.next;
                left = left.next;
            } else {
                prev.next = right;
                prev = prev.next;
                right = right.next;
            }
        }
        return visualhead.next;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
