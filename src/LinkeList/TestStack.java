package LinkeList;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestStack {
    public static void main(String[] args){
        //栈先进后出
//        Pattern pattern = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$");
        String reges = "(^[0-9]*$)||(^[A-Za-z]+$)";
//        Pattern pattern = Pattern.compile("^(?![A-Za-z0-9]+$)(?![a-z0-9\\\\W]+$)(?![A-Za-z\\\\W]+$)(?![A-Z0-9\\\\W]+$)[a-zA-Z0-9\\\\W]{8,}$");
        Pattern pattern = Pattern.compile(reges);
      Matcher match =   pattern.matcher("93890dsfjdf>");
      System.out.println("result : " +match.matches());


        Stack<String> stack = new Stack<>();
        stack.push("第一个");
        stack.push("第二个");
        stack.push("第三个");
        stack.push("第四个");
        stack.push("第五个");

        while (!stack.empty()){
            System.out.println(stack.pop());
        }
    }
}
