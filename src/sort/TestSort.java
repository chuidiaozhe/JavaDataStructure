package sort;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.Arrays;
import java.util.Random;

public class TestSort {

    public static void main(String[] args) {
              int[] arr = new int[]{9,-1,0,7,-2,10,11};

//        int[] arr = new int[10];
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = (int) ((Math.random()) * 1000);
//        }

//        bubbleSort(arr);
//        selectSort(arr);
          insertSort(arr);
        System.out.println("排序后的数组：" + Arrays.toString(arr));
    }



    public static void insertSort(int[] arr){
        for (int i = 1; i <  arr.length; i++) {
            int insertValue = arr[i];
            int insertIndex = i - 1;
            while(insertIndex >= 0 && arr[insertIndex] > insertValue){
                arr[insertIndex+1] = arr[insertIndex];
                insertIndex--;
            }
            if(insertIndex+1 != i){
                arr[insertIndex+1] = insertValue;
            }
        }

    }
    public static void selectSort(int[] arr){
        int min;
        int index;
        for(int i = 0;i < arr.length; i++){
            min = arr[i];
            index = i;
            for(int j = i+1; j < arr.length;j ++){
                if(min > arr[j]){
                    min = arr[j];
                    index = j;
                }
            }
            if( index != i){
                 arr[index] = arr[i];
                arr[i] = min;
            }
        }
    }

    //冒泡排序法
    public static void bubbleSort(int arr[]) {
        int temp;
        boolean flag = false;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (!flag) {
                break;
            }else{
                flag = false;
            }
        }
    }
}
