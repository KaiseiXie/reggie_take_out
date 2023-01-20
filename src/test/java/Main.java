public class Main {
    public static void main(String[] args) {
        //生成5个随机数，放入数组并求和
        int sum = 0;
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 1000);
            sum = arr[i] + sum;
            System.out.print(arr[i] + "  ");
        }
        int avg = sum / arr.length;

        //输出平均数
        System.out.println("平均数为" + avg);

        //循环判断
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < avg) {
                System.out.print(arr[i] + "  ");
            }
        }
    }
}