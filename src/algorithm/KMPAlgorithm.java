package algorithm;


import java.util.Arrays;

/**
 * KMP 算法
 * */
public class KMPAlgorithm {

    public static void main(String args[]){

        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";

//        String dst = "ABCDABD";
//        int[] next = kmpNext(dst);
//        System.out.println(Arrays.toString(next));

       int index =  kmpSearch(str1,str2);
       System.out.println("index= " + index);
    }


    /**
     * @param str1 源字符串
     * @param str2 子串
     * @return  如果是-1就是没有匹配到，否则返回第一个匹配的位置
     * */
    public static int kmpSearch(String str1,String str2){
        int next[] = kmpNext(str2);
        //遍历
        for (int i = 0,j = 0; i < str1.length();i++){
            //需要处理str1.charAt(i) != str2.charAt(j),去调整j的大小
            //KMP 算法核心点, 可以验证...
            while (j> 0 && str1.charAt(i) != str2.charAt(j)){
                j = next[j-1];
            }
            if(str1.charAt(i) == str2.charAt(j)){
                j++;
            }
            if (j == str2.length()){ //找到了
                return i - j+ 1;
            }
        }
        return -1;
    }

    /**
     * 获取一个字符串（子串）的部分匹配值表
     * */
    public static int[] kmpNext(String dst){
        //创建一个next数组保存部分匹配值
        int[] next =new int[dst.length()];
        next[0] = 0;//如果字符串的长度为1，部分匹配值就是0
        for (int i = 1,j= 0;i < dst.length();i++){
            //当dst.charAt(i) !=  dst.charAt(j),我们需要从next[j-1]获取新的j
            //知道我们发现有dst.charAt(i) == dst.charAt(j)成立才退出
            //这是kmp算法的核心点
            while (j > 0 && dst.charAt(i) !=  dst.charAt(j)){
                j = next[j-1];
            }

            //当dst.charAt(i) == dst.charAt(j) 满足时，部分匹配值就是+1
            if(dst.charAt(i) == dst.charAt(j)){
                j++;
            }
            next[i] = j;
        }

        return next;

    }
}
