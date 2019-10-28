package tree;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDemo {

    public static void main(String[] args){
      Integer integer =   Integer.parseInt("110",2);
      System.out.println("integer: " + integer);
      String str = Integer.toBinaryString(-6);
      System.out.println("str : " + str);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY年MM月dd日 E");
        System.out.println("" + simpleDateFormat.format(new Date(System.currentTimeMillis())));
    }

}
