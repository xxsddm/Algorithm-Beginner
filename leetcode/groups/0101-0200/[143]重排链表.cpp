//给定一个单链表 L 的头节点 head ，单链表 L 表示为： 
//
// L0 → L1 → … → Ln-1 → Ln 
//请将其重新排列后变为： 
//
// L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → … 
//
// 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。 
//
// 
//
// 示例 1: 
//
// 
//
// 
//输入: head = [1,2,3,4]
//输出: [1,4,2,3] 
//
// 示例 2: 
//
// 
//
// 
//输入: head = [1,2,3,4,5]
//输出: [1,5,2,4,3] 
//
// 
//
// 提示： 
//
// 
// 链表的长度范围为 [1, 5 * 10⁴] 
// 1 <= node.val <= 1000 
// 
// Related Topics 栈 递归 链表 双指针 👍 667 👎 0


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
    void reorderList(ListNode* head) {
        vector<ListNode*> nodes;
        ListNode* prev = head;
        while (prev != nullptr) {
            nodes.push_back(prev);
            prev = prev->next;
        }
        int left = 1, right = (int) nodes.size() - 1; // 待加入节点的左右索引
        bool front = false; // 是否加入left指针节点
        prev = head;    // 起始端点直接加入
        while (left <= right) {
            if (front) {
                prev->next = nodes[left++];
                front = false;
            }
            else {
                prev->next = nodes[right--];
                front = true;
            }
            prev = prev->next;
        }
        prev->next = nullptr;
    }
};

//leetcode submit region end(Prohibit modification and deletion)
