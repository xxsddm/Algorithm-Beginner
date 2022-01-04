# 给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。 
# 
#  假设二叉树中至少有一个节点。 
# 
#  
# 
#  示例 1: 
# 
#  
# 
#  
# 输入: root = [2,1,3]
# 输出: 1
#  
# 
#  示例 2: 
# 
#  
# 
#  
# 输入: [1,2,3,4,null,5,6,null,null,7]
# 输出: 7
#  
# 
#  
# 
#  提示: 
# 
#  
#  二叉树的节点个数的范围是 [1,104] 
#  -231 <= Node.val <= 231 - 1 
#  
#  Related Topics 树 深度优先搜索 广度优先搜索 二叉树 
#  👍 193 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

class Solution:
    def findBottomLeftValue(self, root: TreeNode) -> int:       # 递归
        self.depth = -1
        self.container = []
        self.dfs(root, 0)
        return self.container[0]

    def dfs(self, node, depth):
        if not node.left and not node.right:
            # 递归顺序从左至右, 从上到下, 因此存在多个相同depth的节点时, 最早保留的即左下角
            if depth > self.depth:
                self.depth = depth
                self.container = [node.val]
            return
        if node.left:
            self.dfs(node.left, depth + 1)
        if node.right:
            self.dfs(node.right, depth + 1)

# leetcode submit region end(Prohibit modification and deletion)


class Solution:
    def findBottomLeftValue(self, root: TreeNode) -> int:       # 迭代
        nodes = deque([root])
        while nodes:
            num = len(nodes)
            for i in range(num):
                node = nodes.popleft()
                if i == 0:
                    ans = node.val
                if node.left:
                    nodes.append(node.left)
                if node.right:
                    nodes.append(node.right)
        return ans

