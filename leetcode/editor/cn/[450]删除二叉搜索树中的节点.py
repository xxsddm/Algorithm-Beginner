# 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的
# 根节点的引用。 
# 
#  一般来说，删除节点可分为两个步骤： 
# 
#  
#  首先找到需要删除的节点； 
#  如果找到了，删除它。 
#  
# 
#  说明： 要求算法时间复杂度为 O(h)，h 为树的高度。 
# 
#  示例: 
# 
#  
# root = [5,3,6,2,4,null,7]
# key = 3
# 
#     5
#    / \
#   3   6
#  / \   \
# 2   4   7
# 
# 给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
# 
# 一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
# 
#     5
#    / \
#   4   6
#  /     \
# 2       7
# 
# 另一个正确答案是 [5,2,6,null,4,null,7]。
# 
#     5
#    / \
#   2   6
#    \   \
#     4   7
#  
#  Related Topics 树 二叉搜索树 二叉树 👍 500 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right


class Solution:
    def deleteNode(self, root: TreeNode, key: int) -> TreeNode:     # 递归
        if not root:
            return None
        if root.val > key:
            root.left = self.deleteNode(root.left, key)
        elif root.val < key:
            root.right = self.deleteNode(root.right, key)
        else:       # 相等时修改右子树, 将左节点拼接到右子树最左端(java中是把右节点拼接到左子树最右端, 都可以)
            if not root.left:
                return root.right
            if not root.right:
                return root.left
            tempright = root.right
            while tempright.left:
                tempright = tempright.left
            tempright.left = root.left
            return root.right
        return root

# leetcode submit region end(Prohibit modification and deletion)
