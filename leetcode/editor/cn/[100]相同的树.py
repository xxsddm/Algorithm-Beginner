# 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。 
# 
#  如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。 
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：p = [1,2,3], q = [1,2,3]
# 输出：true
#  
# 
#  示例 2： 
# 
#  
# 输入：p = [1,2], q = [1,null,2]
# 输出：false
#  
# 
#  示例 3： 
# 
#  
# 输入：p = [1,2,1], q = [1,1,2]
# 输出：false
#  
# 
#  
# 
#  提示： 
# 
#  
#  两棵树上的节点数目都在范围 [0, 100] 内 
#  -104 <= Node.val <= 104 
#  
#  Related Topics 树 深度优先搜索 广度优先搜索 二叉树 
#  👍 667 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

class Solution:
    def isSameTree(self, p: TreeNode, q: TreeNode) -> bool:     # 递归
        if not p and not q:
            return True
        elif not p or not q:
            return False
        if p.val == q.val:
            return self.isSameTree(p.left, q.left) and self.isSameTree(p.right, q.right)
        else:
            return False

# leetcode submit region end(Prohibit modification and deletion)


class Solution:
    def isSameTree(self, p: TreeNode, q: TreeNode) -> bool:     # 迭代
        nodes = [p, q]
        while nodes:
            node1 = nodes.pop()
            node2 = nodes.pop()
            if not node1 and node2:
                return False
            elif node1 and not node2:
                return False
            elif node1 and node2:
                if node1.val != node2.val:
                    return False
                nodes.append(node1.left)  # 需要对比的节点成对入栈
                nodes.append(node2.left)

                nodes.append(node1.right)
                nodes.append(node2.right)
        return True

