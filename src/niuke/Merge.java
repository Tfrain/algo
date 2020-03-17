package niuke;

public class Merge {
    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
    // 递归，下面是迭代，迭代时间更短一些
    public ListNode merge(ListNode list1, ListNode list2) {
       if (list1 == null) {
           return list2;
       }
       if (list2 == null) {
           return list1;
       }

       if(list1.val <= list2.val) {
           list1.next = merge(list1.next, list2);
           return list1;
       } else {
           list2.next = merge(list1, list2.next);
           return list2;
       }
    }
    // public ListNode merge(ListNode list1, ListNode list2) {
    //     ListNode newList = new ListNode(-1);
    //     ListNode cur = newList;
    //     while (list1 != null && list2 != null) {
    //         if(list1.val >= list2.val) {
    //             cur.next = list2;
    //             list2 = list2.next;
    //         } else {
    //             cur.next = list1;
    //             list1 = list1.next;
    //         }
    //         cur = cur.next;
    //     }
    //     if (list1 != null) {
    //         cur.next = list1;
    //     }
    //     if (list2 != null) {
    //         cur.next = list2;
    //     }
    //     return newList.next;
    // }
}
