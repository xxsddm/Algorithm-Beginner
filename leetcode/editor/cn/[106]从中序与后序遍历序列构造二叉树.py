# 根据一棵树的中序遍历与后序遍历构造二叉树。 
# 
#  注意: 
# 你可以假设树中没有重复的元素。 
# 
#  例如，给出 
# 
#  中序遍历 inorder = [9,3,15,20,7]
# 后序遍历 postorder = [9,15,7,20,3] 
# 
#  返回如下的二叉树： 
# 
#      3
#    / \
#   9  20
#     /  \
#    15   7
#  
#  Related Topics 树 数组 哈希表 分治 二叉树 👍 549 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right


class Solution:
    def buildTree(self, inorder: List[int], postorder: List[int]) -> TreeNode:      # 递归
        if not postorder:
            return None
        root = TreeNode(postorder[-1])
        leftlength = inorder.index(root.val)    # 可以考虑hash, 找起来更快
        root.left = self.buildTree(inorder[: leftlength], postorder[: leftlength])
        root.right = self.buildTree(inorder[leftlength + 1:], postorder[leftlength: -1])
        return root

# leetcode submit region end(Prohibit modification and deletion)
