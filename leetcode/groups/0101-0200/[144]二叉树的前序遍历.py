# 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。 
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：root = [1,null,2,3]
# 输出：[1,2,3]
#  
# 
#  示例 2： 
# 
#  
# 输入：root = []
# 输出：[]
#  
# 
#  示例 3： 
# 
#  
# 输入：root = [1]
# 输出：[1]
#  
# 
#  示例 4： 
# 
#  
# 输入：root = [1,2]
# 输出：[1,2]
#  
# 
#  示例 5： 
# 
#  
# 输入：root = [1,null,2]
# 输出：[1,2]
#  
# 
#  
# 
#  提示： 
# 
#  
#  树中节点数目在范围 [0, 100] 内 
#  -100 <= Node.val <= 100 
#  
# 
#  
# 
#  进阶：递归算法很简单，你可以通过迭代算法完成吗？ 
#  Related Topics 栈 树 深度优先搜索 二叉树 
#  👍 617 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:    # 迭代
    def preorderTraversal(self, root: TreeNode) -> List[int]:   # 迭代
        container = []
        stack = [root]
        while stack:
            node = stack.pop()
            if not node:
                continue
            container.append(node.val)
            stack.append(node.right)    # 先加入right, 使迭代顺序在left之后
            stack.append(node.left)
        return container
# leetcode submit region end(Prohibit modification and deletion)


class Solution:
    def preorderTraversal(self, root: TreeNode) -> List[int]:   # 递归
        container = []

        def search(node):
            if node is None:
                return
            container.append(node.val)
            search(node.left)
            search(node.right)

        search(root)
        return container
