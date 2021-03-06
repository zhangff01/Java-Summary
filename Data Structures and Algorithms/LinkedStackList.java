import java.util.Iterator;
/**
 * @description 单向链表实现的栈
 * @author zhangff01
 * @param <T>
 */
public class LinkedStackList<T> implements Iterable<T> {
	
	private class Node{
		T t;
		Node next;
	}
	
	private Node first;	//栈顶元素
	private int N;		//元素数量
	
	public int size(){
		return N;
	}
	public boolean isEmpty(){
		return N==0;
	}
	public void push(T t){
		Node oldfirst=first;
		first=new Node();
		first.t=t;
		first.next=oldfirst;
		N++;
	}
	public T pop(){
		if(isEmpty()){
			return null;
		}
		T t=first.t;
		first=first.next;
		N--;
		return t;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new LinkedStackListIterator();
	}
	private class LinkedStackListIterator implements Iterator<T>{
		private Node current=first;
		@Override
		public boolean hasNext() {
			return current!=null;
		}

		@Override
		public T next() {
			T t=current.t;
			current=current.next;
			return t;
		}

		@Override
		public void remove() {
			
		}
		
	}
}
