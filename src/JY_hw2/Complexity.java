package JY_hw2;
/**
 * Assignment : 2
 * Name: Jiayin Huang
 * CWID: 10477088
 * Course: CS-570
 */

public class Complexity {

    //a method that has time complexity O(n2)
    public static void method1(int n){
        int counter = 0;
        for(int i = 0; i < n; i++){
           for(int j = 0; j < n; j++){
               counter++;
               System.out.println("Operation executes: "+ counter);
           }
       }
        System.out.println("method1 executes: "+ counter);
    }

    //a method that has time complexity O(n3)
    public static void method2(int n){
        int counter = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                for(int k = 0; k < n; k++){
                    counter++;
                    System.out.println("Operation executes: "+ counter);
                }
            }
        }
        System.out.println("method2 executes: "+ counter);

    }

    // a method that has time complexity O(logn)
    public static void method3(int n){
        int counter = 0;
        for(int i = 1; i <= n ; i = i * 2){
            counter++;
            System.out.println("Operation executes: "+ counter);
        }
        System.out.println("method3 executes: "+ counter);
    }

    //a method that has time complexity O(nlogn)
    // n * log(n)
    public static void method4(int n){
        int counter = 0;
        for(int i = 0; i < n; i++){
            for(int j = 1; j <= n; j = j * 2){
                counter++;
                System.out.println("Operation executes: "+ counter);
            }
        }
        System.out.println("method4 executes: "+ counter);
    }

    //a method that has time complexity O(loglogn)
    //take the square root of the size at each layer
    public static void method5(int n){
        if(n < 0){
            throw new IllegalArgumentException("The integer value for O(loglogn) should not be negative.");
        }
        int counter = 0;
        for(int i = n; i >= 2; i = (int) Math.sqrt(i)){
            counter++;
            System.out.println("Operation executes: "+ counter);
        }
        System.out.println("method5 executes: "+ counter);
    }

    //a  method  that  has  time  complexity  O(2n)
    //Algorithms with running time O(2^N) are often recursive algorithms
    // that solve a problem of size N by recursively solving two smaller problems of size N-1.
    public  static  int  method6(int n){
        if(n < 0){
            throw new IllegalArgumentException("Invalid input");
        }
        int counter = 0;
        if(n == 0 || n == 1){
            counter++;
            System.out.println("Operation executes: "+ counter);
            return counter;
        }else{
            return method6(n - 1) + method6(n - 1);
        }
    }

}
