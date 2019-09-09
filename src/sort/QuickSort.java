package sort;

import java.util.Arrays;

/**
 * 快速排序（Quicksort）是对冒泡排序的一种改进。基本思想是：通过一趟排序将要排序的数据分割成独立的两部分，
 *  其中一部分的所有数据都比另外一部分的所有数据都要小，然后再按此方法对这两部分数据分别进行快速排序，
 *  整个排序过程可以递归进行，以此达到整个数据变成有序序列
 * */

public class QuickSort {
    public static void main(String[] args){
//        int[] arr = new int[]{8,9,1,7,2,3,11,5,4,6,0};

                int[] arr = new int[10000000];

        for (int i = 0; i <  10000000; i++) {
            arr[i] = (int) (Math.random()*800000000);
        }

        long startTime = System.currentTimeMillis();

//        quickSort(arr,0,arr.length - 1);
        sort(arr,0,arr.length - 1);
//        System.out.println(Arrays.toString(arr));
        System.out.println("用时：" + (System.currentTimeMillis() - startTime) +"ms");

    }





    public static void sort(int[] arr,int left,int right){
        int l = left;
        int r = right;
        int pivot = arr[(left+right)/2];
        int temp  =0;
        while(l < r){
            while(arr[l] < pivot){
                l++;
            }

            while(arr[r] > pivot){
                r --;
            }
            if( l == r){
                break;
            }

            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            if(arr[l] == pivot){
                r--;
            }
            if(arr[r] == pivot){
                l ++;
            }
        }

        if(l == r){
            l++;
            r --;
        }

        if(left < r){
            sort(arr,left,r);
        }
        if(right > l){
            sort(arr,l,right);
        }


    }


    public static void quickSort(int[] arr,int left,int right){
        int l = left;// 左下标
        int r = right;//右下标
        //pivot 中间值
        int pivot = arr[(left +  right) /2];
        int temp = 0;//临时变量，作为交换时使用
        //while循环的目的是让比pivot值小的放左边，比pivot大的值放右边
        while(l < r){
            //在pivot的左边一直查找，找到小于pivot值，才退出
            while (arr[l] < pivot){
                l++;
            }
            //在pivot的右边一直找，找到大于pivot值才退出
            while(arr[r] > pivot){
                r--;
            }

            //如果 l >= r说明pivot的左右两的值，已经按照左边全部是小于等于pivot值，右边全部是大于等于pivot值
            if(l >= r){
                break;
            }

            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果交换完成后，发现这个arr[l] == pivot值相等r--,前移
            if(arr[l] == pivot){
                r --;
            }

            //如果交换完成后，发现这个arr[r]==pivot值相等l++,后移
            if(arr[r] == pivot){
                l++;
            }
        }

        //如果 l==r ，必须l++，r--，否则出现栈溢出
        if(l == r){
            l ++;
            r --;
        }

        //向左递归
        if(left < r){
            quickSort(arr,left,r);
        }
        //向右递归
        if(right > l){
            quickSort(arr,l,right);
        }
    }

}

