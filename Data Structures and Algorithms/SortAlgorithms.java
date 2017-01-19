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
	/**
	 * @name 快速排序(不稳定)
	 * @description 排序过程
	 * 1.设置low,high变量分别代表前后排序的索引值(第一遍时low=0,high=arr.length);
	 * 2.默认以数组的第一个元素为比较关键值，key=arr[low];
	 * 3.从high开始向前开始搜索(high--)，直到找到第一个小于key值的arr[high]，并将它赋与arr[low];
	 * 4.从low开始向后搜索(low++)，找到第一个大于key的值arr[low]，并将它赋与arr[high];
	 * 5.如果low<high，循环执行步骤3,4。
	 */
	public static void QuickSort(int[] arr){
		RecursionSort(arr,0,arr.length-1);
	}
	private static void RecursionSort(int[] arr,int low,int high){
		if(low<high){
			int current=Partition(arr, low, high);
			RecursionSort(arr, low, current-1);
			RecursionSort(arr, current+1, high);
		}
	}
	private static int Partition(int[] arr,int low,int high){
		int key=arr[low];
		while(low<high){
			while(low<high&&arr[high]>=key){
				high--;
			}
			arr[low]=arr[high];
			while(low<high&&arr[low]<=key){
				low++;
			}
			arr[high]=arr[low];
		}
		arr[low]=key;
		return low;
	}
	//标准实现
	public static void QuickSort(int[] arr,int low,int high){
		int l=low,h=high;
		int key=arr[low];
		while(l<h){
			while(l<h&&arr[h]>=key)
				h--;
			if(l<h){
				int temp=arr[l];
				arr[l]=arr[h];
				arr[h]=temp;
				l++;
			}
			while(l<h&&arr[l]<=key)
				l++;
			if(l<h){
				int temp=arr[l];
				arr[l]=arr[h];
				arr[h]=temp;
				h--;
			}
		}
		if(l>low)
			QuickSort(arr,low,l-1);
		if(h<high)
			QuickSort(arr,l+1,high);
	}
	/**
	 * @name 堆排序
	 * @description 排序过程：for循环构造堆,while循环将最大元素arr[1]和最后元素arr[N]交换并做下沉处理,只到堆减空 
	 */
	public static void HeapSort(int[] arr){
		int N=arr.length;
		for(int k=N/2;k>=1;k--){
			sink(arr,k,N);
		}
		while(N>1){
			exch(arr,1,N--);
			sink(arr,1,N);
		}
	}
	private static void sink(int[] arr,int k,int N){
		while(2*k<=N){
			int j=2*k;
			if(j<N&&arr[j-1]<arr[j])
				j++;
			if(arr[k-1]>arr[j-1])
				break;
			exch(arr,k,j);
			k=j;
		}
	}
	private static void exch(int[] arr,int i,int j){
		int temp=arr[i-1];
		arr[i-1]=arr[j-1];
		arr[j-1]=temp;
	}
}
