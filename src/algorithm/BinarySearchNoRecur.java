package algorithm;

/**
 *
 *  二分查找方法，不用递归来写
 */
public class BinarySearchNoRecur {
    public static void main(String args[]){
        //验证
        int arr[] = {1,3,8,10,11,67,100};
        System.out.println("查找1    " + binarySearch(arr,1));
        System.out.println("查找8    " + binarySearch(arr,11));
        System.out.println("查找100    " + binarySearch(arr,100));
        System.out.println("查找200    " + binarySearch(arr,200));
    }

    /**
     * 二分查找的非递归实现
     * @param arr 待查找的数组，arr是升序排序
     * @param target 需要查找的数
     * @return  返回对应的下标，-1表示没有找到
     * */
    public static int binarySearch(int arr[] ,int target){
        int left = 0;
        int right = arr.length - 1;
        while (left <= right){
            //说明继续查找
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            }else if(arr[mid] < target){
                //向右查找
                left = mid+ 1;
            }else{
                //向左查找
                right = mid -1;
            }
        }
        return  -1;
    }
}
