package sort;

import java.util.Arrays;

/**
 * 选择排序
 *
 *  选择排序（Selection sort）是一种简单直观的排序算法。它的工作原理如下。首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，
 *  然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。
 * 选择排序的主要优点与数据移动有关。如果某个元素位于正确的最终位置上，则它不会被移动。选择排序每次交换一对元素，
 * 它们当中至少有一个将被移到其最终位置上，因此对  n个元素的表进行排序总共进行至多n-1次交换。在所有的完全依靠交换去移动元素的排序方法中，选择排序属于非常好的一种。
 * */
public class SelectSort {

    public static void main(String[] args ){

//        int[] arr = new int[]{101,34,119,1,-1,90,123};

        int[] arr = new int[100000];

        for (int i = 0; i <  100000; i++) {
            arr[i] = (int) (Math.random()*80000000);
        }

        long startTime = System.currentTimeMillis();

        //从小到大排序
        for (int i = 0; i <  arr.length-1; i++) {
            int min = arr[i];
            int index = i;
            for (int j = i+1; j < arr.length; j++) {
                if(min > arr[j]){
                    min = arr[j];
                    index = j;
                }
            }

            int temp = arr[i];
            arr[i] = min;
            arr[index]  =temp;

//            System.out.println("第"+(i+1)+"次"+ Arrays.toString(arr));
        }

        System.out.println("用时：" + (System.currentTimeMillis() - startTime)/1000);

    }

}
