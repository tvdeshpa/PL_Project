public class PerfectNumber {
    public static  void main(String args[]){
        long startTime = System.currentTimeMillis();
        System.out.println("6 is perfect? " + isPerfect(6));
        System.out.println("533550336 is perfect : " + isPerfect(533550336));
        System.out.println("633550337 is perfect : " + isPerfect(633550337));
        System.out.println("935503371 is perfect : " + isPerfect(935503371));
        System.out.println("735503371 is perfect : " + isPerfect(735503371));
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("Duration for Sequential :" + duration);

        Thread t1= new PerfectThread(6);
        Thread t2= new PerfectThread(533550336);
        Thread t3= new PerfectThread(633550337);
        Thread t4= new PerfectThread(935503371);
        Thread t5= new PerfectThread(735503371);
        long startTime2 = System.currentTimeMillis();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        while(t1.isAlive() || t2.isAlive() || t3.isAlive() || t4.isAlive() || t5.isAlive()){
                // waits
        }
        long endTime2 = System.currentTimeMillis();
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
