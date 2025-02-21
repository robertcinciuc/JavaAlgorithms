package robertcinciuc.problems.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

public class LowestCommonAncestorBinaryTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        List<TreeNode> pathToP = getToNode(new ArrayList<>(), root, p);
        List<TreeNode> pathToQ = getToNode(new ArrayList<>(), root, q);

        int len = pathToP.size();
        if(pathToP.size() > pathToQ.size()){
            len = pathToQ.size();
        }

        TreeNode lastCommon = root;
        for(int i = 0; i < len; ++i){
            if(pathToP.get(i) != pathToQ.get(i)){
                return lastCommon;
            }else{
                lastCommon = pathToP.get(i);
            }
        }

        return lastCommon;
    }

    public List<TreeNode> getToNode(List<TreeNode> path, TreeNode currentNode, TreeNode destination){
        path.add(currentNode);
        if(destination != currentNode){
            List<TreeNode> response;
            if(currentNode.left != null){
                response = getToNode(path, currentNode.left, destination);
                if(response != null){
                    return response;
                }
            }
            if(currentNode.right != null){
                response = getToNode(path, currentNode.right, destination);
                if(response != null){
                    return response;
                }
            }
        } else {
            return path;
        }
        path.remove(path.size() - 1);
        return null;
    }

    public static void main(String[] args){
        TreeNode treeNode = new TreeNode(3);
        TreeNode treeNodel = new TreeNode(9);
        TreeNode treeNoder = new TreeNode(20);
        treeNode.left = treeNodel;
        treeNode.right = treeNoder;
        TreeNode treeNoderl = new TreeNode(15);
        TreeNode treeNoderr = new TreeNode(7);
        treeNoder.left = treeNoderl;
        treeNoder.right = treeNoderr;

        LowestCommonAncestorBinaryTree lowestCommonAncestorBinaryTree = new LowestCommonAncestorBinaryTree();
        System.out.println(lowestCommonAncestorBinaryTree.lowestCommonAncestor(treeNode, treeNode, treeNoderr).val);
    }
}
