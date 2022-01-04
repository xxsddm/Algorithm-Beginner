//给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。 
//
// 进阶： 
//
// 
// 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？ 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [4,2,1,3]
//输出：[1,2,3,4]
// 
//
// 示例 2： 
//
// 
//输入：head = [-1,5,3,4,0]
//输出：[-1,0,3,4,5]
// 
//
// 示例 3： 
//
// 
//输入：head = []
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目在范围 [0, 5 * 10⁴] 内 
// -10⁵ <= Node.val <= 10⁵ 
// 
// Related Topics 链表 双指针 分治 排序 归并排序 👍 1257 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.Collections;

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
    public ListNode sortList(ListNode head) {   // 快速排序
        if (head == null) {
            return null;
        }

        // 链表随机排序
        ListNode dummy = new ListNode(), prev = dummy;
        ArrayList<ListNode> container= new ArrayList<>();
        while (head != null) {
            container.add(head);
            head = head.next;
        }
        Collections.shuffle(container);
        for (ListNode node: container) {
            prev.next = node;
            prev = node;
        }
        prev.next = null;

        return sort(dummy.next, null);
    }

    ListNode sort(ListNode start, ListNode nextPart) {  // 三向快排
        if (start == null) {
            return nextPart;
        }
        if (start.next == null) {
            start.next = nextPart;
            return start;
        }
        int pivot = start.val;
        // 链表以pivot为准则分为三个部分,最后拼接
        ListNode dummyLeft = new ListNode(), dummyRight = new ListNode(), dummyMid = new ListNode();
        ListNode prevLeft = dummyLeft, prevRight = dummyRight, prevMid = dummyMid, prev = start.next;
        prevMid.next = start;
        prevMid = start;
        start.next = null;
        while (prev != null) {
            if (prev.val < pivot) {
                prevLeft.next = prev;
                prevLeft = prev;
            } else if (prev.val > pivot) {
                prevRight.next = prev;
                prevRight = prev;
            } else {
                prevMid.next = prev;
                prevMid = prev;
            }
            prev = prev.next;
        }
        prevLeft.next = null;
        prevRight.next = null;
        prevMid.next = null;
        // 拼接三部分
        ListNode ans = sort(dummyLeft.next, dummyMid.next);
        prevMid.next = sort(dummyRight.next, nextPart);
        return ans;
    }
}

//leetcode submit region end(Prohibit modification and deletion)

class Solution {
    public ListNode sortList(ListNode head) {       // 归并排序
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        ListNode slow = head, fast = head, righthead;
        while (fast.next != null && fast.next.next != null) {       // 找到中间分隔点
            fast = fast.next.next;
            slow = slow.next;
        }
        righthead = slow.next;
        slow.next = null;
        return merge(sortList(head), sortList(righthead));
    }

    private ListNode merge(ListNode left, ListNode right) {
        ListNode visualhead = new ListNode(), prev = visualhead;
        while (true) {
            if (left != null) {
                if (right != null) {
                    if (left.val <= right.val) {
                        prev.next = left;
                        prev = prev.next;
                        left = left.next;
                    } else {
                        prev.next = right;
                        prev = prev.next;
                        right = right.next;
                    }
                } else {
                    prev.next = left;
                    prev = prev.next;
                    left = left.next;
                }
            } else {
                if (right == null) {
                    break;
                } else {
                    prev.next = right;
                    prev = prev.next;
                    right = right.next;
                }
            }
        }
        return visualhead.next;
    }
}
