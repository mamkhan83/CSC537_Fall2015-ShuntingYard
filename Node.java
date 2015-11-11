

/**
 * Node.java
 *
 */
public class Node {

    private final char value;
    private final Node leftNode;
    private final Node rightNode;

    /***
     * Constructs a new Node Object. 
     * There's no explicit specialization for leaf
     * nodes. Leaves are denoted by nodes where both the left and right node
     * is null.
     */
    public Node(char value, Node leftNode, Node rightNode) {
        this.value = value;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    
    public char getValue() {
        return value;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }
}
