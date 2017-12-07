public class PerfectThread extends Thread{
    int number;

    PerfectThread(int number){
        this.number = number;
    }

    public void run(){
        System.out.println(number +" is perfect? " + isPerfect(number));
    }



    public int sumOfActors(int number){
        int sum = 0;
        for(int i=1; i<= number; i++ ){
            if(number % i == 0) {
                sum = sum +i;
            }
        }
        return sum;
    }

    public boolean isPerfect(int number){
        return 2*number == sumOfActors(number) ? true : false;
    }
}
