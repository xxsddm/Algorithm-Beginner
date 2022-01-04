//多级双向链表中，除了指向下一个节点和前一个节点指针之外，它还有一个子链表指针，可能指向单独的双向链表。这些子列表也可能会有一个或多个自己的子项，依此类推，生
//成多级数据结构，如下面的示例所示。 
//
// 给你位于列表第一级的头节点，请你扁平化列表，使所有结点出现在单级双链表中。 
//
// 
//
// 示例 1： 
//
// 输入：head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
//输出：[1,2,3,7,8,11,12,9,10,4,5,6]
//解释：
//
//输入的多级列表如下图所示：
//
//
//
//扁平化后的链表如下图：
//
//
// 
//
// 示例 2： 
//
// 输入：head = [1,2,null,3]
//输出：[1,3,2]
//解释：
//
//输入的多级列表如下图所示：
//
//  1---2---NULL
//  |
//  3---NULL
// 
//
// 示例 3： 
//
// 输入：head = []
//输出：[]
// 
//
// 
//
// 如何表示测试用例中的多级链表？ 
//
// 以 示例 1 为例： 
//
//  1---2---3---4---5---6--NULL
//         |
//         7---8---9---10--NULL
//             |
//             11--12--NULL 
//
// 序列化其中的每一级之后： 
//
// [1,2,3,4,5,6,null]
//[7,8,9,10,null]
//[11,12,null]
// 
//
// 为了将每一级都序列化到一起，我们需要每一级中添加值为 null 的元素，以表示没有节点连接到上一级的上级节点。 
//
// [1,2,3,4,5,6,null]
//[null,null,7,8,9,10,null]
//[null,11,12,null]
// 
//
// 合并所有序列化结果，并去除末尾的 null 。 
//
// [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12] 
//
// 
//
// 提示： 
//
// 
// 节点数目不超过 1000 
// 1 <= Node.val <= 10^5 
// 
// Related Topics 深度优先搜索 链表 双向链表 👍 234 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
public:
    int val;
    Node* prev;
    Node* next;
    Node* child;
};
*/

class Solution {
public:
    Node* flatten(Node* head) { // 迭代(空间优化)
        if (head == nullptr) {
            return nullptr;
        }
        Node* node = head;  // 用node遍历整个链表

        while (node != nullptr) {
            if (node->child == nullptr) {
                node = node->next;
                continue;
            }
            Node* oldNext = node->next;
            Node* newNext = node->child;
            node->next = newNext;
            newNext->prev = node;
            node->child = nullptr;
            if (oldNext != nullptr) {   // 若oldNext节点非空,则将其接入newNext的next末端
                while (node->next != nullptr) {
                    node = node->next;
                }
                node->next = oldNext;
                oldNext->prev = node;
            }
            node = newNext;
        }

        return head;
    }
};

//leetcode submit region end(Prohibit modification and deletion)

class Solution {
public:
    Node* flatten(Node* head) { // 递归
        if (head == nullptr) {
            return nullptr;
        }

        // 原next经过flatten后,接入原child经过flatten后的next
        Node* ans = head;
        Node* oldNext = flatten(head->next);
        Node* newNext = flatten(head->child);
        if (newNext != nullptr) {          // 若child不为空,next指向flatten(child)
            head->next = newNext;
            newNext->prev = head;
            head->child = nullptr;          // child重新指向null
            while (head->next != nullptr) { // head移动到新连接next的末端
                head = head->next;
            }
        }
        if (oldNext != nullptr) {         // 新next末端指向flatten(旧next)
            head->next = oldNext;
            oldNext->prev = head;
        }

        return ans;
    }
};

class Solution {
public:
    Node* flatten(Node* head) { // 迭代
        if (head == nullptr) {
            return nullptr;
        }
        Node* prev = new Node();    // 用prev遍历整个链表
        stack<Node*> nodeStack;     // 必须用栈使后加入的child先遍历
        nodeStack.push(head);

        while (!nodeStack.empty()) {
            Node* node = nodeStack.top();
            nodeStack.pop();
            if (node->next != nullptr) {
                nodeStack.push(node->next);
            }
            if (node->child != nullptr) {
                nodeStack.push(node->child);
                node->child = nullptr;
            }
            prev->next = node;
            node->prev = prev;
            prev = prev->next;
        }

        head->prev = nullptr;
        return head;
    }
};
