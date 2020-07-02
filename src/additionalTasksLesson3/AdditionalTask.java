package additionalTasksLesson3;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class AdditionalTask {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Введите значения в таком порядке:\nBысота ширина количество");
        int height = scanner.nextInt();
        int width = scanner.nextInt();
        int num = scanner.nextInt();
        int[][] matrix = drawSpiral(height, width);
        outMatrix(matrix, height, width);

        System.out.println();
        rectangles(num);
    }

    public static int[][] drawSpiral(int width, int height) {
        int[][] matrix = new int[height][width];
        int counter = 1;
        int shift1 = 0;
        int shift2 = width - 1;

        while (counter <= width * height){
            for (int i = 0; i < width; i++) {
                if(matrix[shift1][i] == 0) {
                    matrix[shift1][i] = counter;
                    counter ++;
                }
            }

            for(int i = 0; i < height; i ++){
                if(matrix[i][shift2] == 0){
                    matrix[i][shift2] = counter;
                    counter ++;
                }
            }

            for (int i = width - 1; i >= 0; i--) {
                try {
                    if (matrix[shift2 + 1][i] == 0) {
                        matrix[shift2 + 1][i] = counter;
                        counter++;
                    }
                }catch (Exception e){
                    if (matrix[shift2][i] == 0) {
                        matrix[shift2][i] = counter;
                        counter++;
                    }
                }
            }

            for(int i = height - 1; i >= 0; i --){
                    if (matrix[i][shift1] == 0) {
                        matrix[i][shift1] = counter;
                        counter++;
                }
            }

            shift1++;
            shift2--;
        }
        return matrix;
    }

    public static void outMatrix (int[][] matrix, int width, int height) {
        for(int i = 0; i < width; i ++) {
            System.out.println();
            for (int j = 0; j < height; j++) {
                    if (matrix[j][i] < 10)
                        System.out.printf("0%d ", matrix[j][i]);
                    else
                        System.out.printf("%2d ", matrix[j][i]);
            }
        }
    }

    public static void rectangles (int num){
        ArrayList<Integer> values = new ArrayList<>();
        values.add(1);
        values.add(1);
        BigInteger sum = new BigInteger("8");
        for (int i = 2; true; i++) {
            if(i == values.get(values.size() - 1) + values.get(values.size() - 2)) {
                values.add(i);
                sum = sum.add(new BigInteger(String.valueOf(i * 4)));
                System.out.println(i * 4);
            }

            if(values.size() == num + 1)
                break;
        }
        System.out.println(sum);
    }
}
