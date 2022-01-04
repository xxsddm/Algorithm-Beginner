# 给定一个二叉树，返回所有从根节点到叶子节点的路径。 
# 
#  说明: 叶子节点是指没有子节点的节点。 
# 
#  示例: 
# 
#  输入:
# 
#    1
#  /   \
# 2     3
#  \
#   5
# 
# 输出: ["1->2->5", "1->3"]
# 
# 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3 
#  Related Topics 树 深度优先搜索 字符串 二叉树 
#  👍 556 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right


class Solution:
    def binaryTreePaths(self, root: TreeNode) -> List[str]:
        container = []      # 存放所有路径
        if not root:
            return []
        if not root.left and not root.right:
            return [str(root.val)]
        for subpath in self.binaryTreePaths(root.left):
            container.append(str(root.val) + '->' + subpath)
        for subpath in self.binaryTreePaths(root.right):
            container.append(str(root.val) + '->' + subpath)
        return container

# leetcode submit region end(Prohibit modification and deletion)


class Solution:
    def binaryTreePaths(self, root: TreeNode) -> List[str]:     # 迭代
        if not root:
            return []
        container = []
        nodes = [root]
        paths = [str(root.val)]     # 存放所有路径
        while nodes:
            node = nodes.pop()
            path = paths.pop()
            if not node.left and not node.right:
                container.append(path)
            if node.left:
                nodes.append(node.left)
                paths.append(path + "->" + str(node.left.val))
            if node.right:
                nodes.append(node.right)
                paths.append(path + "->" + str(node.right.val))
        return container

