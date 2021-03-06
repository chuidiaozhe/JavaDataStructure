package tree;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 *
 *  平衡二叉树（AVL树）
 *   平衡二叉树也叫二叉搜索树（Self-balancingbinarysearchtree）又被称为AVL树，可以保证查询效率较高
 *
 *   特点： 他是一棵空树或它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是一棵平衡二叉树。平衡
 *         二叉树的常用实现方法有红黑树、AVL、替罪羊树、Treap、伸展树等。
 *
 * */
public class AVLTreeDemo {
    public static void main(String[] args){
//        int[] arr = {4,3,6,5,7,8};
//        int[] arr = {10,12,8,9,7,6};
        int[] arr = { 10, 11, 7, 6, 8, 9};
        AVLTree avlTree = new AVLTree();
        for (int i : arr){
            avlTree.add(new Node(i));
        }

        System.out.println("树的高度 ： " + avlTree.root.height());
        System.out.println("树的左子树高度："  + avlTree.root.leftHeight());
        System.out.println("树的右子树高度："  + avlTree.root.rightHeight());

    }


    /**
     * 添加结点的方法
     */
    static class AVLTree {
        private Node root;

        public AVLTree () {
        }
        public void add(Node node) {
            if (root == null) {
                root = node; //如果root为空则直接让root指向node
            } else {
                root.add(node);
            }
        }

        /**
         * 中序遍历
         */
        public void infixOrder() {
            if (root == null) {
                System.out.println("二叉排序树为空，不能遍历");
            } else {
                root.infixOrder();
            }
        }


        //查找要删除的结点
        public Node search(int value) {
            if (root == null) {
                return null;
            } else {
                return root.search(value);
            }
        }

        //查找父节点
        public Node searchParent(int value) {
            if (root == null) {
                return null;
            } else {
                return root.searchParent(value);
            }
        }

        //编写方法:
//1. 返回的 以 node 为根结点的二叉排序树的最小结点的值 //2. 删除 node 为根结点的二叉排序树的最小结点

        /**
         * @param node 传入的结点(当做二叉排序树的根结点)
         * @return 返回的 以 node 为根结点的二叉排序树的最小结点的值
         */
        public int delRightTreeMin(Node node) {
            Node target = node;
            //循环的查找左子节点，就会找到最小值
            while (target.left != null) {
                target = target.left;
            }
            //这时 target 就指向了最小结点
            // 删除最小结点
            delNode(target.value);
            return target.value;
        }

        /**
         * 删除节点
         */
        public void delNode(int value) {
            if (root != null) {
                //1.需要先找到要删除的结点targetNode
                Node targetNode = root.search(value);
                //如果没有找到要删除的结点
                if (targetNode == null) {
                    return;
                }
                //如果我们发现当前这棵二叉排序树只有一个节点
                if (root.getLeft() == null && root.getRight() == null) {
                    root = null;
                    return;
                }
                //去找到targetNode的父节点
                Node parent = root.searchParent(value);
                //如果要删除的是节点是叶子节点
                if (targetNode.getLeft() == null && targetNode.getRight() == null) {
                    //判断targetNode是父节点的左子节点还是右子结点
                    if (parent.getLeft() != null && parent.getLeft().value == value) {
                        //左子节点
                        parent.setLeft(null);
                    } else if (parent.getRight() != null && parent.getRight().value == value) {
                        //是右子结点
                        parent.setRight(null);
                    }

                } else if (targetNode.getLeft() != null && targetNode.getRight() != null) {
                    //删除有两棵子树的节点
                    int minVal =  delRightTreeMin(targetNode.getRight());
                    targetNode.setValue(minVal);
                } else {
                    //删除只有一棵子树的节点
                    if (targetNode.getLeft() != null) {
                        //如果删除的节点是左子节点
                        if (parent != null) {
                            //如果targetNode是parent的左子节点
                            if (parent.getLeft().value == value) {
                                parent.left = targetNode.left;
                            } else {
                                //targetNode是parent的右子节点
                                parent.right = targetNode.getLeft();
                            }
                        } else {
                            root = targetNode.left;
                        }
                    } else {
                        //如果删除的节点是右子节点
                        if (parent != null) {
                            if (parent.left.value == value) {
                                parent.setLeft(targetNode.getRight());
                            } else {
                                parent.setRight(targetNode.getRight());
                            }
                        } else {
                            root = targetNode.right;
                        }
                    }
                }
            }

        }
    }



    static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public  Node getLeft() {
            return left;
        }

        public void setLeft( Node left) {
            this.left = left;
        }

        public  Node getRight() {
            return right;
        }

        public void setRight( Node right) {
            this.right = right;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }

        //返回左子树的高度
        public int leftHeight(){
            if(left == null){
                return  0;
            }
            return left.height();
        }

