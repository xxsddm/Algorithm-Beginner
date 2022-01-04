# 给定一棵树的前序遍历 preorderorder 与中序遍历 inorderrder。请构造二叉树并返回其根节点。 
# 
#  
# 
#  示例 1: 
# 
#  
# Input: preorderorder = [3,9,20,15,7], inorderrder = [9,3,15,20,7]
# Output: [3,9,20,null,null,15,7]
#  
# 
#  示例 2: 
# 
#  
# Input: preorderorder = [-1], inorderrder = [-1]
# Output: [-1]
#  
# 
#  
# 
#  提示: 
# 
#  
#  1 <= preorderorder.length <= 3000 
#  inorderrder.length == preorderorder.length 
#  -3000 <= preorderorder[i], inorderrder[i] <= 3000 
#  preorderorder 和 inorderrder 均无重复元素 
#  inorderrder 均出现在 preorderorder 
#  preorderorder 保证为二叉树的前序遍历序列 
#  inorderrder 保证为二叉树的中序遍历序列 
#  
#  Related Topics 树 数组 哈希表 分治 二叉树 👍 1163 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

class Solution:
    def buildTree(self, preorder: List[int], inorder: List[int]) -> TreeNode:       # 递归
        if not preorder:
            return None
        root = TreeNode(preorder[0])
        leftlength = inorder.index(root.val)    # 可以考虑hash, 找起来更快
        root.left = self.buildTree(preorder[1: leftlength + 1], inorder[: leftlength])
        root.right = self.buildTree(preorder[leftlength + 1:], inorder[leftlength + 1:])
        return root

# leetcode submit region end(Prohibit modification and deletion)
