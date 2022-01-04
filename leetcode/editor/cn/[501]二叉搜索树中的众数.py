# 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。 
# 
#  假定 BST 有如下定义： 
# 
#  
#  结点左子树中所含结点的值小于等于当前结点的值 
#  结点右子树中所含结点的值大于等于当前结点的值 
#  左子树和右子树都是二叉搜索树 
#  
# 
#  例如： 
# 给定 BST [1,null,2,2], 
# 
#     1
#     \
#      2
#     /
#    2
#  
# 
#  返回[2]. 
# 
#  提示：如果众数超过1个，不需考虑输出顺序 
# 
#  进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内） 
#  Related Topics 树 深度优先搜索 二叉搜索树 二叉树 👍 335 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right


class Solution:
    def findMode(self, root: TreeNode) -> List[int]:      # 递归
        self.mode = None    # 前一个数
        self.num = 1        # 前一个数当前数量
        self.count = 0      # 当前众数数量
        self.container = []
        self.dfs(root)
        return self.container

    def dfs(self, node):
        if node.left:
            self.dfs(node.left)

        if node.val == self.mode:
            self.count += 1
            if self.count == self.num:
                self.container.append(node.val)
            elif self.count > self.num:
                self.num = self.count
                self.container = [node.val]
        else:
            self.mode = node.val
            self.count = 1
            if self.count == self.num:
                self.container.append(node.val)

        if node.right:
            self.dfs(node.right)

# leetcode submit region end(Prohibit modification and deletion)


class Solution:
    def findMode(self, root: TreeNode) -> List[int]:      # 迭代
        mode = None    # 前一个数
        num = 1        # 前一个数当前数量
        count = 0      # 当前众数数量
        container = []
        nodes = [root]
        while nodes:
            node = nodes.pop()
            if node:
                if node.right:
                    nodes.append(node.right)
                nodes.append(node)
                nodes.append(None)
                if node.left:
                    nodes.append(node.left)
            else:
                node = nodes.pop()
                if node.val == mode:
                    count += 1
                    if count == num:
                        container.append(node.val)
                    elif count > num:
                        num = count
                        container = [node.val]
                else:
                    mode = node.val
                    count = 1
                    if count == num:
                        container.append(node.val)
        return container
