public class PerfectNumber {
    public static  void main(String args[]){
        long startTime = System.nanoTime();
        System.out.println("6 is perfect? " + isPerfect(6));
        System.out.println("33550336 is perfect : " + isPerfect(33550336));
        System.out.println("33550337 is perfect : " + isPerfect(33550337));
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println("Duration :" + duration);

        Thread t1= new PerfectThread(6);
        Thread t2= new PerfectThread(33550336);
        Thread t3= new PerfectThread(33550337);
        long startTime2 = System.nanoTime();
        t1.start();
        t2.start();
        t3.start();
        long endTime2 = System.nanoTime();
        long duration2 = endTime2 - startTime2;
        System.out.println("Duration Thread :" + duration2);
    }

    public static int sumOfActors(int number){
        int sum = 0;
        for(int i=1; i<= number; i++ ){
            if(number % i == 0) {
                sum = sum +i;
            }
        }
        return sum;
    }

    public static boolean isPerfect(int number){
        return 2*number == sumOfActors(number) ? true : false;
    }
}
