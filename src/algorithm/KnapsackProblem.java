package algorithm;

/**
 * 动态规划 - 背包问题
 * <p>
 * 1) 动态规划(DynamicProgramming)算法的核心思想是:将大问题划分为小问题进行解决，从而一步步获取最优解 的处理算法
 * 2) 动态规划算法与分治算法类似，其基本思想也是将待求解问题分解成若干个子问题，先求解子问题，然后从这 些子问题的解得到原问题的解。
 * 3) 与分治法不同的是，适合于用动态规划求解的问题，经分解得到子问题往往不是互相独立的。
 * ( 即下一个子 阶段的求解是建立在上一个子阶段的解的基础上，进行进一步的求解 )
 * 4) 动态规划可以通过填表的方式来逐步推进，得到最优解.
 */
public class KnapsackProblem {
    public static void main(String args[]) {
        int w[] = {1, 4, 3};//物品的重量
        int val[] = {1500, 3000, 2000};//物品的价值
        int m = 4;//背包的容量
        int n = val.length;//物品的个数
        //创建二维数组，v[i][j]表示在前i个物品中能装入容量为j的背包中的最大值
        int v[][] = new int[n + 1][m + 1];
        //为了记录放入商品的情况，我们定义了一个二维数组
        int path[][] = new int[n + 1][m + 1];
        //初始化第一行和第一列, 这里在本程序中，可以不去处理，因为默认就是 0
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;//将第一列设置为0
        }

        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;//将第一行设置为0
        }


        //根据前面的公示来动态规划处理
        for (int i = 1; i < v.length; i++) {//不处理第一行，i是从1进行开始
            for (int j = 1; j < v[0].length; j++) { //不处理第一列，j是从1开始的
                //公式
                if (w[i - 1] > j) {  //因为我们程序 i 是从 1 开始的，因此原来公式中的 w[i] 修改成 w[i-1]
                    v[i][j] = v[i - 1][j];
                } else {
                    //说明:
                    //因为我们的 i 从 1 开始的， 因此公式需要调整成
                    // v[i][j]=Math.max(v[i-1][j], val[i-1]+v[i-1][j-w[i-1]]);
//                    v[i][j] = Math.max(v[i-1][j],val[i-1]+v[i-1][j-w[i-1]]);

                    //为了记录商品存放到背包的情况，我们不能直接的使用上面的公式，需要使用 if-else 来体现公式
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]]; //把当前的情况记录到 path
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }

                }

            }

        }


        //输出一下 v 看看目前的情况
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("============================");
        //输出最后我们是放入的哪些商品
        //遍历 path, 这样输出会把所有的放入情况都得到, 其实我们只需要最后的放入
//
//        for (int i = 0; i < path.length; i++) {
//            for (int j = 0; j < path[i].length; j++) {
//                if (path[i][j] == 1) {
//                    System.out.printf("第%d 个商品放入到背包\n", i);
//                }
//            }
//        }

        //动脑筋
        int i = path.length - 1; //行的最大下标
        int j = path[0].length - 1; //列的最大下标
        while (i > 0 && j > 0) { //从path的最后开始找
            if (path[i][j] == 1) {
                System.out.printf("第%d 个商品放入到背包\n", i);
                j -= w[i - 1]; //w[i-1]
            }
            i--;
        }
    }
}
