# 给定一个二叉树，判断其是否是一个有效的二叉搜索树。 
# 
#  假设一个二叉搜索树具有如下特征： 
# 
#  
#  节点的左子树只包含小于当前节点的数。 
#  节点的右子树只包含大于当前节点的数。 
#  所有左子树和右子树自身必须也是二叉搜索树。 
#  
# 
#  示例 1: 
# 
#  输入:
#     2
#    / \
#   1   3
# 输出: true
#  
# 
#  示例 2: 
# 
#  输入:
#     5
#    / \
#   1   4
#      / \
#     3   6
# 输出: false
# 解释: 输入为: [5,1,4,null,null,3,6]。
#      根节点的值为 5 ，但是其右子节点值为 4 。
#  
#  Related Topics 树 深度优先搜索 二叉搜索树 二叉树 👍 1173 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right


class Solution:
    def isValidBST(self, root: TreeNode) -> bool:       # 迭代
        if not root:
            return True
        container = [root]
        maxnum = - math.inf
        while container:
            node = container.pop()
            if node:
                if node.right:
                    container.append(node.right)
                container.append(node)
                container.append(None)      # 空值标记父节点
                if node.left:
                    container.append(node.left)
            else:
                node = container.pop()
                if node.val <= maxnum:
                    return False
                maxnum = node.val
        return True

# leetcode submit region end(Prohibit modification and deletion)


class Solution:
    def isValidBST(self, root: TreeNode) -> bool:  # 递归, 利用中序遍历性质, 前面遍历到的点一定在后面点的left侧
        self.maxnum = - math.inf
        return self.dfs(root)

    def dfs(self, node):
        if not node:
            return True
        if not self.dfs(node.left):
            return False
        if node.val <= self.maxnum:
            return False
        self.maxnum = node.val
        if not self.dfs(node.right):
            return False
        return True


class Solution:
    def isValidBST(self, root: TreeNode) -> bool:  # 硬刚
        if not root:
            return True
        if root.left:
            temp = root.left
            while temp.right:
                temp = temp.right
            if temp.val >= root.val:
                return False
        if root.right:
            temp = root.right
            while temp.left:
                temp = temp.left
            if temp.val <= root.val:
                return False
        return self.isValidBST(root.left) and self.isValidBST(root.right)
