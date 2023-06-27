package org.example;

public class Main {
    public static void main(String[] args) {
        
    }

    public class RedBlackTree<K extends Comparable<K>, V> {

        private static final boolean RED = true;
        private static final boolean BLACK = false;

        private class Node {
            K key;
            V value;
            Node left, right;
            boolean color;

            public Node(K key, V value) {
                this.key = key;
                this.value = value;
                this.color = RED;
            }
        }

        private Node root;

        private boolean isRed(Node x) {
            if (x == null) {
                return false;
            }
            return x.color == RED;
        }

        private Node rotateLeft(Node h) {
            Node x = h.right;
            h.right = x.left;
            x.left = h;
            x.color = h.color;
            h.color = RED;
            return x;
        }

        private Node rotateRight(Node h) {
            Node x = h.left;
            h.left = x.right;
            x.right = h;
            x.color = h.color;
            h.color = RED;
            return x;
        }

        private void flipColors(Node h) {
            h.color = RED;
            h.left.color = BLACK;
            h.right.color = BLACK;
        }

        public void put(K key, V value) {
            root = put(root, key, value);
            root.color = BLACK;
        }

        private Node put(Node h, K key, V value) {
            if (h == null) {
                return new Node(key, value);
            }
            int cmp = key.compareTo(h.key);
            if (cmp < 0) {
                h.left = put(h.left, key, value);
            } else if (cmp > 0) {
                h.right = put(h.right, key, value);
            } else {
                h.value = value;
            }

            if (isRed(h.right) && !isRed(h.left)) {
                h = rotateLeft(h);
            }
            if (isRed(h.left) && isRed(h.left.left)) {
                h = rotateRight(h);
            }
            if (isRed(h.left) && isRed(h.right)) {
                flipColors(h);
            }
            return h;
        }
    }

}