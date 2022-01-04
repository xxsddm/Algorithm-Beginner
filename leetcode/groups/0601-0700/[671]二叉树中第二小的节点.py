# 给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一
# 个。 
# 
#  更正式地说，root.val = min(root.left.val, root.right.val) 总成立。 
# 
#  给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。 
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：root = [2,2,5,null,null,5,7]
# 输出：5
# 解释：最小的值是 2 ，第二小的值是 5 。
#  
# 
#  示例 2： 
# 
#  
# 输入：root = [2,2,2]
# 输出：-1
# 解释：最小的值是 2, 但是不存在第二小的值。
#  
# 
#  
# 
#  提示： 
# 
#  
#  树中节点数目在范围 [1, 25] 内 
#  1 <= Node.val <= 231 - 1 
#  对于树中每个节点 root.val == min(root.left.val, root.right.val) 
#  
#  Related Topics 树 深度优先搜索 二叉树 
#  👍 159 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

class Solution:
    def findSecondMinimumValue(self, root: TreeNode) -> int:
        if root.left is None:
            return -1

        if root.left.val != root.val:   # 找到异于root值的节点则停止递归
            templeft = root.left.val
        else:
            templeft = self.findSecondMinimumValue(root.left)

        if root.right.val != root.val:
            tempright = root.right.val
        else:
            tempright = self.findSecondMinimumValue(root.right)

        if templeft == -1:
            return tempright
        elif tempright == -1:
            return templeft
        else:
            return min(templeft, tempright)


# leetcode submit region end(Prohibit modification and deletion)


class Solution:
    def findSecondMinimumValue(self, root: TreeNode) -> int:
        if root.left is None:
            return -1
        elif root.left.val < root.right.val:
            temp = self.findSecondMinimumValue(root.left)
            if temp != -1:
                return min(temp, root.right.val)
            else:
                return root.right.val
        elif root.left.val > root.right.val:
            temp = self.findSecondMinimumValue(root.right)
            if temp != -1:
                return min(temp, root.left.val)
            else:
                return root.left.val
        else:
            templeft = self.findSecondMinimumValue(root.left)
            tempright = self.findSecondMinimumValue(root.right)
            if templeft == -1:
                return tempright
            elif tempright == -1:
                return templeft
            else:
                return min(templeft, tempright)
