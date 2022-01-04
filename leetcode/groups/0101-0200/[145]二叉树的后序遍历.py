# 给定一个二叉树，返回它的 后序 遍历。 
# 
#  示例: 
# 
#  输入: [1,null,2,3]  
#    1
#     \
#      2
#     /
#    3 
# 
# 输出: [3,2,1] 
# 
#  进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
#  Related Topics 栈 树 深度优先搜索 二叉树 
#  👍 639 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

class Solution:
    def postorderTraversal(self, root: TreeNode) -> List[int]:    # 迭代
        if not root:
            return []
        container = []
        stack = [root]
        while stack:
            node = stack.pop()
            if node is None:
                node = stack.pop()
                container.append(node.val)
                continue
            stack.append(node)
            stack.append(None)    # 仅当前节点时加入None, 用于标记当前节点
            if node.right:
                stack.append(node.right)
            if node.left:
                stack.append(node.left)
        return container

# leetcode submit region end(Prohibit modification and deletion)


class Solution:
    def postorderTraversal(self, root: TreeNode) -> List[int]:    # 迭代(伪)
        container = []
        stack = [root]
        while stack:
            node = stack.pop()
            if not node:
                continue
            container.append(node.val)
            stack.append(node.left)
            stack.append(node.right)
        return container[::-1]  # 前面同前序遍历的迭代算法, 最后翻转


class Solution:
    def postorderTraversal(self, root: TreeNode) -> List[int]:    # 递归
        container = []

        def search(node):
            if node is None:
                return
            search(node.left)
            search(node.right)
            container.append(node.val)

        search(root)
        return container


class Solution:
    def postorderTraversal(self, root: TreeNode) -> List[int]:    # 迭代
        if not root:
            return []
        container = []
        ignore = {None}    # 一般若节点有左右节点, 则还需继续访问左右节点再访问该节点, 但若左右节点已被访问则可忽略, 也不必再加入
        stack = [root]
        while stack:
            node = stack[-1]
            if node.right is not None and node.right not in ignore:
                stack.append(node.right)
            if node.left is not None and node.left not in ignore:
                stack.append(node.left)
            if node.right in ignore and node.left in ignore:
                temp = stack.pop()
                container.append(temp.val)
                ignore.add(temp)
        return container
