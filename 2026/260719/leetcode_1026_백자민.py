# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def maxAncestorDiff(self, root: Optional[TreeNode]) -> int:
        def dfs(node, mn, mx):
            if node is None:
                return mx - mn

            mn = min(mn, node.val)
            mx = max(mx, node.val)

            return max(
                dfs(node.left, mn, mx),
                dfs(node.right, mn, mx)
            )

        return dfs(root, root.val, root.val)