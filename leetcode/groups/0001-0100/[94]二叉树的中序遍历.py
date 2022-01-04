# 给定一个二叉树的根节点 root ，返回它的 中序 遍历。 
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：root = [1,null,2,3]
# 输出：[1,3,2]
#  
# 
#  示例 2： 
# 
#  
# 输入：root = []
# 输出：[]
#  
# 
#  示例 3： 
# 
#  
# 输入：root = [1]
# 输出：[1]
#  
# 
#  示例 4： 
# 
#  
# 输入：root = [1,2]
# 输出：[2,1]
#  
# 
#  示例 5： 
# 
#  
# 输入：root = [1,null,2]
# 输出：[1,2]
#  
# 
#  
# 
#  提示： 
# 
#  
#  树中节点数目在范围 [0, 100] 内 
#  -100 <= Node.val <= 100 
#  
# 
#  
# 
#  进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
#  Related Topics 栈 树 深度优先搜索 二叉树 
#  👍 1052 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

class Solution:
    def inorderTraversal(self, root: TreeNode) -> List[int]:    # 迭代
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
            if node.right:
                stack.append(node.right)

            stack.append(node)
            stack.append(None)

            if node.left:
                stack.append(node.left)

        return container
# leetcode submit region end(Prohibit modification and deletion)


class Solution:
    def inorderTraversal(self, root: TreeNode) -> List[int]:    # 递归
        container = []

        def search(node):
            if node is None:
                return
            search(node.left)
            container.append(node.val)
            search(node.right)

        search(root)
        return container


class Solution:    # 迭代
    def inorderTraversal(self, root: TreeNode) -> List[int]:
        if not root:
            return []
        container = []
        ignore = {None}    # 一般若节点有左右节点, 则还需继续访问左节点再访问该节点再访问右节点, 但若左右节点已被访问则可忽略, 也不必再加入
        stack = [root]
        while stack:
            node = stack.pop()
            if node.right is not None and node.right not in ignore:    # 右节点从一开始就可以忽略, 因为优先级最低
                stack.append(node.right)
                ignore.add(node.right)
            stack.append(node)
            if node.left is not None and node.left not in ignore:
                stack.append(node.left)
            if node.left in ignore:
                temp = stack.pop()
                container.append(temp.val)
                ignore.add(temp)
        return container
