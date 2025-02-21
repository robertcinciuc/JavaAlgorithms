package robertcinciuc.problems.leetcode.tree;

public class LowestCommonAncestorBinaryTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static class TriValue{
        public TreeNode node;
        public boolean foundPBelow;
        public boolean foundQBelow;

        public TriValue(TreeNode node, boolean foundPBelow, boolean foundQBelow){
            this.node = node;
            this.foundPBelow = foundPBelow;
            this.foundQBelow = foundQBelow;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TriValue triValue = recursion(root, p, q);
        return triValue.node;
    }

    public TriValue recursion(TreeNode currentNode, TreeNode p, TreeNode q){
        TriValue response;
        boolean foundPBelow = false;
        boolean foundQBelow = false;

        if(currentNode == p){
            foundPBelow = true;
        }
        if(currentNode == q){
            foundQBelow = true;
        }

        if(currentNode.left != null){
            response = recursion(currentNode.left, p, q);
            if(response != null){
                if(response.node != null){
                    return response;
                }
                if(response.foundPBelow){
                    foundPBelow = true;
                }
                if(response.foundQBelow){
                    foundQBelow = true;
                }
            }
        }
        if(currentNode.right != null){
            response = recursion(currentNode.right, p, q);
            if(response != null){
                if(response.node != null){
                    return response;
                }
                if(response.foundPBelow){
                    foundPBelow = true;
                }
                if(response.foundQBelow){
                    foundQBelow = true;
                }
            }
        }

        if(foundPBelow && foundQBelow){
            return new TriValue(currentNode, true, true);
        }

        return new TriValue(null, foundPBelow, foundQBelow);
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
