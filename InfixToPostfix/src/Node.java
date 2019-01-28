public class Node {

    private String string;
    private Node next;

    // This method creates a new node
    public Node(String str) {
        string = str;
        next = null;
    }

    // This method returns the string value of the node
    public String getToken() {
        return string;
    }

    // This method returns the reference of the next node
    public Node getNext() {
        return next;
    }

    // This method sets the next value to the node
    public void setNext(Node node) {
        next = node;
    }
}

