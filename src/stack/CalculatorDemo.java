package stack;

import com.sun.org.apache.bcel.internal.generic.GOTO;

import java.sql.Statement;

/**
 * 用栈的方式模拟一个简单的数字计算
 * @see {https://www.bilibili.com/video/av54029771/?p=34}
 *
 *  使用栈完成表达式的计算思路：
 *   1.通过一个index值（索引），来遍历我们的表达式
 *   2. 如果我们发现是一个数字，就直接如栈
 *   3.如果发现扫描到是一个符号，就分如下情况
 *    3.1如果发现当前的符号栈为空，就直接入栈
 *    3.2如果符号栈有操作符，就进行比较，如果当前的栈操作符的优先级小于或等于符号栈顶的操作符，
 *       就需要从数栈中pop出两个数，再从符号栈中pop出一个符号，进行运算，将得到的结果入数栈，然后
 *       将当前的操作符入符号栈，如果当前的操作符的优先级大于符号栈中的操作符，就直接入符号栈
 *       注意：当加入的操作符小于等于栈顶操作符时，要先计算该栈顶顶操作符
 *   4.当表达式扫描完毕，就顺序的从数栈和符号栈中opo出相应的数和符号，并运算入数栈
 *   5.最后在数栈中只有一个数字，就是表达式的结果
 * */

public class CalculatorDemo {

    public static void main(String[] args){
         //完成表达式的运算
         String expression = "3+2*51/3/2-5+10+2";
         //创建两个栈，一个是数栈，一个是符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);

        //定义需要的相关变量
        int index = 0;//用于扫描
        int num1 = 0;
        int num2 = 0;
        char oper = ' ';
        int res = 0;
        char ch = ' ';// 每次扫描得到的值
        String keepNum = "";
        //开始while循环的扫描expression
        while (true){
            //依次得到expression的每一个字符
            ch = expression.toCharArray()[index];
            //判断ch是什么，然后做相应的处理
            if(operStack.isOper(ch)){//如果是运算符
               //判断当前的符号栈是否为空
               if(!operStack.isEmpty()){
               // 如果符号栈有操作符，就进行比较，如果当前的操作符的优先级小于或等于符号栈顶的操作符
                if(operStack.priority(ch) <= operStack.priority((char)operStack.peek())){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = (char)operStack.pop();
                        res =  numStack.cal(num1,num2,oper);
                        //把运算符的结果入数栈
                        numStack.push(res);
                        //如果符号栈出栈后，当前的操作符的优先级小于或等于符号栈顶的操作符，则继续执行符号出栈计算
                     if( !operStack.isEmpty() && operStack.priority(ch) <= operStack.priority((char)operStack.peek())){
                         continue;
                     }else{
                         //然后将当前的操作符入符号栈
                         operStack.push(ch);
                     }
                   }else{
                       //如果当前的操作符的优先级大于栈中的操作符，就直接入符号栈
                       operStack.push(ch);
                   }
               }else{
                   //如果为空直接入符号栈
                   operStack.push(ch);
               }
            }else{
                //如果是数，则直接入数栈
//                numStack.push( ch -48);

                keepNum = keepNum+ch;
                if(index == expression.length() -1){
                    numStack.push(Integer.valueOf(keepNum));
                }else{
                    if(operStack.isOper(expression.toCharArray()[index+1])){
                        numStack.push(Integer.valueOf(keepNum));
                        keepNum = "";
                    }
                }
            }
            //让index+1，并判断是否扫描到expression的末尾
            index ++;
            if(index >= expression.length()){
                break;
            }
        }

        //当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应的数字和符号，并运算
        while (true){
            //如果符号栈为空，则计算到最后的结果，数栈中只有一个数字(结果)
            if(operStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = (char)operStack.pop();
            res = numStack.cal(num1,num2,oper);
            numStack.push(res);//入栈
        }

        //将数栈的最后数，pop出，就是结果
        int res2 = numStack.pop();
        System.out.printf("%s=%d\n",expression,res2);
    }

}

//定义一个ArrayStack表示栈
class ArrayStack2{
    public int maxSize;//栈存放的最大值
    public int[] stack; //用来存放数据的栈
    public int top = -1; // 栈的顶部，初始化为-1

    //构造器
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack  = new int[maxSize];
    }

    //栈空
    public  boolean isEmpty(){
        return  top == -1;
    }

    //栈满
    public boolean isFull(){
        return  top == maxSize -1;
    }

    //入栈-push
    public void push(int value){
        if(isFull()){
            System.out.println("栈已满");
        }else{
            top++;
            stack[top] = value;
        }
    }

    //出栈-pop,将栈顶的数据返回
    public int pop(){
        if(isEmpty()){
            throw  new RuntimeException("栈空，没有数据！");
        }
        int obj = stack[top];
        top --;
        return obj;
    }

    public int peek(){
        return stack[top];
    }

    //遍历栈
    public void list(){
        if(isEmpty()){
            System.out.println("list ： 栈空，没有数据");
        }else{
            for (int i = top; i >= 0; i--) {
                System.out.printf("stack[%d]=%s\t",i,stack[i]);
            }
            System.out.println();
        }
    }

    /**
     * 返回运算符的优先级，优先级由程序员来确定，优先级使用数字表示
     * 数字越大，则优先级越高
     * */
    public int priority(char oper){
        if(oper == '+' || oper == '-'){
            return 0;
        }else if(oper == '*' || oper == '/'){
             return 1;
        }else{
            return  -1;//假定目前的表达式只有+，-，*，/
        }
    }

    /**
     * 判断是不是一个运算符
     * */
    public boolean isOper(char val){
        return  val == '+' || val == '-' || val == '*' || val =='/';
    }

    /**
     * 计算方法
     * */
    public int cal(int num1,int num2,int oper){
        int res = 0;//res用于存放计算的结果
        switch (oper){
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num2 * num1;
                break;
            case '/':
                res = num2 / num1;
                break;
        }

        return  res;
    }
}
