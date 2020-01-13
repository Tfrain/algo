package thirty_six_ac_automata;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;

public class ACAutoMata {
    // 理论讲解时的代码
    /*public class AcNode{
        public char data;
        public AcNode[] children = new AcNode[26]; // a~z
        public boolean isEndingChar = false;//结尾字符为 true
        public int length = -1; // 当isEndingChar=true时，记录模式串长度。
        public AcNode fail;// 失败指针
        public AcNode(char data) {
            this.data = data;
        }
    }

    public void buildFailurePointer(AcNode root) {
        Queue<AcNode> queue = new LinkedList<>();
        root.fail = null;
        queue.add(root);
        while(!queue.isEmpty()) {
            AcNode p = queue.remove();
            for(int i = 0;i < 26; i++) {//这感觉复杂度也挺高的
                AcNode pc = p.children[i];
                if (pc == null) continue;
                if(pc == root) {
                    pc.fail = root;
                } else {
                    AcNode q = p.fail;
                    while(q != null) {
                        AcNode qc = q.children[pc.data - 'a'];
                        if(qc != null) {
                            pc.fail = qc;
                            break;
                        }
                        q = q.fail;
                    }
                    if(q == null) {
                        pc.fail = root;
                    }
                }
                queue.add(pc);
            }
        }
    }
    public void match(char[] text, AcNode root) {
        int n = text.length;
        AcNode p = root;
        for (int i = 0; i < n; i++) {
            int idx = text[i] - 'a';
            while (p.children[idx] == null && p != root) {
                p = p.fail;// 失败指针发挥作用的地方
            }
            p = p.children[idx];
            if(p == null) p = root;// 如果没有匹配的，从root开始重新匹配
            AcNode tmp = p;
            while(tmp != root) { // 打印出可以匹配的模式串
                if(tmp.isEndingChar == true) {
                    int pos = i - tmp.length + 1;
                    System.out.println("匹配起始下标" + pos + "; 长度" + tmp.length);
                }
                tmp = tmp.fail;
            }
        }
    }*/
    // algo仓库的代码
    public class ACNode {
        private String data;
        private Map<String, ACNode> children;
        private Boolean isEndingChar;
        private Integer length;
        private ACNode fail;

        public ACNode(String data) {
            this.data = data;
            this.children = new HashMap<>();
            this.isEndingChar = false;
            this.length = 0;
            this.fail = null;
        }
    }

    private ACNode root;
    public ACAutoMata() {
        this.root = new ACNode("/");
    }

    private void insert(String pattern) {
        ACNode node = this.root;
        int len = pattern.length();
        for (int i = 0; i < len; i++) {
            String c = pattern.charAt(i) + "";
            //node.children.get(c)获得的 value 是 ACNode 类型的
            if(Objects.isNull(node.children.get(c))) {
                node.children.put(c, new ACNode(c));
            }
            node = node.children.get(c);
        }

        node.isEndingChar = true;
        node.length = pattern.length();
    }

    private void buildFailurePointer() {
        ACNode root = this.root;
        LinkedList<ACNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            ACNode p = queue.pop();

            for(ACNode pc: p.children.values()) {
                if(Objects.isNull(pc)) {
                    continue;
                }

                if(p == root) {
                    pc.fail = root;
                } else {
                    ACNode q = p.fail;
                    while(Objects.nonNull(q)) {
                        ACNode qc = q.children.get(pc.data);
                        if(Objects.nonNull(qc)) {
                            pc.fail = qc;
                            break;
                        }
                        q = q.fail;
                    }
                    if(Objects.isNull(q)) {
                        pc.fail = root;
                    }
                }
                queue.add(pc);
            }
        }
    }
    private Boolean match (String text) {
        ACNode root = this.root;
        ACNode p = root;

        int n = text.length();
        for (int i = 0; i < n; i++) {
            String c = text.charAt(i) + "";
            while(Objects.isNull(p.children.get(c)) && p != root) {
                p = p.fail;
            }

            p = p.children.get(c);
            if(Objects.isNull(p)) {
                p = root;
            }

            ACNode tmp = p;
            while (tmp != root) {
                if (tmp.isEndingChar) {
                    System.out.println("Start from " + (i - p.length + 1));
                    return true;
                }
                tmp = tmp.fail;
            }
        }
        return false;
    }
    public static boolean match(String text, String[] patterns) {
        ACAutoMata autoMata = new ACAutoMata();
        for (String pattern : patterns) {
            autoMata.insert(pattern);
        }

        autoMata.buildFailurePointer();
        return autoMata.match(text);
    }

    public static void main(String[] args) {
        String[] patterns = {"at", "art", "oars", "soar"};
        String text = "soarsoars";
        System.out.println(match(text, patterns));

        String[] patterns2 = {"Fxtec Pro1", "谷歌Pixel"};

        String text2 = "一家总部位于伦敦的公司Fxtex在MWC上就推出了一款名为Fxtec Pro1的手机，该机最大的亮点就是采用了侧滑式全键盘设计。DxOMark年度总榜发布 华为P20 Pro/谷歌Pixel 3争冠";
        System.out.println(match(text2, patterns2));
    }

}
