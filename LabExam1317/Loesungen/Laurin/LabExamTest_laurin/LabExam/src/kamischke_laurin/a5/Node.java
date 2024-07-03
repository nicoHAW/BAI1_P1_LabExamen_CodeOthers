// This source code is UTF-8 coded - see https://stackoverflow.com/questions/9180981/how-to-support-utf-8-encoding-in-eclipse
package kamischke_laurin.a5;


/* TODO
 * Die Klasse Node ist nur vorgegeben um "lästige" Compilerfehler im "TestFrameAndStarter" zu vermeiden.
 * Die Vorgabe ist in einer absoluten Minimalform um die zuvor erwähnten Syntaxfehler zu vermeiden.
 * Diese Klasse hat in der vorliegenden Form KEINE Funktionalität.
 * Sie MÜSSEN ZWINGEND Ergänzungen in dieser Klasse vornehmen!
 * Wenn Sie diese Ergänzungen abgeschlossen haben, müssen Sie die TODO-Markierungen entfernen!
 */
class Node<T> {

	private Node prev;
	private Node next;
	private T data;
	
	public Node(T data) {
		assert (data != null);
		this.data = data;
	}
	
	public Node getPrev() {
		return prev;
	}
	
	public Node getNext() {
		return next;
	}	
	
	public void setPrev(Node n) {
		prev = n;
	}
	
	public void setNext(Node n) {
		next = n;
	}
	
	public T getData(){
		return data;
	}
 
}//class