package robertcinciuc.problems.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeRightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        dfs(root, 0, result);

        return result;
    }

    private void dfs(TreeNode node, int level, List<Integer> result) {
        if (node == null) {
            return;
        }

        if (level == result.size()) {
            result.add(node.val);
        }

        dfs(node.right, level + 1, result);

        dfs(node.left, level + 1, result);
    }

    public static void main(String[] args){
        BinaryTreeRightSideView solution = new BinaryTreeRightSideView();
        solution.rightSideView(null);
    }
}
