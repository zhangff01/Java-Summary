/**
 * @description 八大排序算法
 * @author zhangff01
 */
public final class SortAlgorithms {
	
	/**
	 * @name 选择排序(不稳定)
	 * @description 遍历每个元素时和后面所有元素的最小值交换位置
	 */
	public static void SelectSort(int[] arr){
		for(int i=0;i<arr.length;i++){
			int min=i;
			for(int j=i+1;j<arr.length;j++){
				if(arr[j]<arr[min])
					min=j;
			}
			int temp=arr[i];
			arr[i]=arr[min];
			arr[min]=temp;
		}
	}
	/**
	 * @name 冒泡排序(稳定)
	 * @description 遍历每个元素时和后面的每个元素比较，只要大于就交换位置(升序)
	 */
	public static void BubbleSort(int[] arr){
		for(int i=0;i<arr.length;i++){
			for(int j=i+1;j<arr.length;j++){
				if(arr[i]>arr[j]){
					int temp=arr[i];
					arr[i]=arr[j];
					arr[j]=temp;
				}
			}
		}
	}
	/**
	 * @name 插入排序(稳定)
	 * @description 遍历每个元素并与前面的元素比较大小(向前排序)
	 */
	public static void InsertSort(int[] arr){
		for(int i=0;i<arr.length;i++){
			for(int j=i;j>0&&arr[j-1]>arr[j];j--){
				int temp=arr[j];
				arr[j]=arr[j-1];
				arr[j-1]=temp;
			}
		}
	}
	/**
	 * @name 希尔排序(不稳定)
	 * @description 把数组每次以相隔h距离的为一组分别进行插入排序，最后一次再进行一次统一的插入排序
	 */
	public static void ShellSort(int[] arr){
		int N=arr.length;
		int h=1;
		while(h<N/3)
			h=3*h+1;
		while(h>=1){
			for(int i=h;i<N;i++){
				for(int j=i;j>0&&arr[j-h]>arr[j];j-=h){
					int temp=arr[j];
					arr[j]=arr[j-1];
					arr[j-1]=temp;
				}
			}
			h=h/3;
		}
	}
	/**
	 * @name 归并排序 
	 * @description 
	 */
	private static int[] aux;
	public static void MergeSort(int[] arr){
		aux=new int[arr.length];//归并时的辅助数组
		sort(arr,0,arr.length-1);
	}
	private static void sort(int[] arr,int low,int high){
		if(low>=high)
			return;
		int mid=low+(high-low)/2;
		sort(arr,low,mid);
		sort(arr,mid+1,high);
		merge(arr,low,mid,high);
	}
	private static void merge(int[] arr,int low,int mid,int high){
		int i=low,j=mid+1;
		for(int k=low;k<=high;k++){
			aux[k]=arr[k];
		}
		for(int k=low;k<=high;k++){
			if(i>mid){
				arr[k]=aux[j++];
			}else if(j>high){
				arr[k]=aux[i++];
			}else if(aux[j]<aux[i]){
				arr[k]=aux[j++];
			}else{
				arr[k]=aux[i++];
			}
		}
	}
}