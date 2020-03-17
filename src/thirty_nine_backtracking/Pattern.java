package thirty_nine_backtracking;

public class Pattern {
    private boolean matched = false;
    private char[] pattern;
    private int pLen;//正则表达式长度

    public Pattern(char[] pattern, int pLen) {
        this.pattern = pattern;
        this.pLen = pLen;
    }

    public boolean match(char[] text, int tLen) {
        matched = false;
        rMatch(0, 0, text, tLen);
        return matched;
    }
    private void rMatch(int tI, int pJ, char[] text, int tLen) {
        if (matched) return;
        if (pJ == pLen) { //正则表达式到末尾
            if (tI == tLen) matched = true;// 文本串到了末尾
            return;
        }
        // 主要思想就是匹配的rMatch函数，具体通配符具体调用rMatch函数
        if(pattern[pJ] == '*') { // 任意字符
            for (int k = 0; k <= tLen - tI; k++) {
                rMatch(tI + k, pJ + 1, text, tLen);
            }
        } else if (pattern[pJ] == '?') { // 0或1个字符
            rMatch(tI, pJ + 1, text, tLen);
            rMatch(tI + 1, pJ + 1, text, tLen);
        } else if (tI < tLen && pattern[pJ] == text[tI]) {// 纯字符匹配
            rMatch(tI + 1, pJ + 1, text, tLen);
        }
    }
}
