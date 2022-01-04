# 给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值。 
# 
#  
# 
#  示例1： 
# 
#  
# 输入: root = [1,3,2,5,3,null,9]
# 输出: [1,3,9]
# 解释:
#           1
#          / \
#         3   2
#        / \   \  
#       5   3   9 
#  
# 
#  示例2： 
# 
#  
# 输入: root = [1,2,3]
# 输出: [1,3]
# 解释:
#           1
#          / \
#         2   3
#  
# 
#  示例3： 
# 
#  
# 输入: root = [1]
# 输出: [1]
#  
# 
#  示例4： 
# 
#  
# 输入: root = [1,null,2]
# 输出: [1,2]
# 解释:      
#            1 
#             \
#              2     
#  
# 
#  示例5： 
# 
#  
# 输入: root = []
# 输出: []
#  
# 
#  
# 
#  提示： 
# 
#  
#  二叉树的节点个数的范围是 [0,104] 
#  -231 <= Node.val <= 231 - 1 
#  
#  Related Topics 树 深度优先搜索 广度优先搜索 二叉树 
#  👍 140 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

class Solution:
    def largestValues(self, root: TreeNode) -> List[int]:
        if not root:
            return []
        container = []
        nodes = [root]
        while nodes:
            container.append(max(_.val for _ in nodes))
            temp = []    # 存放各层节点
            for node in nodes:
                if node.left:
                    temp.append(node.left)
                if node.right:
                    temp.append(node.right)
            nodes = temp
        return container
# leetcode submit region end(Prohibit modification and deletion)
