package recursion;

/**
 * 测试递归
 * */
public class RecursionTest {

    public static void main(String[] args){
        int res = factorial(3);
        System.out.println("res=" + res);
    }

    //阶乘问题
    public static int factorial(int n) {
        if (n == 1) { return 1;
        } else {
            return factorial(n - 1) * n; // 1 * 2 * 3
        } }

}
