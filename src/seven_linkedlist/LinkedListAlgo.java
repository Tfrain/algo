package seven_linkedlist;

/**
 *  1) 单链表反转
 *  2）链表中环的检测
 *  3）两个有序的链表合并
 *  4）删除链表倒数第n个结点
 *  5）求链表中的中间结点
 */
public class LinkedListAlgo {
    public static class Node {
        private int data;
        private Node next;

        public Node (int data, Node next) {
            this.data = data;
            this.next = next;
        }
        public int getData() {
            return data;
        }
    }

    public static Node reverse(Node list) {
        Node curr = list, pre = null;
        while (curr != null) {
            //只是为了传宗接代
            Node next = curr.next;
            //继承前辈们的财富，斩断前世的联系
            curr.next = pre;
            //传承留在pre身上，上前线的也肯定是他
            pre = curr;
            //继续继承，但也可能三代而亡
            curr = next;
        }
        return pre;
    }
    public static boolean CheckCircle(Node list) {
        if (list == null) return false;
        Node fast = list, slow = list;
        //非环情况弹出，末节点注意可能为 null，都是为了非环考虑
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) return true;
        }
        return false;
    }

    public static Node mergeSortedLists(Node la, Node lb) {
        if (la == null) return lb;
        if (lb == null) return la;
        //因为是交错指向问题，所以需要变量，也需要head这个头
        Node p = la;
        Node q = lb;
        //因为需要头，所以要设置第一个的变量
        Node head;
        if (p.data < q.data) {
            head = p;
            p = p.next;
        } else {
            head = q;
            q = q.next;
        }
        // 因为头的位置固定，穿起来还需要临时Node变量
        // 注意这一切都因为while这个循环，一个没错，多个就要借助临时变量了
        Node r = head;
        while (p != null && q != null) {
            if (p.data < q.data) {
                r.next = p;
                p = p.next;
            } else {
                r.next = q;
                q = q.next;
            }
        }
        if (p != null) {
            r.next = p;
        } else {
            r.next = q;
        }
        return head;
    }

    public static Node delectLastKth(Node list, int k) {
        Node fast = list;
        int i = 1;
        //通过正数的相对的移动来删除，这么做也可以排查超出长度的情况，一举两得
        while (fast != null && i < k) {
            fast = fast.next;
            i++;
        }
        //正向数没有这个数字，倒数肯定也没有
        if (fast == null) return list;
        //slow显然是为了相对移动，最后确认是靠他，他就是要删元素位置
        //prev是临时变量，用来删除元素，是要删除元素的前一个元素位置。
        Node slow = list;
        Node prev = null;
        //slow 不可能跑到fast的前面
        while (fast.next != null) {
            fast = fast.next;
            prev = slow;
            slow = slow.next;
        }
        //考虑倒数第n个情况
        if (prev == null) {
            list = list.next;
        } else {
            //倒数第一个也能考虑到，但负数或零删除的都是最后一个元素。
            prev.next = prev.next.next;
        }
        return list;
    }
}
