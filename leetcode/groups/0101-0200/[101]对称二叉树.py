# 给定一个二叉树，检查它是否是镜像对称的。 
# 
#  
# 
#  例如，二叉树 [1,2,2,3,4,4,3] 是对称的。 
# 
#      1
#    / \
#   2   2
#  / \ / \
# 3  4 4  3
#  
# 
#  
# 
#  但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的: 
# 
#      1
#    / \
#   2   2
#    \   \
#    3    3
#  
# 
#  
# 
#  进阶： 
# 
#  你可以运用递归和迭代两种方法解决这个问题吗？ 
#  Related Topics 树 深度优先搜索 广度优先搜索 二叉树 
#  👍 1487 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

class Solution:
    def isSymmetric(self, root: TreeNode) -> bool:      # DFS
        def equal(node1=root, node2=root):
            if not node1 and not node2:
                return True
            if not node1 or not node2:
                return False
            # 若输入的node1, node2位置对称, 则下一轮进行比较的各对, 其位置也对称
            return node1.val == node2.val and equal(node1.left, node2.right) and equal(node1.right, node2.left)
        return equal()

# leetcode submit region end(Prohibit modification and deletion)


class Solution:
    def isSymmetric(self, root: TreeNode) -> bool:      # BFS
        nodes = [root, root]        # 一次取一对对称点进行对比
        while nodes:
            # 若出栈的node1, node2位置对称, 则入栈进行比较的各对, 其位置也对称
            node1 = nodes.pop()
            node2 = nodes.pop()
            if not node1 and not node2:
                continue
            if not node1 or not node2 or node1.val != node2.val:
                return False
            nodes.append(node2.right)
            nodes.append(node1.left)
            nodes.append(node2.left)
            nodes.append(node1.right)
        return True
