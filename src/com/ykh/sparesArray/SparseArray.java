package com.ykh.sparesArray;

public class SparseArray {
    public static void main(String[] args) {
        //创建一个原始的二维数组
        //0：表示没有棋子 1表示黑子 2表示蓝子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][4] = 2;
        System.out.println("原始二维数组");
        for (int[] ints : chessArr1) {
            for (int data : ints) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        //将二维数组转换为稀疏数组
        int sum = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1.length; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }
        //创建一个稀疏数组
        int sparseArray[][] = new int[sum + 1][3];
        //将二维数组转换为稀疏数组
        sparseArray[0][0] = chessArr1.length;//行
        sparseArray[0][1] = chessArr1[0].length;//列
        sparseArray[0][2] = sum;//总共有几个不为0的值
        //遍历二维数组
        int count = 0;//用于记录稀疏数组的第几行
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[0].length; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = chessArr1[i][j];
                }
            }
        }
        //输出稀疏数组
        System.out.println("稀疏数组");
        for (int[] ints : sparseArray) {
            for (int data : ints) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        //将稀疏数组转为二维数组
        //创建一个新的二维数组  行列由稀疏数组的第一列完成
        int chessArr2[][] = new int[sparseArray[0][0]][sparseArray[0][1]];
        //先读取长度
        //遍历稀疏数组
        for (int i = 1; i < sparseArray.length; i++) {
            //稀疏数组的i行的第一列为二维数组的二维数组有值的行标
            //稀疏数组的i行的第二列为二维数组的二维数组有值的列标
            //稀疏数组的i行的第三列为二维数组的二维数组有值的值
            chessArr2[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        //输出转换回来的二维数组
        System.out.println("转换回来的二维数组");
        for (int[] ints : chessArr2) {
            for (int data : ints) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }
}
