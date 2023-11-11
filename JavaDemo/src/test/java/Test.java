interface Computeable {
    int sum(int n); // 计算从1+2+...+n之和

    double sum(int arr[][]); // 计算二维数组arr中所有元素之和
}

class MyCompute implements Computeable {
    public int sum(int n) {
        int result = 0;
        for (int i = 1; i <= n; i++) {
            result += i;
        }
        return result;
    }

    public double sum(int arr[][]) {
        double result = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                result += arr[i][j];
            }
        }
        return result;
    }
}

public class Test {
    public static void main(String[] args) {
        MyCompute myCompute = new MyCompute();

        int sum1 = myCompute.sum(10);
        System.out.println("Sum of numbers from 1 to 10: " + sum1);

        int arr[][] = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double sum2 = myCompute.sum(arr);
        System.out.println("Sum of elements in the 2D array: " + sum2);
    }
}

