# 给定一个非空二叉树, 返回一个由每层节点平均值组成的数组。 
# 
#  
# 
#  示例 1： 
# 
#  输入：
#     3
#    / \
#   9  20
#     /  \
#    15   7
# 输出：[3, 14.5, 11]
# 解释：
# 第 0 层的平均值是 3 ,  第1层是 14.5 , 第2层是 11 。因此返回 [3, 14.5, 11] 。
#  
# 
#  
# 
#  提示： 
# 
#  
#  节点值的范围在32位有符号整数范围内。 
#  
#  Related Topics 树 深度优先搜索 广度优先搜索 二叉树 
#  👍 277 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

class Solution:
    def averageOfLevels(self, root: TreeNode) -> List[float]:
        if not root:
            return []
        container = []
        nodes = [root]
        while nodes:
            container.append(sum(_.val for _ in nodes) / len(nodes))
            temp = []    # 存放各层节点
            for node in nodes:
                if node.left:
                    temp.append(node.left)
                if node.right:
                    temp.append(node.right)
            nodes = temp
        return container

# leetcode submit region end(Prohibit modification and deletion)

from queue import Queue


class Solution:
    def averageOfLevels(self, root: TreeNode) -> List[float]:
        if not root:
            return []
        container = []
        queue = Queue()
        queue.put(root)
        while not queue.empty():      # 不支持 while queue:
            num = queue.qsize()       # 各层节点数量
            cumsum = 0
            for i in range(num):
                node = queue.get()    # 各层节点按顺序移出, 并将子节点加入队列
                cumsum += node.val
                if node.left:
                    queue.put(node.left)
                if node.right:
                    queue.put(node.right)
            container.append(cumsum / num)
        return container