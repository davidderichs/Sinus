package UE01A01Rekursion;

/**
 * Created by David on 16.10.2017.
 */
class Fibonacci{
    static int f(int n){
        if (n<=0) return 0;
        if (n==1) return 1;
        return f(n-1) + f(n-2);
    }

    public static void main(String[] args) {
        for (int i=0; i < 10; i++){
            System.out.println(Fibonacci.f(i));
        }
    }
}
