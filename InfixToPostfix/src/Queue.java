public class Queue {

    private Node head;

    public Queue() {
        head = null;
    }

    // This method adds an item to the queue
    public void addToQueue(String operand) {
        Node currentNode = head;
        Node previousNode = null;
        Node temp = new Node(operand);

        if (head == null) {
            head = temp;
        } else {
            while (currentNode != null) {
                if (currentNode.getNext() != null) {
                    previousNode = currentNode;
                    currentNode = currentNode.getNext();
                } else {
                    currentNode.setNext(temp);
                    break;
                }
            }
        }
    }

    // This method removes and returns an item from the end of the queue
    public String removeFromQueue() {
        Node currentNode = head;
        head = currentNode.getNext();

        return currentNode.getToken();
    }

    // This method returns the size of the list
    public int size() {
        int count = 0;
        Node currentNode = head;

        while (currentNode != null) {
            count++;
            currentNode = currentNode.getNext();
        }
        return count;
    }

    // This method displays all the items in a queue
    public void displayQueue() {
        Node currentNode = head;

        while (currentNode != null) {
            System.out.print(currentNode.getToken() + " ");
            currentNode = currentNode.getNext();
        }
    }
}
