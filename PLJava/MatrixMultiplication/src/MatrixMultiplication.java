import java.util.Random;

public class MatrixMultiplication implements Runnable {

    private static final int MAX_VALUE = 200;
    static int i=0,j=0,k=0;
    int row , col;
    int array1[][];
    int array2[][];

    MatrixMultiplication(int[][] a, int[][] b, int row, int col){
        this.array1 = a;
        this.array2 = b;
        this.row = row;
        this.col = col;
    }

    public void run(){

        //Creating the instance of the MatrixMultiplication server to call the methods.
        matrixMultiply(this.array1, this.array2, row, col);
        //Calling the matrix multiplication method.

    }
    public static void main(String[] args) {

        int row = 1000, col =1000;
        int[][] array1 = new int[1000][1000];
        int[][] array2 = new int[1000][1000];
        Random rand = new Random();
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                array1[i][j] = rand.nextInt() % MAX_VALUE;
            }
        }

        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                array2[i][j] = rand.nextInt() % MAX_VALUE;
            }
        }

        /* Sequential */
        long startTime3 = System.currentTimeMillis();
        matrixMultiply(array1, array2, row, col);
        matrixMultiply(array1, array2, row, col);
        matrixMultiply(array1, array2, row, col);
        long endTime3= System.currentTimeMillis();
        long duration = endTime3 - startTime3;
        System.out.println("Time taken for Sequential : " + duration);

        /* Concurrent */
        long startTime2= System.currentTimeMillis();
        MatrixMultiplication ms=new MatrixMultiplication(array1, array2, row, col);
        Thread myThread1=new Thread(ms);
        Thread myThread2=new Thread(ms);
        Thread myThread3=new Thread(ms);
        myThread1.setName("First Thread");
        myThread2.setName("Second Thread");
        myThread3.setName("Third Thread");

        myThread1.start();
        myThread2.start();
        myThread3.start();

        while (myThread1.isAlive() || myThread2.isAlive() || myThread3.isAlive()) {
            //wait for all threads to finish
        }
        long endTime2= System.currentTimeMillis();
        long duration2 = endTime2 - startTime2;
        System.out.println("Time taken for parallel computation : " + duration2);

    }


    //matrix initialization which will call the recursive function. Complexity of the algorithm is O(n^3)
    public static void matrixMultiply(int[][] array1, int[][] array2, int row, int col){
        try{
            int[][] rslt = new int[row][col];

            for(int x = 0; x < row*col; x++ ){
                int i = x / row;
                int k = x % row;
                int j = 0;

                while(j < row) {
                    rslt[i][j] += array1[i][k] * array2[k][j];
                    j++;
                }
            }

            //i=0;
            //Call for recursive method.
            //matrixMultiplicaitonRec(row, col, array1, array2, rslt);

        }catch(Exception e){
            System.out.println("Error in executing matrix multiplication:"+e.getMessage());
        }
    }
    //
    public static void matrixMultiplicaitonRec(int row,int col,int ary1[][],
                                                            int ary2[][],int result[][]){
        try{
            for (int i=0; i<row; i++){
                for (int j=0; j<col; j++){
                    for (int k=0; k<row; k++){
                        result[i][j] += ary1[i][k] * ary2[k][j];
                    }
                }
            }

        }catch(Exception e){
            System.out.println("Error in::MethodName::matrixMultiplication()::"+e.getMessage());
        }
    }
}
