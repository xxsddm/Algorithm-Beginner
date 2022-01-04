//给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5], k = 2
//输出：[4,5,1,2,3]
// 
//
// 示例 2： 
//
// 
//输入：head = [0,1,2], k = 4
//输出：[2,0,1]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目在范围 [0, 500] 内 
// -100 <= Node.val <= 100 
// 0 <= k <= 2 * 10⁹ 
// 
// Related Topics 链表 双指针 👍 608 👎 0


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
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }

        ListNode tail = head,  temp = head, newhead;
        int count = 1;
        while (tail.next != null) {     // 计算节点数量
            count++;
            tail = tail.next;
        }

        int num = k % count;        // 实际需要移动的次数, 即需要从尾部截取的长度
        if (num == 0) {
            return head;
        }
        for (int numhead = 1; numhead < count - num; numhead++) {   // 前端部分需移到后面的已截取节点数量
            temp = temp.next;
        }
        newhead = temp.next;        // temp为新的末端
        temp.next = null;
        tail.next = head;       // 原末端的next指向原首端
        return newhead;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
