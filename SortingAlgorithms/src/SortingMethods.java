
public class SortingMethods{
	
	
	public void bublleSort(int[] ary){
		boolean swapped;
		for(int i=0;i<ary.length-1;i++){
			swapped=false;
			for(int j=0;j<ary.length-i-1;j++){
				if(ary[j]>ary[j+1]){
					int temp=ary[j];
					ary[j]=ary[j+1];
					ary[j+1]=temp;
					swapped=true;
				}
			}
			if(swapped==false){
				break;
			}
			
		}
		
		
	}
	
	public void insertionSort(int[] ary){
		
		//Base case
		if(ary.length==0 || ary.length==1){
			return;
		}
		//Regular case
		for(int i=1;i<ary.length;i++){
			int key=ary[i];
			int j=i-1;
			while(j>=0&&ary[j]>key){
				ary[j+1]=ary[j];
				j=j-1;
			}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
			ary[j+1]=key;
		}
		
		
	}

	//Sorting the array using the heapSort algorithm
	public int[] heapSort(int[] ary){
		int n= ary.length;
		
		//Building the max heap to sort the array.
		for(int i=n/2-1;i>=0;i--){
			buildMaxHeap(ary, n, i);
		}
		//Sorting the array by changing removing 1 element from end after each iteration
		for(int i=n-1;i>=0;i--){
			int swap=ary[0];
			ary[0]=ary[i];
			ary[i]=swap;
			
			//Calling the buildHeap to change the heap
			buildMaxHeap(ary, i, 0);
			
		}
		
		return ary;
	}
	
	//Building the private method to max-Hepify the array for sorting
	private void buildMaxHeap(int[] ary,int length,int i){
		int largest,left,right;
		left=2*i+1;
		right=2*i+2;
		if(left<length && ary[left]>ary[i]){
			largest=left;
		}
		else{
			largest=i;
		}
		if(right<length && ary[right]>ary[i] ){
			largest=right;
		}
		if(largest!=i){
			int temp=ary[i];
			ary[i]=ary[largest];
			ary[largest]=temp;
			
			buildMaxHeap(ary,length,right);
		}
	}
	
	private void merge(int ary[],int l,int m,int r){
		int n1=m-l+1;
		int n2=r-m;
		
		int L[]=new int[n1];
		int R[]=new int[n2];
		
		for(int i=0;i<n1;i++){
			L[i]=ary[l+i];
			
		}
		for(int j=0;j<n2;j++){
			R[j]=ary[m+1+j];
		}
		
		int i=0,j=0;
		
		int k=l;
		
		while(i<n1&&j<n2){
			if(L[i]<R[j]){
				ary[k]=L[i];
				i++;
			}
			else{
				ary[k]=R[j];
				j++;
			}
			k++;
		}
		
		while(i<n1){
			ary[k]=L[i];
			i++;
			k++;
		}
		while(j<n2){
			ary[k]=R[j];
			j++;
			k++;
		}
	}
	
	public void mergeSort(int[] ary,int low, int high){
		
		if(low<high){
			int medium=(low+high)/2;
			
			mergeSort(ary, low, medium);
			mergeSort(ary,medium+1,high);
			
			merge(ary, low, medium, high);
		}
	}
	
	private int partition(int[] ary,int low,int high){
		int pivot=ary[high];
		int i=(low-1);
		for(int j=low;j<high;j++){
			if(ary[j]<=pivot){
				i++;
				int temp=ary[i];
				ary[i]=ary[j];
				ary[j]=temp;
			}
		}
		int temp=ary[i+1];
		ary[i+1]=ary[high];
		ary[high]=temp;
		
		return i+1;
		
	}
	public void quickSort(int[] ary,int low,int high){
		if(low<high){
			int pi=partition(ary, low, high);
			quickSort(ary,low,pi-1);
			quickSort(ary, pi+1, high);
		}
	}	

}
