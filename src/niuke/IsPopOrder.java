package niuke;

import java.util.ArrayList;
import java.util.Stack;

public class IsPopOrder {
    public boolean isPopOrder(int[] pushA, int[] popA) {
        // 牛客网需要将其改造成 ArrayList 的，思想没区别
        int n = pushA.length;
        ArrayList<Integer> stack = new ArrayList<>();
        for (int pushIndex = 0, popIndex = 0; pushIndex < n; pushIndex++) {
            stack.add(pushA[pushIndex]);
            // 当栈中不再匹配，再压入新的值，然后继续比较
            while (popIndex < n && !stack.isEmpty() && stack.get(stack.size() - 1) == popA[popIndex]) {
                stack.remove(stack.size() - 1);
                popIndex++;
            }
        }
        return stack.isEmpty();
    }
    // public boolean isPopOrder(int[] pushA, int[] popA) {
    //     int n = pushA.length;
    //     Stack<Integer> stack = new Stack<>();
    //     for (int pushIndex = 0, popIndex = 0; pushIndex < n; pushIndex++) {
    //         stack.push(pushA[pushIndex]);
    //         // 当栈中不再匹配，再压入新的值，然后继续比较
    //         while (popIndex < n && !stack.isEmpty() && stack.peek() == popA[popIndex]) {
    //             stack.pop();
    //             popIndex++;
    //         }
    //     }
    //     return stack.isEmpty();
    // }
}
