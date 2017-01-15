package com.zhangff01;
/**
 * @description 二叉树
 * @author zhangff01
 */
public class BinaryTree {
	//性质1：一个最大有i层的二叉树最多有2^i-1个节点
	//性质2：第i层最多有2^(i-1)各节点
	//满二叉树：一个i层的二叉树有2^i-1个节点则被称为满二叉树
	//完全二叉树：除了最后一层外其他所有层每层都是最大节点。
	
	private class TreeNode{
		int data;
		TreeNode left;
		TreeNode right;
		public TreeNode(){}
		public TreeNode(int data){
			this.data=data;
		}
	}
	
	/**
	 * @description 前序遍历
	 */
	public static void PreOrder(TreeNode node){
		if(node!=null){
			System.out.print(node.data+",");
			if(node.left!=null)
				PreOrder(node.left);
			if(node.right!=null){
				PreOrder(node.right);
			}
		}
	}
	/**
	 * @description 前序遍历(非递归，用栈实现)
	 */
	public static void PreOrderNoRecursion(TreeNode node){
		if(node!=null){
			LinkedStackList<TreeNode> ls=new LinkedStackList<TreeNode>();
			TreeNode current=node;
			ls.push(node);
			while(current!=null&&!ls.isEmpty()){
				current=ls.pop();
				System.out.print(current.data+",");
				if(current.right!=null){
					ls.push(current.right);
				}
				if(current.left!=null){
					ls.push(current.left);
				}
			}
		}
	}
	/**
	 * @description 中序遍历
	 */
	public static void InOrder(TreeNode node){
		if(node!=null){
			if(node.left!=null)
				InOrder(node.left);
			System.out.print(node.data+",");
			if(node.right!=null){
				InOrder(node.right);
			}
		}
	}
	/**
	 * @description 中序遍历(非递归，用栈实现)
	 */
	public static void InOrderNoRecursion(TreeNode node){
		if(node!=null){
			LinkedStackList<TreeNode> ls=new LinkedStackList<TreeNode>();
			TreeNode current=node;
			while(current!=null||!ls.isEmpty()){
				while(current!=null){
					ls.push(current);
					current=current.left;
				}
				TreeNode top=ls.pop();
				System.out.print(top.data+",");
				current=top.right;
			}
		}
	}
	/**
	 * @description 后序遍历
	 */
	public static void PostOrder(TreeNode node){
		if(node!=null){
			if(node.left!=null)
				PostOrder(node.left);
			if(node.right!=null){
				PostOrder(node.right);
			}
			System.out.print(node.data+",");
		}
	}
	/**
	 * @description 后序遍历(非递归，用栈实现)
	 */
	public static void PostOrderNoRecursion(TreeNode node){
		if(node!=null){
			LinkedStackList<TreeNode> ls=new LinkedStackList<TreeNode>();
			LinkedStackList<TreeNode> ls2=new LinkedStackList<TreeNode>();//中间栈来存储逆后续遍历的结果
			TreeNode current=node;
			while(current!=null||!ls.isEmpty()){
				if(current!=null){
					ls.push(current);
					ls2.push(current);
					current=current.right;
				}else{
					current=ls.pop();
					current=current.left;
				}
			}
			for(TreeNode tr:ls2){
				System.out.print(tr.data+",");
			}
		}
	}
	/**
	 * @description 层次遍历(用队列实现)
	 */
	public static void LevelOrder(TreeNode node){
		if(node!=null){
			LinkedQueueList<TreeNode> queue=new LinkedQueueList<TreeNode>();
			TreeNode current=null;
			queue.enqueue(node);
			while(!queue.isEmpty()){
				current=queue.dequeue();
				System.out.print(current.data+",");
				if(current.left!=null)
					queue.enqueue(current.left);
				if(current.right!=null)
					queue.enqueue(current.right);
			}
		}
	}
}
