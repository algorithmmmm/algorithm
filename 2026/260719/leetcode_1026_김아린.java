/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int answer = 0;

    public int maxAncestorDiff(TreeNode root) {
        dfs(root, root.val, root.val);
        return answer;
    }

    public void dfs(TreeNode node, int min, int max) { // 노드, 지금까지 경로에서 최솟값, 지금까지 경로에서 최댓값
        if(node == null) return;

        int cur = node.val;

        // 현재 값에서 나올 수 있는 최대 차이 값
        int diff = Math.max(Math.abs(cur - min), Math.abs(cur - max));
        answer = Math.max(answer, diff);

        // 최대 최소 갱신
        min = Math.min(min, cur);
        max = Math.max(max, cur);

        dfs(node.left, min, max); // 왼쪽 탐색
        dfs(node.right, min, max); // 오른쪽 탐색
    }
}