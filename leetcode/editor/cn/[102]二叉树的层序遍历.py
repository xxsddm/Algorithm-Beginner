# 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。 
# 
#  
# 
#  示例： 
# 二叉树：[3,9,20,null,null,15,7], 
# 
#  
#     3
#    / \
#   9  20
#     /  \
#    15   7
#  
# 
#  返回其层序遍历结果： 
# 
#  
# [
#   [3],
#   [9,20],
#   [15,7]
# ]
#  
#  Related Topics 树 广度优先搜索 二叉树 
#  👍 964 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

class Solution:
    def levelOrder(self, root: TreeNode) -> List[List[int]]:
        if not root:
            return []
        container = []
        nodes = [root]
        while nodes:
            container.append([node.val for node in nodes])
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
    def levelOrder(self, root: TreeNode) -> List[List[int]]:
        if not root:
            return []
        container = []
        queue = Queue()
        queue.put(root)
        while not queue.empty():      # 不支持 while queue:
            num = queue.qsize()       # 各层节点数量
            temp = []
            for i in range(num):
                node = queue.get()    # 各层节点按顺序移出, 并将子节点加入队列
                temp.append(node.val)
                if node.left:
                    queue.put(node.left)
                if node.right:
                    queue.put(node.right)
            container.append(temp)
        return container
