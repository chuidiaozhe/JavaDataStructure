package sort;

import java.util.Arrays;

/**
 * 插入排序
 * <p>
 * 插入排序（英语：Insertion Sort）是一种简单直观的排序算法。它的工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，
 * 找到相应位置并插入。插入排序在实现上，通常采用in-place排序（即只需用到O(1)}的额外空间的排序），
 * 因而在从后向前扫描过程中，需要反复把已排序元素逐步向后挪位，为最新元素提供插入空间。
 */
public class InsertSort {

    public static void main(String[] args) {
        //从小到达排序
//        int[] arr = new int[]{101, 34, 119, 1, -1, 90, 123};

        int[] arr = new int[100000];

        for (int i = 0; i <  100000; i++) {
            arr[i] = (int) (Math.random()*80000000);
        }

        long startTime = System.currentTimeMillis();


        for (int i = 1; i < arr.length; i++) {
            ////定义待插入的数
            int insertValue = arr[i];
            int insertIndex = i - 1;// 即 arr[1]的前面这个数的下标

            // 给 insertVal 找到插入的位置
            // 说明
            // 1. insertIndex >= 0 保证在给 insertVal 找插入位置，不越界
            // 2. insertVal < arr[insertIndex] 待插入的数，还没有找到插入位置
            // 3. 就需要将 arr[insertIndex] 后移
            while (insertIndex >= 0 && arr[insertIndex] < insertValue) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }

            // 当退出 while 循环时，说明插入的位置找到, insertIndex + 1
            // 举例:理解不了，我们一会 debug
            // 这里我们判断是否需要赋值
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertValue;
            }


//            System.out.println("第" + (i + 1) + "次" + Arrays.toString(arr));
        }

        System.out.println("用时：" + (System.currentTimeMillis() - startTime)/1000);

    }

}
