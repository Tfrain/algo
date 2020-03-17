package niuke;


import java.util.ArrayList;

public class FindPath {
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }
    private ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> findPath(TreeNode root, int target) {
        backTracking(root, target, new ArrayList<>());
        return ret;
    }
    private void backTracking(TreeNode node, int target, ArrayList<Integer> path) {
        if (node == null || target < 0) return;
        path.add(node.val);
        target -= node.val;
        if (target == 0 && node.left == null && node.right == null) {
            ret.add(new ArrayList<>(path));
        } else {
            backTracking(node.left, target, path);
            backTracking(node.right, target, path);
        }
        path.remove(path.size() - 1);
    }


}
