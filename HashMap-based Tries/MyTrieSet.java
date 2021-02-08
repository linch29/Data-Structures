import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyTrieSet implements TrieSet61B {

    private Node root;

    private class Node {
        private char c;
        private boolean isKey;
        private Map<Character, Node> map;

        Node(char c, boolean isKey) {
            this.c = c;
            this.isKey = isKey;
            map = new HashMap<>();
        }
    }

    /**
     * Intialize the tries
     */
    MyTrieSet() {
        root = new Node('_', false);
    }

    /**
     * Clear all the tries
     */
    @Override
    public void clear() {
        root = new Node('_', false);
    }

    /**
     * Return whether the tries contains the key
     */
    @Override
    public boolean contains(String key) {
        if (key == null || key.length() < 1) {
            return false;
        }
        Node curr = root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (curr.map.containsKey(c)) {
                curr = curr.map.get(c);
                if (i == key.length() - 1 && !curr.isKey) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * Return the longest prefix of the key
     */
    @Override
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }

    /**
     * Return all the prefix of the given key
     */
    @Override
    public List<String> keysWithPrefix(String prefix) {
        Node curr = root;
        List<String> result = new ArrayList<>();
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (curr.map.containsKey(c)) {
                curr = curr.map.get(c);
            } else {
                return result;
            }
        }
        if (curr.isKey){
            result.add(prefix);
        }
        for(char c: curr.map.keySet()){
            helperKeysWithPrefix(result, prefix, curr.map.get(c));
        }
        return result;
    }

    /**
     * The helper function of the keysWithPrefix method
     */
    private void helperKeysWithPrefix(List<String> result, String currentString, Node currentNode) {
        if (currentNode.isKey) {
            result.add(currentString + currentNode.c);
        }
        for (char c : currentNode.map.keySet()) {
            helperKeysWithPrefix(result, currentString + currentNode.c, currentNode.map.get(c));
        }
    }

    /**
     * Add the keys to the tries
     */
    @Override
    public void add(String key) {
        if (key == null || key.length() < 1) {
            return;
        }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                curr.map.put(c, new Node(c, false));
            }
            curr = curr.map.get(c);
        }
        curr.isKey = true;
    }

}
