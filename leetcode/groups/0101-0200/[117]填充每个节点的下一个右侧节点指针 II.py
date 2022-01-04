# 给定一个二叉树 
# 
#  
# struct Node {
#   int val;
#   Node *left;
#   Node *right;
#   Node *next;
# } 
# 
#  填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。 
# 
#  初始状态下，所有 next 指针都被设置为 NULL。 
# 
#  
# 
#  进阶： 
# 
#  
#  你只能使用常量级额外空间。 
#  使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。 
#  
# 
#  
# 
#  示例： 
# 
#  
# 
#  
# 输入：root = [1,2,3,4,5,null,7]
# 输出：[1,#,2,3,#,4,5,7,#]
# 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化输出按层序遍历顺序（由 next 指
# 针连接），'#' 表示每层的末尾。 
# 
#  
# 
#  提示： 
# 
#  
#  树中的节点数小于 6000 
#  -100 <= node.val <= 100 
#  
# 
#  
# 
#  
#  
#  Related Topics 树 深度优先搜索 广度优先搜索 二叉树 
#  👍 433 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
"""
# Definition for a Node.
class Node:
    def __init__(self, val: int = 0, left: 'Node' = None, right: 'Node' = None, next: 'Node' = None):
        self.val = val
        self.left = left
        self.right = right
        self.next = next
"""


class Solution:
    def connect(self, root: 'Node') -> 'Node':    # 链表
        if not root:
            return root
        temp = root
        while temp:
            nexthead = None
            find = False
            while temp:
                for child in (temp.left, temp.right):
                    if child:
                        if not find:
                            nexthead = before = child
                            find = True
                        else:
                            before.next = child
                            before = before.next
                temp = temp.next
            temp = nexthead
        return root
        
# leetcode submit region end(Prohibit modification and deletion)


class Solution:
    def connect(self, root: 'Node') -> 'Node':    # 层序遍历
        if not root:
            return root
        nodes = [root]
        while nodes:
            temp = []
            for i in range(len(nodes) - 1):
                nodes[i].next = nodes[i + 1]
            for node in nodes:
                if node.left:
                    temp.append(node.left)
                if node.right:
                    temp.append(node.right)
            nodes = temp
        return root
