package kakao.recruit2018;

public class 자동완성 {
    public int solution(String[] words) {
        int answer = 0;
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }
        for (String word : words) {
            answer += trie.countInputStr(word);
        }
        return answer;
    }

    private static class Trie {
        private final TrieNode root = new TrieNode();

        public void insert(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                assert node != null;
                node.addChildNode(c);
                node = node.getChildNode(c);
            }
            assert node != null;
            node.setEnd(true);
        }

        public int countInputStr(String word) {
            int ret = 0;
            TrieNode parentNode = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                TrieNode childNode = parentNode.getChildNode(c);
                if (parentNode.isEnd() || parentNode.countChild() > 1) {
                    ret = i + 1;
                }
                parentNode = childNode;
            }
            if (parentNode.countChild() > 0) {
                ret = word.length();
            }
            return ret;
        }

        public boolean contain(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                node = node.getChildNode(c);
                if (node == null) return false;
            }
            return node.isEnd();
        }
    }


    private static class TrieNode {
        private final TrieNode[] childNodes = new TrieNode['z' - 'a' + 1];
        private boolean end = false;

        public TrieNode getChildNode(char c) {
            if (childNodes[c - 'a'] == null) return null;
            else return childNodes[c - 'a'];
        }

        public void addChildNode(char c) {
            if (getChildNode(c) == null) {
                childNodes[c - 'a'] = new TrieNode();
            }
        }

        public boolean isEnd() {
            return end;
        }

        public void setEnd(boolean end) {
            this.end = end;
        }

        public int countChild() {
            int ret = 0;
            for (TrieNode childNode : childNodes) {
                if (childNode != null) ret++;
            }
            return ret;
        }
    }
}