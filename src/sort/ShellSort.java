package sort;

import java.util.Arrays;

/**
 * 希尔排序法介绍:
 * 希尔排序是希尔（Donald Shell）于1959年提出的一种排序算法。希尔排序也是一种插入排序，它是简单插入排序经过改进之后的一个更高效的版本，也称为缩小增量排序。
 *
 * 希尔排序法基本思想:
 * 希尔排序是把记录按下标的一定增量分组，对每组使用直接插入排序算法排序；随着增量逐渐减少，每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止
 * */
public class ShellSort {

    public static void main(String[] args){
        int[] arr = new int[]{8,9,1,7,2,3,11,5,4,6,0};

//        int[] arr = new int[100000];
//
//        for (int i = 0; i <  100000; i++) {
//            arr[i] = (int) (Math.random()*80000000);
//        }

        long startTime = System.currentTimeMillis();

        shellSort(arr);
//        shellSort2(arr);
        System.out.println("排序后的数组："+Arrays.toString(arr));
        System.out.println("用时：" + (System.currentTimeMillis() - startTime) +"ms");

    }


//    public static void shellSort(int[] arr){
//
//        for(int gap = arr.length/2 ; gap > 0 ; gap = gap/2){
//            for(int i = gap ; i < arr.length; i++){
//                for(int j = i -gap ; j  >= 0; j= j-gap){
//                    if(arr[j] > arr[j+gap]){
//                        int temp = arr[j];
//                        arr[j] = arr[j+gap];
//                        arr[j+gap] = temp;
//                    }
//                }
//            }
//        }
//
//    }

    //采用交换法进行排序
    public static void shellSort(int[] arr){
        int gap = arr.length / 2;
        int count = 0;
        int tem;
       while (gap > 0){
           count++;
           for (int i = gap;i < arr.length;i++){
               //遍历各组中所有的元素（共gap组，每组有个元素），步长gap
               for (int j = i - gap; j >= 0; j-= gap) {
                   //如果当前元素大于加上步长后的那个元素，说明交换
                   if(arr[j] > arr[j+gap]){
                       tem = arr[j];
                       arr[j] = arr[j+gap];
                       arr[j+gap] = tem;
                   }
               }
           }
//           System.out.println("第"+count+"轮排序后的数组：" + Arrays.toString(arr));
           gap = gap/2;
       }
    }

    //对交换法的希尔排序进行优化-》移位法
    public static void shellSort2(int[] arr){
        //增量gap，并逐步的缩小增量
        for (int gap = arr.length /2 ; gap > 0 ; gap/=2) {
               //从第gap个元素，逐个对其所在的组进行直接插入排序
              for (int i = gap; i < arr.length ;i++){
                  int j = i;
                  int temp = arr[j];
                  if(arr[j] < arr[j - gap]){
                      while (j - gap >= 0 && temp < arr[j-gap]){
                          //移动
                          arr[j] = arr[j-gap];
                          j-=gap;
                      }
                      //当退出while后，就给temp找到插入的位置
                      arr[j] = temp;
                  }
              }
        }
    }

//    public static void shellSort2(int[] arr){
//        for(int gap = arr.length/2; gap > 0 ; gap = gap/2){
//            for(int i = gap; i  < arr.length; i++){
//                int j = i;
//                int temp = arr[j];
//                if(arr[j] < arr[j-gap]){
//                    while(j - gap >= 0 && temp < arr[j-gap]){
//                        arr[j] = arr[j-gap];
//                        j= j-gap;
//                    }
//                    arr[j] = temp;
//                }
//            }
//        }
//    }

}



