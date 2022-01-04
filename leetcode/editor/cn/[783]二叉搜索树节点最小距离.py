# 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。 
# 
#  注意：本题与 530：https://leetcode-cn.com/problems/minimum-absolute-difference-in-
# bst/ 相同 
# 
#  
# 
#  
#  
#  示例 1： 
# 
#  
# 输入：root = [4,2,6,1,3]
# 输出：1
#  
# 
#  示例 2： 
# 
#  
# 输入：root = [1,0,48,null,null,12,49]
# 输出：1
#  
# 
#  
# 
#  提示： 
# 
#  
#  树中节点数目在范围 [2, 100] 内 
#  0 <= Node.val <= 10⁵ 
#  差值是一个正数，其数值等于两值之差的绝对值 
#  
#  
#  
#  Related Topics 树 深度优先搜索 广度优先搜索 二叉搜索树 二叉树 👍 196 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right


class Solution:
    def minDiffInBST(self, root: TreeNode) -> int:      # 递归
        self.before = 200000
        self.mindiff = 200000
        self.dfs(root)
        return self.mindiff

    def dfs(self, node):
        if node.left:
            self.dfs(node.left)
        temp = abs(node.val - self.before)
        if temp < self.mindiff:
            self.mindiff = temp
        self.before = node.val
        if node.right:
            self.dfs(node.right)

# leetcode submit region end(Prohibit modification and deletion)


class Solution:
    def minDiffInBST(self, root: TreeNode) -> int:      # 迭代
        before = 200000
        mindiff = 200000
        container = [root]
        while container:
            node = container.pop()
            if node:
                if node.right:
                    container.append(node.right)
                container.append(node)
                container.append(None)
                if node.left:
                    container.append(node.left)
            else:
                node = container.pop()
                if (temp := abs(node.val - before)) < mindiff:
                    mindiff = temp
                before = node.val
        return mindiff
