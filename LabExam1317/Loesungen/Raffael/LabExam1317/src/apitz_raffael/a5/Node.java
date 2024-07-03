// This source code is UTF-8 coded - see https://stackoverflow.com/questions/9180981/how-to-support-utf-8-encoding-in-eclipse
package apitz_raffael.a5;

class Node<T> {
    
    private T info;
    private Node<T> prev;
    
    public Node(T info) {
        this.info = info;
    }

    public boolean hasPrev() {
        return prev != null;
    }
    
    public Node<T> getPrev() {
        return prev;
    }
    
    public void setPrev(Node<T> node) {
        prev = node;
    }
    
    public T getInfo() {
        return info;
    }
    
    public void setInfo(T info) {
        this.info = info;
    }
 
}//class