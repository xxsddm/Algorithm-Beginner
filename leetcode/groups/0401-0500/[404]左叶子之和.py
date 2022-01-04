# 计算给定二叉树的所有左叶子之和。 
# 
#  示例： 
# 
#  
#     3
#    / \
#   9  20
#     /  \
#    15   7
# 
# 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24 
# 
#  
#  Related Topics 树 深度优先搜索 广度优先搜索 二叉树 
#  👍 335 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

class Solution:
    def sumOfLeftLeaves(self, root: TreeNode) -> int:  # 迭代
        if not root:
            return 0
        count = 0
        nodes = [root]
        while nodes:
            node = nodes.pop()
            if node.left:
                if not node.left.left and not node.left.right:
                    count += node.left.val
                else:
                    nodes.append(node.left)
            if node.right:
                nodes.append(node.right)
        return count

# leetcode submit region end(Prohibit modification and deletion)


class Solution:
    def sumOfLeftLeaves(self, root: TreeNode) -> int:       # 递归
        if not root or not root.left and not root.right:
            return 0
        if root.left and not root.left.left and not root.left.right:
            return root.left.val + self.sumOfLeftLeaves(root.right)
        return self.sumOfLeftLeaves(root.left) + self.sumOfLeftLeaves(root.right)

