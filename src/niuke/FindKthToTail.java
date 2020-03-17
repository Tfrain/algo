package niuke;

class ListNode {
    int val;
    ListNode next = null;
    ListNode(int val) {
        this.val = val;
    }
}

public class FindKthToTail {
    public ListNode findKthToTail(ListNode head, int k) {
    //    利用双指针，让node1往前走k，之后node2和node1一直走到node1的结尾
    //    最后的node2所指之处便是倒数第k个节点，简单的双指针和数学方法
        if (head == null) {
            return null;
        }
        ListNode node1 = head;
        // k要先减后判断，因为倒数第一时node2和node1指向同一个节点
        while (node1 != null && k-- > 0) {
            node1 = node1.next;
        }
        if (k > 0) return null;

        ListNode node2 = head;
        while (node1 != null) {
            node1 = node1.next;
            node2 = node2.next;
        }
        return node2;

    }

    // public ListNode findKthToTail(ListNode head, int k) {
    //     ListNode newNode = new ListNode(-1);
    //     while (head != null) {
    //         ListNode memo = head.next;
    //         head.next = newNode.next;
    //         newNode.next = head;
    //         head = memo;
    //     }
    //     int i = 1;
    //     while(newNode != null && i <= k) {
    //         newNode = newNode.next;
    //         i++;
    //     }
    //     ListNode tmp = new ListNode(newNode.val);
    //     return tmp;
    // }
}






