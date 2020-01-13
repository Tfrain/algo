package thirty_five_trie;

class TrieNode {
    public char data;
    public TrieNode[] children = new TrieNode[26];
    public boolean isEndingChar = false;
    public TrieNode(char data) {
        this.data = data;
    }
}
public class Trie {
    private TrieNode root = new TrieNode('/');
    // 往Trie树中插入一个字符串
    public void insert(char[] text) {
        TrieNode p = root;
        for(int i = 0; i < text.length; i++) {
            int index = text[i] - 'a';
            if(p.children[index] == null) {
                TrieNode newNode = new TrieNode(text[i]);
                p.children[index] = newNode;
            }
            //此时p.children[index]是一个newNode，所以可以往深处继续
            p = p.children[index];
        }
        p.isEndingChar = true;
    }

    public boolean find(char[] pattern) {
        TrieNode p = root;
        for (int i = 0; i < pattern.length; i++) {
            int index = pattern[i] - 'a';
            if(p.children[index] == null) return false;
            //此时可以往深度遍历，是因为已经初始化。
            p = p.children[index];
        }
        return p.isEndingChar;
    }
}
