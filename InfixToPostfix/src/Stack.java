public class Stack {

    private Node head;

    public Stack() {
        head = null;
    }

    // This methods returns the size of the stack
    public int size() {
        int count = 0;
        Node currentNode = head;

        while (currentNode != null) {
            count++;
            currentNode = currentNode.getNext();
        }
        return count;
    }

    // This method adds an item to the stack
    public void push(String item) {
        Node temp = new Node(item);

        if (head == null) {
            head = temp;
        } else {
            temp.setNext(head);
            head = temp;
        }
    }

    // This method removes and returns the first item of the stack
    public String pop() {
        Node currentNode = head;
        head = currentNode.getNext();

        return currentNode.getToken();
    }

    // This method returns the top item of the stack
    public String getTopItem() {
        return head.getToken();
    }
}
