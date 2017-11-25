import java.util.Random;
import java.util.Scanner;
import java.io.*;

public class SortingArrays extends Thread {
	
	int[] ary={10,5,6,3,87,24,6};
	int low,high;
	int parameter;
	static double exeTime,startTime,endTime;
	SortingMethods sortMethod;
	Random rand=new Random();
	
	public SortingArrays(int parameter){
		this.parameter=parameter;
		sortMethod=new SortingMethods();
		low=0;
		high=ary.length-1;
		exeTime=0;
		startTime=0;
		endTime=0;
	}
	
	public void run(){
		if(parameter==1){
			
			startTime=System.nanoTime();
			sortMethod.insertionSort(ary);
			endTime=System.nanoTime();
			exeTime=(endTime-startTime);
			printArray(ary, low, high,exeTime);
			
		}
		else if(parameter==2){
			startTime=System.nanoTime();
			sortMethod.bublleSort(ary);
			endTime=System.nanoTime();
			exeTime=(endTime-startTime);
			printArray(ary, low, high,exeTime);
		}
		else if(parameter==3){
			startTime=System.nanoTime();
			sortMethod.mergeSort(ary, low, high);
			endTime=System.nanoTime();
			exeTime=(endTime-startTime);
			printArray(ary, low, high,exeTime);
			
		}
		else if(parameter==4){
			startTime=System.nanoTime();
			sortMethod.heapSort(ary);
			endTime=System.nanoTime();
			exeTime=(endTime-startTime);
			printArray(ary, low, high,exeTime);
		}
		else if(parameter==5){
			startTime=System.nanoTime();
			sortMethod.quickSort(ary, low, high);
			endTime=System.nanoTime();
			exeTime=(endTime-startTime);
			printArray(ary, low, high,exeTime);
		}
		else if(parameter==6){
			startTime=System.nanoTime();
			printArray(ary, low, high,exeTime);
			endTime=System.nanoTime();
			exeTime=(endTime-startTime);
		}
		else if(parameter==7){
			for(int i=low;i<high+1;i++){
				ary[i]=rand.nextInt(50)+1;
			}
			printArray(ary, low, high,0);
		}
		else{
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int choice;
		Thread t1,t2,t3;
		do{
			System.out.println("Following is the list of the sorting algorithms:");
			System.out.println("1)Insertion Sort");
			System.out.println("2)Bubble Sort");
			System.out.println("3)Merge Sort");
			System.out.println("4)Heap Sort");
			System.out.println("5)Quick Sort");
			System.out.println("6)Print Array");
			System.out.println("7)Reset Array");
			System.out.println("8)Exit");
			System.out.println("Enter the choice:");
			Scanner scr=new Scanner(System.in);
			choice=scr.nextInt();
			
			
			
			
			
				t1=new SortingArrays(choice);
				t2=new SortingArrays(choice);
				t3=new SortingArrays(choice);
				t1.start();
				//t2.start();
				//t3.start();
			
		}while(choice!=8);
	}
	
	public static void printArray(int[] ary,int low,int high,double PTime){
	
		System.out.print("{");
		for(int i=low;i<=high;i++){
			
			System.out.print(ary[i]);
			if(i!=high){
				System.out.print(",");
			}
			
		}
		System.out.print("}:");
		System.out.println(PTime);
		System.out.println();
	}

}
