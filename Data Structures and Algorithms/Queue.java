import java.util.Iterator;
/**
 * @author zhangff01
 * @param <T>
 */
public class QueueList<T> implements Iterable<T> {
	private int N=0;
	private T[] items=(T[]) new Object[1];
	public boolean isEmpty(){
		return N==0;
	}
	public int size(){return N;}
	public void resize(int max){
		T[] temp=(T[]) new Object[max];
		for(int i=0;i<N;i++){
			temp[i]=items[i];
		}
		items=temp;
	}
	public void enqueue(T t){
		if(items.length==N){
			resize(items.length*2);
		}
		items[N++]=t;
	}
	public T dequeue(){
		T t=items[0];
		for(int i=0;i<N;i++){
			if(i==N-1)
				break;
			items[i]=items[i+1];
		}
		items[--N]=null;
		if(N>0&&N<items.length/4){
			resize(items.length/2);
		}
		return t;
	}
	@Override
	public Iterator<T> iterator() {
		return new QueueListIterator();
	}
	private class QueueListIterator implements Iterator<T>{
		private int i=0;
		@Override
		public boolean hasNext() {
			return i<N;
		}
		@Override
		public T next() {
			return items[i++];
		}
		@Override
		public void remove() {}
	}
}
