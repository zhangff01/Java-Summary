/**
 * @description realize the ST(Symbol Table) by BST
 * @author zhangff01
 * @property 二叉查找树(BST):一颗二叉查找树是一颗二叉树，其中每个节点都含有一个Comparable的键(以及相关联的值)
 * 				而且该节点的值都大于其左子树的任意节点的值并且小于其右子树的任意节点指
 */
public class BinarySearchTree<Key extends Comparable<Key>,Value> {
	
	private Node root;		//二叉查找树的根节点
	
	private class Node{
		private Key key;	//键
		private Value val;	//值
		private Node left,right;//指向子树的链接
		private int N;		//以该节点为根的子树中的节点总数
		public Node(Key key,Value val,int N){
			this.key=key;
			this.val=val;
			this.N=N;
		}
	}
	/**
	 * @return the size of ST
	 */
	public int size(){
		return size(root);
	}
	private int size(Node x){
		return x==null?0:x.N;
	}
	/**
	 * 
	 * @param key
	 * @return get the value of key
	 */
	public Value get(Key key){
		return get(root,key);
	}
	private Value get(Node x,Key key){
		if(x==null)
			return null;
		int cmp=key.compareTo(x.key);
		if(cmp<0){
			return get(x.left,key);
		}else if(cmp>0){
			return get(x.right,key);
		}else{
			return x.val;
		}
	}
	/**
	 * @param key
	 * @param val
	 */
	public void put(Key key,Value val){
		root=put(root,key,val);
	}
	private Node put(Node x,Key key,Value val){
		if(x==null)
			return new Node(key,val,1);
		int cmp=key.compareTo(x.key);
		if(cmp<0){
			x.left=put(x.left,key,val);
		}else if(cmp>0){
			x.right=put(x.right,key,val);
		}else{
			x.val=val;
		}
		x.N=size(x.left)+size(x.right)+1;
		return x;
	}
}