        //返回右子树的高度
        public int rightHeight(){
            if(right == null){
                return 0;
            }
            return right.height();
        }


        //返回该结点为根节点的数的高度
        public int height(){
            return Math.max(left == null ? 0 : left.height() ,right == null ? 0 : right.height()) + 1;
        }


        //左旋转
        public void leftRotate(){
            //创建新的结点，以当前根结点的值
            Node newNode = new Node(value);
            //把新节点的左子树设置成当前结点的左子树
            newNode.setLeft(getLeft());
            //把新节点的右子树设置成当前结点右子树的左子树
            newNode.setRight(getRight().left);
            //把当前结点的值替换成右子结点的值
            value = getRight().getValue();
            //把当前结点的右子树设置成当前结点右子树的右子树
            setRight(getRight().getRight());
            //把当前结点的左子树（左子结点）设置成新的结点
            setLeft(newNode);
        }

        //右旋转
        public void rightRotate(){
           //创建新的结点，以当前根结点的值
           Node newNode = new Node(value);
           //把新节点的右子树设置成当前结点的右子树
            newNode.setRight(getRight());
            //把新节点的左子树设置成当前结点左子树的右子树
            newNode.setLeft(getLeft().getRight());
            //把当前结点的值替换成左子节点的值
            value = getLeft().getValue();
            //把当前结点的左子树设置成当前结点左子树的左子树
            setLeft(getLeft().getLeft());
            //把当前结点的右子树设置成新的结点
            setRight(newNode);
        }



        /**
         * 查找要删除的节点
         *
         * @param value 希望删除的节点的值
         * @return 如果找到返回该结点，否则返回null
         */
        public  Node search(int value) {
            if (this.value == value) {
                //找到就是该节点
                return this;
            } else if (this.value > value) {
                //查找左边的
                if (this.left != null) {
                    return this.left.search(value);
                } else {
                    return null;
                }
            } else {
                if (this.right == null) {
                    return null;
                }
                return this.right.search(value);
            }
        }


        /**
         * 查找要删除节点的父节点
         *
         * @param value 要找到节点的值
         * @return 返回的是要删除的结点的父节点，如果没有则返回null
         */
        public  Node searchParent(int value) {
            //如果当前节点就是要删除的节点的父节点，就返回
            if ((this.left != null && this.left.value == value) ||
                    (this.right != null && this.right.value == value)) {
                return this;
            } else {
                //如果查找的值小于当前节点的值，并且当前节点的左子节点不为空
                if (value < this.value && this.left != null) {
                    //向左子树递归遍历
                    return this.left.searchParent(value);
                } else if (value >= this.value && this.right != null) {
                    //向右子树递归遍历
                    return this.right.searchParent(value);
                } else {
                    return null; //没有找到父节点
                }
            }
        }

        /**
         * 添加节点的方法，递归的形式添加节点，注意需要满足二叉树的需求
         */
        public void add( Node node) {
            if (node == null) {
                return;
            }

            if (node.value < this.value) {
                //如果当前节点左子树节点为null
                if (this.left == null) {
                    this.left = node;
                } else {
                    //递归的向左子树添加
                    this.left.add(node);
                }
            } else {
                //添加的结点的值大于等于当前节点的值
                if (this.right == null) {
                    this.right = node;
                } else {
                    //递归的向左子树添加
                    this.right.add(node);
                }
            }

            //当添加完一个结点后，如果（左子树的高度 - 右子树的高度）> 1，进行左旋转
            if((rightHeight() - leftHeight()) > 1){
                //如果它的右子树的左子树的高度大于它的右子树的右子树的高度
                 if(getRight() != null && getRight().leftHeight() > getRight().rightHeight()){
                    //先对右子树结点进行右旋转
                     getRight().rightRotate();
                     //然后再对当前结点进行左旋转
                     leftRotate();
                 }else{
                     //直接进行左旋转即可
                     leftRotate();
                 }
                 return;//避免重复旋转
            }

            //当添加完一个结点后，如果（左子树的高度 - 右子树的高度）> 1，进行右旋转
            if((leftHeight() - rightHeight()) > 1){
                //如果它的左子树的右子树高度大于它的左子树的高度
                if(getLeft() != null && getLeft().rightHeight() > getLeft().leftHeight()){
                    //先对当前结点的左节点进行左旋转
                    getLeft().leftRotate();
                    //再对当前结点进行右旋转
                    rightRotate();
                }else{
                    //直接进行右旋转即可
                    rightRotate();
                }
            }
        }

        /**
         * 中序遍历
         */
        public void infixOrder() {
            if (this.left != null) {
                this.left.infixOrder();
            }
            System.out.println(this);
            if (this.right != null) {
                this.right.infixOrder();
            }
        }
    }
}
