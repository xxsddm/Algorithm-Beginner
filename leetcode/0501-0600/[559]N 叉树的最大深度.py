# 给定一个 N 叉树，找到其最大深度。 
# 
#  最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。 
# 
#  N 叉树输入按层序遍历序列化表示，每组子节点由空值分隔（请参见示例）。 
# 
#  
# 
#  示例 1： 
# 
#  
# 
#  
# 输入：root = [1,null,3,2,4,null,5,6]
# 输出：3
#  
# 
#  示例 2： 
# 
#  
# 
#  
# 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,
# null,13,null,null,14]
# 输出：5
#  
# 
#  
# 
#  提示： 
# 
#  
#  树的深度不会超过 1000 。 
#  树的节点数目位于 [0, 104] 之间。 
#  
#  Related Topics 树 深度优先搜索 广度优先搜索 
#  👍 181 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
"""
# Definition for a Node.
class Node:
    def __init__(self, val=None, children=None):
        self.val = val
        self.children = children
"""


class Solution:
    def maxDepth(self, root: 'Node') -> int:  # DFS
        if not root:
            return 0
        if not root.children:
            return 1
        return 1 + max(self.maxDepth(node) for node in root.children)

# leetcode submit region end(Prohibit modification and deletion)


class Solution:
    def maxDepth(self, root: 'Node') -> int:  # BFS
        if not root:
            return 0
        nodes = [root]
        count = 0
        while nodes:
            count += 1
            temp = []
            for node in nodes:
                for child in node.children:
                    if child:
                        temp.append(child)
            nodes = temp
        return count
