package niuke;
//如果是在原来的字符串上进行替换，就有可能覆盖修改在该字符串后面的内存
//如果是创建新的字符串并在新的字符串上进行替换，那么我们可以自己分配足够多的内存
//newIndex > oldIndex没什么必要，边界情况也被str.setLength(newLength);消除了
public class ReplaceBlank {
    static String replaceSpace(StringBuffer str) {
        if(str == null) return null;
        int originalLength = str.length();
        int numberOfBlank = 0;
        for (int i = 0; i < originalLength;i++) {
            if(str.charAt(i) == ' ') numberOfBlank++;
        }
        int newLength = originalLength + 2*numberOfBlank;
        int oldIndex = originalLength - 1;
        int newIndex = newLength - 1;

        str.setLength(newLength);
        // System.out.println(str);
        while (oldIndex >= 0 && newIndex > oldIndex) {
            if(str.charAt(oldIndex) == ' ') {
                str.setCharAt(newIndex--, '0');
                str.setCharAt(newIndex--, '2');
                str.setCharAt(newIndex--, '%');
            } else {
                str.setCharAt(newIndex--, str.charAt(oldIndex));
            }
            oldIndex--;
        }
        return str.toString();
    }

    public static void main(String[] args) {
        StringBuffer str = new StringBuffer("we are happy");
        System.out.println(ReplaceBlank.replaceSpace(str));
    }
}
