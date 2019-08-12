package stack;

 import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args){
//       String zhongzhui = "5+((1+2)*4)-3";
       String zhongzhui = "1+((2+3)*4)-5";
        String result = zhongzhuiToHouzhui(zhongzhui);
        String result2 = parseSuffixExpreesionList(toinfixExpressionList(zhongzhui)).toString();
       System.out.println("result : " + result);
       System.out.println("result2 : " + result2);


        //先定义逆波兰表达式
        //（3+4）*5-6 =》 3 4 + 5 * 6 -
        //为了方便，逆波兰表达式的数字和符号使用空格隔开
        String suffixExpression = "3 4 + 5 * 6 -";
        //思路
        //1.先将  "3 4 + 5 * 6 -" =>放到ArrayList中
        //2.将ArrayList传递给一个方法，遍历ArrayList配合栈完成计算

        System.out.printf("%s=%d\n",suffixExpression,calculate(getListString(suffixExpression)));

    }

    //将一个逆波兰表达式，依次将数据和运算符放入到ArrayList中
    public static ArrayList<String> getListString(String suffixExpression){
        //将suffixWxpression分割
        String [] split = suffixExpression.split(" ");
        ArrayList<String> list = new ArrayList<String>();
         for (String ele : split){
             list.add(ele);
         }

         return  list;
     }


     /**
      * 完成对逆波兰表达式的运算
      *
      * */
     public static int calculate(List<String> ls){
        //创建一个栈
         Stack<String> stack = new Stack<>();
         //遍历ls
         for (String item : ls){
             if(item != null){
                 if(item.matches("\\d+")){
                     //是数字则直接入栈
                     stack.push(item);
                 }else{
                     //符号进行计算
                     int num2 = Integer.parseInt(stack.pop());
                     int num1 = Integer.parseInt(stack.pop());
                     int res = 0;
                     if(item.equals("+")){
                         res = num1 + num2;
                     }else if(item.equals("-")){
                         res = num1 - num2;
                     }else if(item.equals("*")){
                         res = num1 * num2;
                     }else if(item.equals("/")){
                         res = num1 / num2;
                     }else {
                         throw  new RuntimeException("运算符有误");
                     }

                     stack.push("" + res);
                 }
             }
         }
        return  Integer.valueOf(stack.pop());
     }

     public static List<String> parseSuffixExpreesionList(List<String> ls){
         //定义两个栈
         Stack<String> s1 = new Stack<>();
         //说明：因为s2这个栈，在整个转换过程中，没有pop操作，而且后面我们还需要逆序输出
         //因此比较麻烦，这里我们就不用Stac<String>直接使用List<String> s2
//         Stack<String> s2 = new Stack<>();//存储中间结果的栈s2
         List<String> s2 = new ArrayList<>();//储存中间结果的list2

         //遍历ls
         for (String item : ls){
             //如果是一个数，加入s2
             if(item.matches("\\d+")){
                 s2.add(item);
             }else if(item.equals("(")){
                 s1.push(item);
             }else if(item.equals(")")){
         //如果是右括号“)”，则依次弹出 s1 栈顶的运算符，并压入 s2，直到遇到左括号为止，此时将这一对括号丢弃
                 while (!s1.peek().equals("(")){
                     s2.add(s1.pop());
                 }
                 s1.pop();//将（弹出s1栈，消除小括号

             }else {
                 //当 item 的优先级小于等于 s1 栈顶运算符, 将 s1 栈顶的运算符弹出并加入到 s2 中，再次转到(4.1) 与 s1 中新的栈顶运算符相比较
                 while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)){
                     s2.add(s1.pop());
                 }
                 //还需要将item压入栈
                 s1.push(item);
             }
         }
         //将s1中剩余的运算符依次弹出并加入s2
         while (!s1.isEmpty()){
             s2.add(s1.pop());
         }
         return  s2; //注意因为是存在list，因此安顺序输出就是对应的后缀表达式对应的list
     }

     //将中缀表达式转成对应的list
     public static List<String> toinfixExpressionList(String s){
         //定义一个List，存放中缀表达式对应的内容
         List<String> ls = new ArrayList<>();
         int i = 0;//这时是一个指针，用于遍历中缀表达式字符串
         String str;//对应多位数的拼接
         char c = 0;//每遍历一个字符，就放入到c
         do {
             //如果c是一个非数字，我需要加入到ls
             if((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57){
                 ls.add(""+c);
                 i++; //i 需要后移
             }else{
                //如果是一个数字，需要考虑多位数
                str = "";
                while (i < s.length() && (c = s.charAt(i)) >= 48 &&  (c= s.charAt(i)) <= 57){
                    str += c;//拼接
                    i++;
                }
                ls.add(str);
             }

         }while (i < s.length());
         return ls;
     }

     /**
      *
      * 步骤：
      *   （1）若取出的字符是操作数，则分析出完整的运算数，该操作数直接送入S2栈
      * （2）若取出的字符是运算符，则将该运算符与S1栈栈顶元素比较，如果该运算符优先级大于S1栈栈顶运算符优先级，则将该运算符进S1栈，否则，将S1栈的栈顶运算符弹出，送入S2栈中，
      *   直至S1栈栈顶运算符低于（不包括等于）该运算符优先级，则将该运算符送入S1栈。
      * （3）若取出的字符是“（”，则直接送入S1栈栈顶。
      * （4）若取出的字符是“）”，则将距离S1栈栈顶最近的“（”之间的运算符，逐个出栈，依次送入S2栈，此时抛弃“（”。
      * （5）重复上面的1~4步，直至处理完所有的输入字符
      * （6）若取出的字符是“#”，则将S1栈内所有运算符（不包括“#”），逐个出栈，依次送入S2栈。
      * 完成以上步骤，S2栈便为逆波兰式输出结果。不过S2应做一下逆序处理。便可以按照逆波兰式的计算方法计算了！
      * */
     public static String zhongzhuiToHouzhui(String str){

          String result = "";
          Stack<String> s1 = new Stack<>();
          Stack<String> s2 = new Stack<>();
          if(str != null && str.length() > 2){
             char[] arr = str.toCharArray();
             String sb = "";
              for (int i = 0; i < arr.length ; i++) {
                  char ch = arr[i];

                  if(String.valueOf(ch).matches("\\d+")){
                      //是数字
                      sb = sb + ch;
                      if(i == arr.length -1){
                          s2.push(sb);
                          sb = "";
                      }else{
                         if(!String.valueOf(arr[i+1]).matches("\\d+")){
                            s2.push(sb);
                            sb = "";
                        }
                      }
                  }else{
                      String chStr = String.valueOf(ch);
                    if(chStr.equals("+") || chStr.equals("-") || chStr.equals("*") || chStr.equals("/")){
                       //运算符
                        if (s1.isEmpty()){
                            s1.push(chStr);
                        }else{
                           String pop = s1.peek();
                           if( operGrade(chStr) > operGrade(pop)){
                               s1.push(chStr);
                           }else{
                               while (true){
                                   if(s1.isEmpty()){
                                       break;
                                   }
                                   String peek = s1.peek();
                                   if(operGrade(peek) < operGrade(chStr)){
                                         break;
                                   }else{
                                       s2.push(s1.pop());
                                   }
                               }
                               s1.push(chStr);

                           }
                        }
                    }else{
                        //括号
                        if(chStr.equals("(")){
                            s1.push(chStr);
                        }else if(chStr.equals(")")){
                            while (true){
                                String peek = s1.peek();
                                if(peek.equals("(")){
                                    s1.pop();
                                    break;
                                }else{
                                    s2.push(s1.pop());
                                }
                            }
                        }
                    }
                  }
              }
          }
           while (!s1.isEmpty()){
               s2.push(s1.pop());
           }
           while (!s2.isEmpty()){
               result = s2.pop()+ " "+ result;
           }

          return result;

     }

     public static int operGrade(String str){
         switch (str){
             case "+":
                 return  1;
              case "-":
                 return  1;
              case "*":
                 return 2;
              case "/":
                 return  2;
             case "(":
                 return 0;
             case ")":
                 return 0;
                  default:
                     throw  new RuntimeException("操作符错误");
         }
     }

     /**
      * 编写一个类Operation可以返回一个运算符对应的优先级
      * */
  public static    class  Operation{
         private static int ADD = 1;
         private static int SUB = 1;
         private static int MUL = 2;
         private static int DIV = 2;

         //写一个方法，返回对应的优先级数字
         public static int getValue(String operation){
             int result = 0;
             switch (operation){
                 case "+":
                     result = ADD;
                     break;
                 case "-":
                     result = SUB;
                     break;
                 case "*":
                     result = MUL;
                     break;
                 case "/":
                     result = DIV;
                     break;
                     default:
                         System.out.println("不存在该运算符！");
                         break;

             }
             return  result;
         }
    }
}
