# 给定一个二叉树，判断它是否是高度平衡的二叉树。 
# 
#  本题中，一棵高度平衡二叉树定义为： 
# 
#  
#  一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。 
#  
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：root = [3,9,20,null,null,15,7]
# 输出：true
#  
# 
#  示例 2： 
# 
#  
# 输入：root = [1,2,2,3,3,null,null,4,4]
# 输出：false
#  
# 
#  示例 3： 
# 
#  
# 输入：root = []
# 输出：true
#  
# 
#  
# 
#  提示： 
# 
#  
#  树中的节点数在范围 [0, 5000] 内 
#  -104 <= Node.val <= 104 
#  
#  Related Topics 树 深度优先搜索 二叉树 
#  👍 744 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

# 递归 自下而上
class Solution:
    def isBalanced(self, root: TreeNode) -> bool:
        if not root:
            return True
        # 每一步递归都要调用下层点, 但本层递归完后 不需要 下降一层继续递归下层点
        return depth(root) >= 0


def depth(node):
    if not node:
        return 0
    depth1 = depth(node.left)
    depth2 = depth(node.right)
    # 用被标记为负非平衡节点和正常深度标出非平衡树
    # 下层点不会被多次调用depth
    if depth1 == -1 or depth2 == -1 or abs(depth1 - depth2) > 1:
        return -1
    return 1 + max(depth1, depth2)      # 仅平衡时返回正常深度

# leetcode submit region end(Prohibit modification and deletion)


# 迭代 自上而下 效率很低
class Solution:
    def isBalanced(self, root: TreeNode) -> bool:
        if not root:
            return True

        def depth(node):    # 每次调用都是一轮递归
            if not node:
                return 0
            return 1 + max(depth(node.left), depth(node.right))

        nodes = [root, root]        # 每次取出要进行比较的两个子节点
        while nodes:
            node1 = nodes.pop()
            node2 = nodes.pop()
            # 每一步迭代都要调用下层点, 但本层迭代完后 需要 下降一层继续迭代下层点
            # 下层点会被多次调用depth
            if abs(depth(node1) - depth(node2)) > 1:
                return False
            if node1:
                nodes.append(node1.left)
                nodes.append(node1.right)
            if node2:
                nodes.append(node2.left)
                nodes.append(node2.right)
        return True


# 递归 自上而下 效率很低
class Solution:
    def isBalanced(self, root: TreeNode) -> bool:
        if not root:
            return True
        left = depth(root.left)
        right = depth(root.right)
        # 每一步递归都要调用下层点, 但本层递归完后 需要 下降一层继续递归下层点
        # 下层点会被多次调用depth
        return -1 <= left - right <= 1 and self.isBalanced(root.left) and self.isBalanced(root.right)


def depth(node):    # 每次调用都是一轮递归
    if not node:
        return 0
    return 1 + max(depth(node.left), depth(node.right))

