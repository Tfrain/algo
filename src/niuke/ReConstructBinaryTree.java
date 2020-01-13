package niuke;

import java.util.HashMap;
import java.util.Map;

public class ReConstructBinaryTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre == null || pre.length == 0 || in == null || in.length == 0 || pre.length != in.length) {
            return null;
        }
        return reConstructBinaryTree(pre, in, 0, pre.length - 1, 0, in.length - 1);
    }
    // 前序遍历中推进出子树，中序遍历分割出子树
    public TreeNode reConstructBinaryTree(int[] pre, int[] in, int preS, int preE, int inS, int inE) {
       if(preS > preE || inS > inE) return null;
       TreeNode root = new TreeNode(pre[preS]);
       int inMid = inS;
       while (root.val != in[inMid]) inMid++;
       int length = inMid - inS;
       root.left = reConstructBinaryTree(pre, in, preS+1, preS+length, inS, inMid-1);
       root.right = reConstructBinaryTree(pre, in, preS+length+1, preE, inMid+1, inE);
       return root;
    }
    /*
    // inL用于中序遍历中确定左、右子树的长度，然后在前序遍历中推进
    private Map<Integer, Integer> indexForInOrders = new HashMap<>();
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        for(int i = 0; i < in.length; i++) {
            indexForInOrders.put(in[i], i);
        }
        return reConstructBinaryTree(pre, 0, in.length - 1, 0);
    }
    // inL用于中序遍历中确定左、右子树的长度，然后在前序遍历中推进
    public TreeNode reConstructBinaryTree(int[] pre, int preL, int preR, int inL) {
        if (preL > preR) return null;
        // 不断获取在前序中，让中序分出左右子树的根
        TreeNode root = new TreeNode(pre[preL]);
        int length = indexForInOrders.get(root.val);
        int leftTreeLength = length - inL;
        root.left = reConstructBinaryTree(pre, preL+1, preL+leftTreeLength, inL);
        root.right = reConstructBinaryTree(pre,preL+leftTreeLength+1, preR, inL+leftTreeLength+1);
        return root;
    }*/
}
