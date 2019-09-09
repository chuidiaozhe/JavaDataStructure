package sort;


import java.util.Arrays;

/**
 * 归并排序(MERGE-SORT)是利用归并的思想实现的排序方法，该算法采用经典的分治(divide-and-conquer)
 * 策略(分治法将问题分(divide)成一些小的问题然后递归求解，而治(conquer)的阶段则将分的阶段得到的各答案"修 补"在一起，即分而治之)。
 */
public class MergetSort {

    public static void main(String[] args) {
//        int[] arr = new int[]{8, 9, 1, 7, 2, 3, 11, 5, 4, 6, 0};



                        int[] arr = new int[10000000];

        for (int i = 0; i <  10000000; i++) {
            arr[i] = (int) (Math.random()*800000000);
        }

        long startTime = System.currentTimeMillis();

        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);
                System.out.println("用时：" + (System.currentTimeMillis() - startTime) +"ms");

//        System.out.println("归并方法排序后：" + Arrays.toString(arr));
    }




    //分+合方法
    public static void mergeSort(int arr[], int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;//中间索引
            //向左递归进行分解
            mergeSort(arr, left, mid, temp);
            //向右递归进行分解
            mergeSort(arr, mid + 1, right, temp);
            //合并
            merge(arr, left, mid, right, temp);
        }
    }

//    public static void mergeSort(int arr[],int left,int right,int[] temp){
//        if(left < right){
//            int mid = (left+right)/2;
//            //向左递归
//            mergeSort(arr,left,mid,temp);
//            //向右递归
//            mergeSort(arr,mid+1,right,temp);
//
//            merge(arr,left,mid,right,temp);
//        }
//    }
//
//    public static void merge(int[] arr,int left,int mid,int right,int[] temp){
//
//        int i = left;
//        int j = mid+ 1;
//        int tep = 0;
//
//        while(i <= mid && j <= right){
//            if(arr[i] <= arr[j]){
//                temp[tep] = arr[i];
//                tep++;
//                i++;
//            }else{
//                temp[tep]= arr[j];
//                tep++;
//                j++;
//            }
//        }
//
//        while(i <= mid){
//            temp[tep] = arr[i];
//            tep++;
//            i++;
//        }
//
//        while(j <= right){
//            temp[tep]= arr[j];
//            tep++;
//            j++;
//        }
//
//        //拷贝临时数组值到数组中
//
//        tep = 0;
//        int tempLeft = left;
//        while(tempLeft <= right){
//            arr[tempLeft] = temp[tep];
//            tempLeft++;
//            tep++;
//        }
//
//    }

    /**
     * 合并的方法
     *
     * @param arr   排序的原始数组
     * @param left  左边有序序列的初始索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  做中转的数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;//初始化i，左边有序序列的初始索引
        int j = mid + 1;//初始化j,右边有序序列的初始索引
        int t = 0;//指向temp数组的当前索引

        /**
         * 第一步
         *  先把左右两边（有序）的数据安装规则填充到temp数组
         *  直到左右两边的有序序列，有一边处理完毕为止
         * */
        while (i <= mid && j <= right) {

            //如果左边的有序序列的当前元素，小于等于右边有序序列的当期元素
            //即将左边的当前元素，填充到temp数组，然后t++，i++
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t++;
                i++;
            } else {
                //反之，将右边有序序列的当前元素，填充到temp数组
                temp[t] = arr[j];
                t++;
                j++;
            }
        }

        /**
         * 第二步
         *  把有剩余数据的一边的数据依次全部填充到temp
         * */

        while (i <= mid) {//左边的有序序列还有剩余的元素，就全部填充到temp
            temp[t] = arr[i];
            t++;
            i++;
        }
        while (j <= right) {
            temp[t] = arr[j];
            t++;
            j++;
        }

        /**
         * 第三步
         * 将temp数组的元素拷贝到arr，注意：并不是每次都拷贝所有
         */

        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }
    }
}
