
class Rekursion{
    static int f(int n){
        System.out.println(n);
        n--;
        if (n<=0) return 1;
        return f(n) + f(n-1);

    }
}


public class Main {

    public static void main(String[] args) {
        Rekursion.f(10);
    }
}
