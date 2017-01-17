/**
 * @description 堆模拟的优先队列
 * @author zhangff01
 * @param <T>
 */
public class MaxPriorityQueue<T extends Comparable<T>> {
	/**
	 * 堆有序:但一颗二叉树的每个节点都大于等于它的两个子节点时,被称为堆有序
	 * 堆(二叉堆):是一组能够用堆有序的完全二叉树排序的元素,并在数组中按层级存储(不使用数组的第一个元素)
	 */
	private T[] priorityQueue;	//基于堆的完全二叉树
	private int N=0;			//size of heap
	
	/**
	 * --堆性质--
	 * 对于位置为k的节点:
	 * 1.子节点的位置:2k(左节点)和2k+1(右节点).
	 * 2.父节点的位置:(int)k/2.
	 * --sort heap by two ways--
	 * sort heap from down to up(swim)--swim
	 * sort heap from up to down(sink)--sink
	 */
	
	/**
	 * compareTo function:i<j return negative,i==j return 0,i>j return positive
	 * @param i
	 * @param j
	 * @return i<j return true;i>j return false
	 */
	private boolean small(int i,int j){
		return priorityQueue[i].compareTo(priorityQueue[j])<0;
	}
	/**
	 * exchange the value of i,j index
	 * @param i
	 * @param j
	 */
	private void exch(int i,int j){
		T temp=priorityQueue[i];
		priorityQueue[i]=priorityQueue[j];
		priorityQueue[j]=temp;
	}
	/**
	 * sort heap from down to up(swim)
	 * @param k
	 */
	private void swim(int k){
		while(k>1&&small(k/2,k)){
			exch(k/2,k);
			k=k/2;
		}
	}
	/**
	 * sort heap from up to down(sink)
	 * @param k
	 */
	private void sink(int k){
		while(2*k<=N){
			int left=2*k;
			//find the bigger node between left node and right node
			if(left<N&&small(left,left+1))
				left++;
			//if the current node is bigger than left node and right node
			if(!small(k,left))
				break;
			exch(left,k);
			k=left;
		}
	}
	/**
	 * construct function
	 * @param N
	 */
	public MaxPriorityQueue(int N){
		priorityQueue=(T[]) new Comparable[N+1];
	}
	/**
	 * priorityqueue is empty?
	 * @return yes:true no:false
	 */
	public boolean isEmpty(){
		return N==0;
	}
	/**
	 * return the size of priorityqueue
	 * @return
	 */
	public int size(){
		return N;
	}
	/**
	 * insert a element to the priority queue
	 * @param t
	 */
	public void insert(T t){
		priorityQueue[++N]=t;
		swim(N);
	}
	/**
	 * delete the max element of priority queue and return it
	 * @return
	 */
	public T delMax(){
		T t=priorityQueue[1];
		exch(1,N--);
		priorityQueue[N+1]=null;
		sink(1);
		return t;
	}
}
