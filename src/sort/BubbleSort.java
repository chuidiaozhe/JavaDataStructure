package sort;

import java.util.Arrays;

/**
 * 冒泡排序
 * */
public class BubbleSort {

    public static void main(String[] args){
//      int[] arr = new int[]{9,-1,0,7,-2,10,11};
      int[] arr = new int[100000];

        for (int i = 0; i <  100000; i++) {
            arr[i] = (int) (Math.random()*80000000);
        }

        long startTime = System.currentTimeMillis();
         for (int i = 0; i < arr.length; i++) {
            int temp = 0;
            boolean isExchange = false;
            for (int j = 0; j < arr.length -1- i ; j++) {
                if(arr[j] > arr[j+1]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    isExchange = true;
                }
            }
//            System.out.println("第"+(i+1)+"次"+Arrays.toString(arr));
           if(!isExchange){
               break;
           }
        }

         System.out.println("用时：" + (System.currentTimeMillis() - startTime)/1000);
//        System.out.println(Arrays.toString(arr));
    }

}
