package apitz_raffael.a5;

public class Stack<T> implements Stack_I<T> {
    
    private Node<T> head;
        
    @Override
    public void push(T data) {
        assert data != null : "data cant be null";
        Node<T> createdNode = new Node<T>(data);
        createdNode.setPrev(head);
        head = createdNode;
    }

    @Override
    public T pop() {
        assert !isEmpty() : "there is no node to pop";
        Node<T> work = head;
        head = work.getPrev();
        return work.getInfo();
    }

    @Override
    public T top() {
        assert !isEmpty() : "there is no node on top";
        return head.getInfo();
    }

    @Override
    public void clear() {
        Node<T> work = head;
        while(work != null) {
            Node<T> temp = work.getPrev();
            work.setPrev(null);
            work = temp;
        }
        head = null;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

}
