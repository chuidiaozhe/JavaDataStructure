package algorithm;

import java.util.HashSet;

public class Test {
    public static void main(String args[]){
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("北京");
        hashSet.add("上海");
        hashSet.add("深圳");
        HashSet<String> temp = new HashSet<>();
        temp.retainAll(hashSet);
        System.out.println("  :" + temp);
    }
}
