package niuke;

import java.util.ArrayList;
import java.util.Stack;

// List.addAll方法——添加所有元素到列表中
public class PrintListFromTailToHead {
    public class ListNode {
       int val;
       ListNode next = null;
       ListNode(int val) {
            this.val = val;
       }
   }
/*  使用递归
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> ret = new ArrayList<>();
        if(listNode != null) {
            ret.addAll(printListFromTailToHead(listNode.next));
            ret.add(listNode.val);
        }
        return ret;
    }*/

    //头插法
    /*public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        //头插法构建逆序链表，必须完全理解并掌握
        ListNode head = new ListNode(-1);
        while(listNode != null) {
            ListNode memo = listNode.next;
            listNode.next = head.next;
            head.next = listNode;
            listNode = memo;
        }
        ArrayList<Integer> ret = new ArrayList<>();
        head = head.next;
        while (head != null) {
            ret.add(head.val);
            head = head.next;
        }
        return ret;
    }*/
    //使用栈,牛客网不能使用栈
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        Stack<Integer> stack = new Stack<>();
        while(listNode != null) {
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        ArrayList<Integer> ret = new ArrayList<>();
        while (!stack.isEmpty()) {
            ret.add(stack.pop());
        }
        return ret;
    }


}
