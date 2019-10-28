package tree;


import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.*;
import java.util.*;

/**
 * 哈夫曼压缩
 */
public class HuffmanCodeDemo {

    public static void main(String args[]) {
        String content = "i like like like java do you like a java";
//        List<Node> nodes = getNodes(content.getBytes());
//       Node rootNode = createHuffmanTree(nodes);
//        getCodes(rootNode);
//        System.out.println("result : " + huffmanCodes.toString());
//        byte[] zipByte =  zip(content.getBytes(),huffmanCodes);
//        System.out.println("zip result : " + Arrays.toString(zipByte));


        //测试数据压缩，解压缩
//        byte[] zipByte = huffmanZip(content.getBytes());
//        System.out.println("zip result :" + Arrays.toString(zipByte));
//        byte[] decodeByte = decode(huffmanCodes, zipByte);
//        System.out.println("解码 ：   " + new String(decodeByte));

        //测试文件压缩，解压缩

        String srcPath  ="/Users/Xiangshifu/Documents/DaiMa/DataStructure/src/tree/src.bmp";
        String dstPaht = "/Users/Xiangshifu/Documents/DaiMa/DataStructure/src/tree/test.zip";
        String unZipPath = "/Users/Xiangshifu/Documents/DaiMa/DataStructure/src/tree/unzip.bmp";
//        zipFile(srcPath,dstPaht);
//        System.out.println("压缩文件完成~~~~~");

        unZipFile(dstPaht,unZipPath);
        System.out.println("解压缩完成~~~~~~~~");



    }


    /**
     * 编写一个方法，完成对压缩文件的解压
     *
     * @param zipFile 准备解压的文件
     * @param dstFile 将文件解压到哪个路径
     */
    public static void unZipFile(String zipFile, String dstFile) {
        //定义文件输入流
        InputStream is = null;
        //定义一个对象输入流
        ObjectInputStream ois = null;
        //定义文件的输出流
        OutputStream os = null;

        try {
            //创建文件输入流
            is = new FileInputStream(zipFile);
            //创建一个和is关联的对象输入流
            ois = new ObjectInputStream(is);
            //读取byte数组 huffmanBytes
            byte[] huffmanBytes = (byte[]) ois.readObject();
            // 读取赫夫曼编码表
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();
            //解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            //将bytes数组写入到目标文件
            os = new FileOutputStream(dstFile);
            //写数据到dstFile文件
            os.write(bytes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

    }


    /**
     * 编写一个方法，将一个文件进行压缩
     *
     * @param srcFile 希望压缩的文件
     * @param dstFile 压缩后存放的文件
     */
    public static void zipFile(String srcFile, String dstFile) {

        //创建输出流
        OutputStream os = null;
        ObjectOutputStream oos = null;
        //创建文件的输入流
        FileInputStream is = null;

        try {
            //创建文件的输入流
            is = new FileInputStream(srcFile);
            //创建一个和源文件大小一样的byte[]
            byte[] b = new byte[is.available()];
            //读取文件
            is.read(b);
            //直接对源文件压缩
            byte[] huffmanBytes = huffmanZip(b);
            //创建文件的输出流，存放压缩文件
            os = new FileOutputStream(dstFile);
            //创建一个和文件输出流关联的ObjectOutputStream
            oos = new ObjectOutputStream(os);
            //把赫夫曼编码后的字节数组压入压缩文件
            oos.writeObject(huffmanBytes);
            //这里我们以对象流的方式写入赫夫曼编码，是为了以后我们恢复源文件时使用
            //注意一定要把赫夫曼编码写入压缩文件
            oos.writeObject(huffmanCodes);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                is.close();
                oos.close();
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 完成数据的解压
     * 思路
     * 1. 将 huffmanCodeBytes [-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
     * 重写先转成 赫夫曼编码对应的二进制的字符串 "1010100010111..."
     * 2. 赫夫曼编码对应的二进制的字符串 "1010100010111..." =》 对照 赫夫曼编码 =》 "i like like like
     * java do you like a java"
     * 编写一个方法，完成对压缩数据的解码
     *
     * @param huffmanCodes 赫夫曼编码表 map
     * @param huffmanBytes 赫夫曼编码得到的字节数组
     * @return 就是原来的字符串对应的数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        //1. 先得到 huffmanBytes 对应的 二进制的字符串 ， 形式 1010100010111...
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            //判断是不是最后一个字节
            boolean flag = (huffmanBytes.length - 1 == i);
            stringBuilder.append(byteToBitString(!flag, b));
        }

        //把字符串安装指定的赫夫曼编码进行解码
        //把赫夫曼编码表进行调换，因为反向查询 a->100 100->a
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }

        //创建要存放byte的集合
        List<Byte> list = new ArrayList<>();
        //i 可以理解成就是索引,扫描 stringBuilder
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;//小的计数器
            boolean flag = true;
            Byte b = null;
            while (flag) {
                //1010100010111...
                //递增的取出 key 1
                String key = stringBuilder.substring(i, i + count);//i不动，让count移动，指定匹配到一个字符
                b = map.get(key);
                if (b == null) {
                    //说明没有匹配到
                    count++;
                } else {
                    //匹配到
                    flag = false;
                }
            }
            list.add(b);
            i += count;//i 直接移动到 count
        }

        //当 for 循环结束后，我们 list 中就存放了所有的字符
        // 把 list 中的数据放入到 byte[] 并返回
        byte b[] = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }


    /**
     * 将一个byte转换成一个二进制字符串
     *
     * @param flag 标志是否需要补高位如果是 true ，表示需要补高位，如果是 false 表示不补, 如果是最后一 个字节，无需补高位
     * @param b    传入的byte
     * @return 是该b对应的二进制字符串（注意是按补码返回）
     */
    private static String byteToBitString(boolean flag, byte b) {
        //使用变量保存b
        int temp = b;//将b转成int
        //如果正数还存在补高位
        if (flag) {
            temp = temp | 256;//按位或 256 1 0000 0000 0000 0001 => 1 0000 0001
        }

        String str = Integer.toBinaryString(temp); //返回的是temp对应的二进制补码
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }

    }


