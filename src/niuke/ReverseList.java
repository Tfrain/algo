package niuke;

public class ReverseList {
    class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
    //头插法
    // public ListNode reverseList(ListNode head) {
    //     ListNode newNode = new ListNode(-1);
    //     while (head != null) {
    //         ListNode next = head.next;
    //         head.next = newNode.next;
    //         newNode.next = head;
    //         head = next;
    //     }
    //     return newNode.next;
    // }
    // 递归法
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        head.next = null;
        ListNode newNode = reverseList(next);
        next.next = head;
        return newNode;
    }
}
