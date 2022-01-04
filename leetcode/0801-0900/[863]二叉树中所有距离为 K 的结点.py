# 给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 K 。 
# 
#  返回到目标结点 target 距离为 K 的所有结点的值的列表。 答案可以以任何顺序返回。 
# 
#  
# 
#  
#  
# 
#  示例 1： 
# 
#  输入：root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
# 输出：[7,4,1]
# 解释：
# 所求结点为与目标结点（值为 5）距离为 2 的结点，
# 值分别为 7，4，以及 1
# 
# 
# 
# 注意，输入的 "root" 和 "target" 实际上是树上的结点。
# 上面的输入仅仅是对这些对象进行了序列化描述。
#  
# 
#  
# 
#  提示： 
# 
#  
#  给定的树是非空的。 
#  树上的每个结点都具有唯一的值 0 <= node.val <= 500 。 
#  目标结点 target 是树上的结点。 
#  0 <= K <= 1000. 
#  
#  Related Topics 树 深度优先搜索 广度优先搜索 二叉树 
#  👍 318 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def distanceK(self, root: TreeNode, target: TreeNode, k: int) -> List[int]:
        def parent(node):
            if node.left:
                parents[node.left.val] = node
                parent(node.left)
            if node.right:
                parents[node.right.val] = node
                parent(node.right)

        def find(node, parentnode, distance):
            if node is None:
                return
            if distance == k:
                container.append(node.val)
                return

            # 如果当前节点沿着正常节点顺序递归
            if node.left != parentnode:
                find(node.left, node, distance + 1)
            if node.right != parentnode:
                find(node.right, node, distance + 1)

            # 颠倒节点顺序, 查看target父节点部分(往回查找), 当前节点为target子节点部分则不需要
            if parents[node.val] != parentnode:
                find(parents[node.val], node, distance + 1)

        parents = {root.val: None}
        container = []
        parent(root)
        # 这里的父节点必须设置为None, 同时防止root==target
        find(target, None, 0)
        return container

# leetcode submit region end(Prohibit modification and deletion)
