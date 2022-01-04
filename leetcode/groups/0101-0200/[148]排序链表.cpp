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
// Related Topics 链表 双指针 分治 排序 归并排序 👍 1358 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */

class Solution {
public:
    ListNode* sortList(ListNode* head) {    // 快速排序(归并见java)
        if (head == nullptr) {
            return nullptr;
        }

        // 链表随机排序(否则卡案例)
        vector<ListNode*> container;
        ListNode *prev = head, dummy = ListNode();
        while (prev != nullptr) {
            container.push_back(prev);
            prev = prev->next;
        }
        random_shuffle(container.begin(), container.end());
        prev = &dummy;
        for (ListNode* node: container) {
            prev->next = node;
            prev = node;
        }
        prev->next = nullptr;

        return sort(dummy.next, nullptr);
    }

    ListNode* sort(ListNode *start, ListNode *nextPart) {   // 三向快排
        if (start == nullptr) {
            return nextPart;
        }
        if (start->next == nullptr) {
            start->next = nextPart;
            return start;
        }
        int pivot = start->val;
        // 链表以pivot为准则分为三个部分,最后拼接
        ListNode dummyLeft = ListNode(), dummyRight = ListNode(), dummyMid = ListNode();
        ListNode *prev = start->next, *prevLeft = &dummyLeft, *prevRight = &dummyRight, *prevMid = &dummyMid;
        prevMid->next = start;
        prevMid = start;
        start->next = nullptr;
        while (prev != nullptr) {
            if (prev->val < pivot) {
                prevLeft->next = prev;
                prevLeft = prev;
            } else if (prev->val > pivot) {
                prevRight->next = prev;
                prevRight = prev;
            } else {
                prevMid->next = prev;
                prevMid = prev;
            }
            prev = prev->next;
        }
        prevLeft->next = prevRight->next = prevMid->next = nullptr;
        // 拼接三部分
        ListNode *ans = sort(dummyLeft.next, dummyMid.next);
        prevMid->next = sort(dummyRight.next, nextPart);
        return ans;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
