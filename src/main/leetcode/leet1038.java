public class leet1038 {
      public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
    class Solution {
        int sum = 0;
        public TreeNode bstToGst(TreeNode root) {
            //DFS right
            dfs(root);
            return root;

        }
        public void dfs(TreeNode root) {
            if(root == null) {
                return;
            }
            dfs(root.right);
            sum += root.val;
            root.val = sum;
            dfs(root.left);


        }
    }

    class Solution2 {
        public TreeNode bstToGst(TreeNode root) {
            reversedInorder(root, 0);
            return root;
        }
        private int reversedInorder(TreeNode node, int sum) {
            if (node == null) { return sum; } // base case.
            node.val += reversedInorder(node.right, sum); // recurse to right subtree.
            return reversedInorder(node.left, node.val); // recurse to left subtree.
        }
    }
}