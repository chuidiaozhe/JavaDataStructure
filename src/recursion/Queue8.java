package recursion;

/**
 * 八皇后问题，是一个古老而著名的问题，是回溯算法的典型案例。该问题是国际西洋棋棋手马克斯·贝瑟尔于
 * 1848 年提出:在 8×8 格的国际象棋上摆放八个皇后，使其不能互相攻击，即:任意两个皇后都不能处于同一行、 同一列或同一斜线上
 * ，问有多少种摆法(92)。
 */
public class Queue8 {
    //定义一个max表示共有多少个皇后
    int max = 8;
    //定义数组array，保存皇后位置的结果，比如arr={0,3,4,5,2,6,1,3}
    int[] array = new int[max];

    static int count = 0;
    static int judgeCount = 0;

    public static void main(String[] args) {
         Queue8 queue8 = new Queue8();
         queue8.check(0);
        System.out.printf("一共有%d 解法\n", count);
        System.out.printf("一共判断冲突的次数%d 次", judgeCount); // 1.5w
    }


    private void check(int n) {
       if(n == max){ // n = 8，其实8个皇后就已经放好了
           print();
           return;
       }
       //依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            //先把当前这个皇后n，放到该行的第一列
            array[n] = i;
            //判断当放置第n个皇后到i列时，是否冲突
            if(judge(n)){//不冲突
                //接着放n+1个皇后，即开始递归
                check(n+1);
            }
            //如果冲突，就继续执行 array[n] = i; 即将第 n 个皇后，放置在本行得 后移的一个位置
        }
    }

    /**
     * 查看当我们放置第n个皇后，就去检测该皇后是否和前面已经放置的皇后冲突
     *
     * @param n 表示第几个皇后
     * @return 返回true表示不冲突，返回false表示冲突
     */
    private boolean judge(int n) {
        judgeCount++;
        for (int i = 0; i < n; i++) {
            //说明：
            //1. array[i] == array[n]表示判断第n个皇后是否与前面的n-1个皇后在同一列
            //2.Math.abs(n-i) == Math.abs(array[n] - array[i]) 表示判断第 n 个皇后是否和第 i 皇后是否在同一斜线
            //n=1 放置第 2列 1n=1array[1]=1
            // Math.abs(1-0) == 1 Math.abs(array[n] - array[i]) = Math.abs(1-0) = 1
            // 3. 判断是否在同一行, 没有必要，n 每次都在递增
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    //写一个方法，可以将皇后摆放的位置输出
    private void print() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }


}
