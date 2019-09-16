package search;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找法
 * */
public class BinarySearch {

    public static void main(String[] args){
        int arr[] = { 1, 8, 10, 89,1000, 1000,1000,1000,1234 };
//        int findIndex = binarySearch(arr,0,arr.length-1,100);
//        System.out.println("findIndex :" + findIndex);
        List<Integer> findList = binarySearch2(arr,0,arr.length -1,10000);
        System.out.println("findList :" + findList);
    }

    /**
     * @param arr 数组
     * @param left 左边的索引
     * @param right 右边的索引
     * @param findVal 要查找的值
     * @return 如果找到就返回下标，如果没有找到就返回 -1
     * */
    public static int binarySearch(int[] arr,int left,int right,int findVal){
        //当 left>right 时，说明递归整个数组，但是没有找到
        if(left > right){
            return  -1;
        }

        int mid = (left + right ) / 2;
        int midVal = arr[mid];

        if(findVal > midVal){//向右递归
         return   binarySearch(arr,mid+1,right,findVal);
        }else if(findVal < midVal){//向左递归
          return   binarySearch(arr,left,mid -1,findVal);
        }else {
            return  mid;
        }
    }


    /**
     * @param arr 数组
     * @param left 左边的索引
     * @param right 右边的索引
     * @param findVal 要查找的值
     * @return  查找到指定值的下标数组
     * */
    public static List<Integer> binarySearch2(int[] arr, int left, int right, int findVal){
        //当 left>right 时，说明递归整个数组，但是没有找到
        if(left > right){
            return  new ArrayList<>();
        }

        int mid = (left + right ) / 2;
        int midVal = arr[mid];

        if(findVal > midVal){//向右递归
            return   binarySearch2(arr,mid+1,right,findVal);
        }else if(findVal < midVal){//向左递归
            return   binarySearch2(arr,left,mid -1,findVal);
        }else {
            /**
             * 因为是有序数组，所以只需向左移动和向右移动查找相邻的值添加到数组就可以
             *
             * 1. 在找到 mid 索引值，不要马上返回
             * 2. 向 mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合 ArrayList
             * 3. 向 mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合 ArrayList
             * 4. 将 Arraylist 返回
             * */

            List<Integer> resIndexList = new ArrayList<>();
            //向 mid 索引值的左边扫描，将所有满足 findVal， 的元素的下标，加入到集合 ArrayList
            int temp = mid -1;
            while (true){
                if(temp < 0 || arr[temp] != findVal){
                    break;
                }
                resIndexList.add(temp);
                temp--;
            }
            resIndexList.add(mid);

            //向 mid 索引值的右边扫描，将所有满足 findVal， 的元素的下标，加入到集合 ArrayList
            temp = mid + 1;
            while (true){
                if(temp > arr.length -1 || arr[temp] != findVal){
                    break;
                }
                resIndexList.add(temp);
                temp++;
            }

            return  resIndexList;
        }
    }
}
