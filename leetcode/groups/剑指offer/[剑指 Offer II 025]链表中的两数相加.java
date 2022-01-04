//给定两个 非空链表 l1和 l2 来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。 
//
// 可以假设除了数字 0 之外，这两个数字都不会以零开头。 
//
// 
//
// 示例1： 
//
// 
//
// 
//输入：l1 = [7,2,4,3], l2 = [5,6,4]
//输出：[7,8,0,7]
// 
//
// 示例2： 
//
// 
//输入：l1 = [2,4,3], l2 = [5,6,4]
//输出：[8,0,7]
// 
//
// 示例3： 
//
// 
//输入：l1 = [0], l2 = [0]
//输出：[0]
// 
//
// 
//
// 提示： 
//
// 
// 链表的长度范围为 [1, 100] 
// 0 <= node.val <= 9 
// 输入数据保证链表代表的数字无前导 0 
// 
//
// 
//
// 进阶：如果输入链表不能修改该如何处理？换句话说，不能对列表中的节点进行翻转。 
//
// 
//
// 注意：本题与主站 445 题相同：https://leetcode-cn.com/problems/add-two-numbers-ii/ 
// Related Topics 栈 链表 数学 
// 👍 0 👎 0


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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode[] heads = new ListNode[]{l1, l2};
        for (int i = 0; i < 2; i++){    // 链表反转
            ListNode before = heads[i], temp = before.next;
            before.next = null;
            while (temp != null){
                ListNode temptemp = temp.next;
                temp.next = before;
                before = temp;
                temp = temptemp;
            }
            heads[i] = before;
        }
        ListNode node1 = heads[0], node2 = heads[1];
        int num = node1.val + node2.val, add = num / 10;
        ListNode after = new ListNode(num % 10);
        node1 = node1.next;
        node2 = node2.next;
        while (add != 0 || node1 != null || node2 != null){
            num = add;
            if (node1 != null){
                num += node1.val;
                node1 = node1.next;
            }
            if (node2 != null){
                num += node2.val;
                node2 = node2.next;
            }
            add = num / 10;
            after = new ListNode(num % 10, after);
        }

        return after;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
