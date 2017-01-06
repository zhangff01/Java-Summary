import java.util.Iterator;
/**
 * @description 双向链表实现的队列
 * @author zhangff01
 * @param <T>
 */
public class LinkedQueueList<T> implements Iterable<T> {
	private Node first;
	private Node last;
	private int N;
	
	public boolean isEmpty(){
		return N==0;
	}
	public int size(){
		return N;
	}
	public void enqueue(T t){
		Node oldlast=last;
		last=new Node();
		last.t=t;
		if(isEmpty()){
			first=last;
		}else{
			oldlast.next=last;
		}
		N++;
	}
	public T dequeue(){
		T t=first.t;
		first=first.next;
		if(isEmpty())
			last=null;
		N--;
		return t;
	}
	
	private class Node{
		Node prev;
		T t;
		Node next;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new LinkedQueueListIterator();
	}
	private class LinkedQueueListIterator implements Iterator<T>{
		private Node Ifirst=first;
		@Override
		public boolean hasNext() {
			return Ifirst!=null;
		}
		@Override
		public T next() {
			T t=Ifirst.t;
			Ifirst=Ifirst.next;
			return t;
		}
		@Override
		public void remove() {
			
		}
	}
}