    /**
     * 使用一个方法，将前面的方法封装起来，便于我们的调用.
     *
     * @param bytes 原始的字符串对应的字节数组
     * @return 是经过 赫夫曼编码处理后的字节数组(压缩后的数组)
     */
    private static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        //根据nodes创建Huffman树
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        //对应的赫夫曼编码(根据 赫夫曼树)
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        //根据生成的赫夫曼编码，压缩得到压缩后的赫夫曼编码字节数组
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
        return huffmanCodeBytes;
    }

    /**
     * 编写一个方法，将字符串对应的 byte[] 数组，通过生成的赫夫曼编码表，返回一个赫夫曼编码 压缩后的 byte[]
     *
     * @param bytes        这时原始的字符串对应的 byte[]
     * @param huffmanCodes 生成的赫夫曼编码 map
     *                     return 返回赫夫曼编码处理后的 byte[]
     *                     <p>
     *                     举例: String content = "i like like like java do you like a java";
     *                     =》 byte[] contentBytes = content.getBytes();
     *                     返回的是 字符串 "1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000 101111111100110001001010011011100"
     *                     => 对应的 byte[] huffmanCodeBytes ，即 8 位对应一个 byte,放入到 huffmanCodeBytes
     *                     huffmanCodeBytes[0] = 10101000(补码) => byte [推导 10101000=> 10101000 - 1 => 10100111(反码)=> 11011000= -88 ]
     *                     huffmanCodeBytes[1] = -88
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        ///1.利用 huffmanCodes 将 bytes 转成 赫夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //遍历bytes
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
//        System.out.println("测试 stringBuilder~~~=" + stringBuilder.toString());

        //将 "1010100010111111110..." 转成 byte[]
        //统计返回 byte[] huffmanCodeBytes 长度
        // 一句话 int len = (stringBuilder.length() + 7) / 8;

        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }

        //创建好存储压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[len];

        int index = 0;//记录是第几个byte
        for (int i = 0; i < stringBuilder.length(); i += 8) { //因为是每 8 位对应一个 byte,所以步长 +8
            String strByte;
            if (i + 8 > stringBuilder.length()) {
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            //将strByte转变成一个byte，放入到huffmanCodeBytes
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeBytes;
    }

    /**
     * 功能:将传入的 node 结点的所有叶子结点的赫夫曼编码得到，并放入到 huffmanCodes 集合
     *
     * @param node          传入的结点
     * @param code          路径：左子结点是0 右子结点是1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        //将code加入到stringBuilder2
        stringBuilder2.append(code);
        if (node != null) {//如果node 为null则不去处理
            if (node.data == null) { // 非叶子结点
                //递归处理，向左递归
                getCodes(node.getLeft(), "0", stringBuilder2);
                //向右递归
                getCodes(node.getRight(), "1", stringBuilder2);
            } else {
                //说明是一个叶子结点，就表示找到某个叶子结点的最后
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }

    }

    /**
     * 生成Huffman树对应的Huffman编码
     * 思路：
     * 1. 将赫夫曼编码表存放在 Map<Byte,String> 形式
     * 生成的赫夫曼编码表{32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101,
     * 121=11010, 106=0010, 107=1111, 108=000, 111=0011}
     * 2. 在生成赫夫曼编码表示，需要去拼接路径, 定义一个 StringBuilder 存储某个叶子结点的路径
     */

    static Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();
    static StringBuilder stringBuilder = new StringBuilder();

    //为了调用方便，我们重载 getCodes
    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        //处理root的左子树
        getCodes(root.getLeft(), "0", stringBuilder);
        //处理root的右子树
        getCodes(root.getRight(), "1", stringBuilder);
        return huffmanCodes;
    }

    /**
     * 前序遍历方法
     */
    private static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("哈夫曼树为空！！");
        }
    }

    /**
     * @param bytes 接收字节数组
     * @return 返回一个组装后的list集合
     */
    private static List<Node> getNodes(byte[] bytes) {
        //创建一个ArrayList
        ArrayList<Node> nodes = new ArrayList<>();
        //遍历bytes，统计每一个byte出现的次数
        Map<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {
                counts.put(b, 1);
            } else {
                counts.put(b, ++count);
            }
        }

        //把每一个键值对转换成一个Node对象，并加入到nodes集合
        for (Map.Entry<Byte, Integer> map : counts.entrySet()) {
            nodes.add(new Node(map.getKey(), map.getValue()));
        }
        return nodes;
    }

    //可以通过list生成对应的Huffman树
    private static Node createHuffmanTree(List<Node> nodes) {
        if (nodes != null) {
            while (nodes.size() > 1) {
                //排序，从小到大
                Collections.sort(nodes);
                //取出第一棵最小的二叉树
                Node leftNode = nodes.get(0);
                //取出第二棵最小的二叉树
                Node rightNode = nodes.get(1);
                //创建一棵新的二叉树，它的根结点没有data，只有权值
                Node parent = new Node(null, leftNode.getWeight() + rightNode.getWeight());
                parent.setLeft(leftNode);
                parent.setRight(rightNode);
                //将已经处理的两棵二叉树从nodes列表中移除
                nodes.remove(leftNode);
                nodes.remove(rightNode);
                //将新的二叉树插入到nodes
                nodes.add(parent);
            }
            //nodes的最后结点，就是赫夫曼树的根结点
            return nodes.get(0);
        }

        return null;
    }


    /**
     * 创建一个Node，用来存放权值和字符
     */
    static class Node implements Comparable<Node> {

        Byte data = null;//存放数组（字符本身），比如 'a' =》 97
        int weight; // 权值，表示字符出现的次数
        Node left;
        Node right;

        public Node(Byte data, int weight) {
            this.data = data;
            this.weight = weight;
        }

        public void preOrder() {
            System.out.println(this);
            if (this.left != null) {
                this.left.preOrder();
            }
            if (this.right != null) {
                this.right.preOrder();
            }
        }


        public Byte getData() {
            return data;
        }

        public void setData(Byte data) {
            this.data = data;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        @Override
        public int compareTo(Node o) {
            //从小到大排序
            return this.weight - o.weight;
        }

        @Override
        public String toString() {
            return "Node [data = " + data + " weight=" + weight + "]";
        }
    }

}
