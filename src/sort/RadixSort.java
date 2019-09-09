package sort;

import java.util.Arrays;

/**
 *  7.11.1 基数排序(桶排序)介绍:
 * 1) 基数排序(radixsort)属于“分配式排序”(distributionsort)，又称“桶子法”(bucketsort)或binsort，顾 名思义，它是通过键值的各个位的值，将要排序的元素分配至某些“桶”中，达到排序的作用
 * 2) 基数排序法是属于稳定性的排序，基数排序法的是效率高的稳定性排序法
 * 3) 基数排序(RadixSort)是桶排序的扩展
 * 4) 基数排序是1887年赫尔曼·何乐礼发明的。它是这样实现的:将整数按位数切割成不同的数字，然后按每个
 * 位数分别比较。
 *
 *  7.11.2 基数排序基本思想
 * 1) 将所有待比较数值统一为同样的数位长度，数位较短的数前面补零。然后，从最低位开始，依次进行一次排序。
 * 这样从最低位排序一直到最高位排序完成以后, 数列就变成一个有序序列。
 *
 * */
public class RadixSort {

    public static void main(String[] args){
        int[] arr = new int[]{8,9,1,7,2,3,11,5,4,6,0};
//        int[] arr = new int[10000000];
//
//        for (int i = 0; i <  10000000; i++) {
//            arr[i] = (int) (Math.random()*800000000);
//        }
//
//        long startTime = System.currentTimeMillis();

        radixSort(arr);
//        System.out.println("用时：" + (System.currentTimeMillis() - startTime) +"ms");

        System.out.println("排序后的数组 ："+Arrays.toString(arr));

    }


//    public static void radixSort(int[] arr){
//        int arrMax = arr[0];
//        for(int value : arr){
//            if(value > arrMax){
//                arrMax = value;
//            }
//        }
//
//        int maxLength = String.valueOf(arrMax).length();
//
//        for(int i = 0 , n = 1; i < maxLength;i ++,n *= 10){
//            //需要10个桶用来存放数据
//            int[][] bucket = new int[10][arr.length];
//            int[] bucketElementCounts = new int[10];
//
//            for(int j = 0;j < arr.length;j++){
//                int digitOfElement = arr[j]/n%10;
//                bucket[digitOfElement][bucketElementCounts[digitOfElement]]  = arr[j];
//                bucketElementCounts[digitOfElement]++;
//            }
//
//            int index = 0;
//            for(int k = 0; k < bucketElementCounts.length;k++){
//                if(bucketElementCounts[k] > 0){
//                    for(int l = 0;l < bucketElementCounts[k]; l++){
//                        arr[index] = bucket[k][l];
//                        index++;
//                    }
//                }
//                bucketElementCounts[k]= 0;
//            }
//
//        }
//
//    }

    //基数排序方法
    public static void radixSort(int[] arr){
        //先求出数组中最大数的位数
        int max = arr[0];
        for (int value:arr){
            if(value > max){
                max = value;
            }
        }

        //获得最大数的位数
        int maxLength = String.valueOf(max).length();

        /*
         * 定义一个二维数组，表示10个桶，每个桶是一个一维数组
         * 说明：
         *  1.二维数组包含10个一维数组
         *  2.为了防止在放入数组的时候，数据溢出，则每个一维数组，大小定为arr.length
         *  3.明确了，基数排序是使用空间换取时间的经典算法
         */

        int[][] bucket = new int[10][arr.length];
        //为了记录每个桶中，实际存放了多少个数据，我们需要定义一个一维数组来存放每个桶每次放入数据的下标
        int[] bucketElementCounts  = new int[10];

        //处理存入桶中的逻辑

        for (int i = 0 ,n = 1; i < maxLength ; i++,n *= 10) {
             //针对每个元素对应位进行排序处理
            for (int j = 0; j <  arr.length; j++) {
                //取出每个元素对应位的值
                int digitOfElement = arr[j] / n % 10;
                //放入到对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }

            //按照这个桶的顺序（一维数组的下标依次取出数据，放入原来的数组）
            int index = 0;
            //遍历每一个桶，并将桶中的数据，放入到原数组
            for (int k = 0; k < bucketElementCounts.length ; k++) {
                //如果桶中有数据，我们才放入到原数组
                if(bucketElementCounts[k] > 0){
                    for (int j = 0; j <  bucketElementCounts[k]; j++) {
                        //取出元素，放入到arr
                        arr[index] = bucket[k][j];
                        index ++;
                    }
                }
                bucketElementCounts[k] = 0;
            }

        }

    }
}
