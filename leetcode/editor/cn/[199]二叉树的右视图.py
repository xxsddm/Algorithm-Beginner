# 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。 
# 
#  
# 
#  示例 1: 
# 
#  
# 
#  
# 输入: [1,2,3,null,5,null,4]
# 输出: [1,3,4]
#  
# 
#  示例 2: 
# 
#  
# 输入: [1,null,3]
# 输出: [1,3]
#  
# 
#  示例 3: 
# 
#  
# 输入: []
# 输出: []
#  
# 
#  
# 
#  提示: 
# 
#  
#  二叉树的节点个数的范围是 [0,100] 
#  -100 <= Node.val <= 100 
#  
#  Related Topics 树 深度优先搜索 广度优先搜索 二叉树 
#  👍 517 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

class Solution:
    def rightSideView(self, root: TreeNode) -> List[int]:
        if not root:
            return []
        container = []
        layer = [root]
        while layer:
            container.append(layer[-1].val)    # 只加入每层最末端点的值
            temp = []
            for node in layer:
                if node.left:
                    temp.append(node.left)
                if node.right:
                    temp.append(node.right)

            layer = temp
        return container

# leetcode submit region end(Prohibit modification and deletion)
