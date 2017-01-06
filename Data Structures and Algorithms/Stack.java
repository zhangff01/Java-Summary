import java.util.Iterator;
/**
 * @description 数组实现的栈
 * @author zhangff01
 * @param <T>
 */
public class StackList<T> implements Iterable<T> {
	
	private T[] items=(T[]) new Object[1];
	private int N=0;
	public boolean isEmpty(){
		return N==0;
	}
	public int size(){
		return N;
	}
	public void resize(int max){
		T[] temp=(T[]) new Object[max];
		for(int i=0;i<N;i++){
			temp[i]=items[i];
		}
		items=temp;
	}
	public void push(T t){
		if(items.length==N){
			resize(2*items.length);
		}
		items[N++]=t;
	}
	public T pop(){
		T t=items[--N];
		items[N]=null;
		if(N>0&&N<items.length/4){
			resize(items.length/2);
		}
		return t;
	}
	//StackList的iterator方法,返回StackList的迭代器类实例
	@Override
	public Iterator<T> iterator() {
		return new StackListIterator();
	}
	private class StackListIterator implements Iterator<T>{
		private int i=N;
		@Override
		public boolean hasNext() {
			return i>0;
		}
		@Override
		public T next() {
			return items[--i];
		}
		@Override
		public void remove() {
			
		}
	}
}
