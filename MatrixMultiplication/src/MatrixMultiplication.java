//Program by:Tushar Deshpande
//This program will provide the time to execute the matrix multiplication in the java.
//Agenda: finding the maximum speed of the java to execute the actual result of the entire process.
public class MatrixMultiplication implements Runnable {

	static int i=0,j=0,k=0;
	static double exeTime=0,startTime=0,endTime=0;
	
	public void run(){
		
		//Creating the instance of the Matrix server to call the methods.
		
		matrixMultiply();
		//Calling the matrix multiplication method.
		
	}
	public static void main(String[] args) {

		MatrixMultiplication ms=new MatrixMultiplication();
		Thread myThread1=new Thread(ms);
		Thread myThread2=new Thread(ms);
		Thread myThread3=new Thread(ms);
		myThread1.setName("First Thread");
		myThread2.setName("Second Thread");
		myThread3.setName("Third Thread");
		
		myThread1.start();
		myThread2.start();
		myThread3.start();
		
	}
	
	
	//matrix initialization which will call the recursive function. Complexity of the algorithm is O(n^3)
	public static synchronized void matrixMultiply(){
		try{
			
			int row1=2,col1=2,row2=2,col2=2;
			exeTime=0;
			startTime=0;
			endTime=0;
			int array1[][]={
							{1,2},
							{3,4}
							};
			int array2[][]={
							{2,3},
							{5,4}};
			int rslt[][]=new int[100][100];
			if(row1!=col2){
				System.out.println("Not possible to multiply the matrix");
				return;
			}
			
				
				
				
				startTime=System.nanoTime();
				//Call for recursive method.
				matrixMultiplicaitonRec(row1, col1, array1, row2, col2, array2, rslt);
				endTime=System.nanoTime();
				exeTime=(endTime-startTime);
				i=0;
				for(int i=0;i<row1;i++)
				{
					for(int j=0;j<col2;j++){
						System.out.print(rslt[i][j]+" ");
					}
					System.out.println();
				}
				
				
				System.out.println("Execution Time by 1 Thread: "+(exeTime/1000000));
				
			
		}catch(Exception e){
			System.out.println("Error in executing matrix multiplication:"+e.getMessage());
		}
	}
	//
	public static synchronized void matrixMultiplicaitonRec(int row1,int col1,int ary1[][],int row2,int col2,int ary2[][],int rslt[][]){
		try{
			
			
			if(i>=row1){
				return;
			}
			if(j<col2){
				
				if(k<col1){
					rslt[i][j]+=ary1[i][k]*ary2[k][j];
					k++;
					matrixMultiplicaitonRec(row1, col1, ary1, row2, col2, ary2, rslt);
				}
				k=0;
				j++;
				matrixMultiplicaitonRec(row1, col1, ary1, row2, col2, ary2, rslt);
			}
			j=0;
			i++;
			matrixMultiplicaitonRec(row1, col1, ary1, row2, col2, ary2, rslt);
			
		}catch(Exception e){
			System.out.println("Erroin::MethodName::matrixMultiplication()::"+e.getMessage());
		}
	}
}
