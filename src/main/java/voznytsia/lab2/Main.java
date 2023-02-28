package voznytsia.lab2;
/*
    IO-04 Voznytsia Dmytro
    variant - 27
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Main {
    private static final String INPUT_FILENAME = "src\\main\\java\\voznytsia\\lab2\\input.txt";
    private static final String OUTPUT_FILENAME = "src\\main\\java\\voznytsia\\lab2\\output.txt";

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        Operations operations = new Operations();
        try {
            String inputFilePath = new File(INPUT_FILENAME).getAbsolutePath();
            Scanner scanner = new Scanner(new File(inputFilePath));
            int sizeMM = scanner.nextInt();
            int sizeME = scanner.nextInt();
            int sizeMX = scanner.nextInt();
            int sizeB = scanner.nextInt();
            int sizeD = scanner.nextInt();
            double q = scanner.nextDouble();
            scanner.close();

            double[][] MM = operations.generateRandomMatrix(sizeMM, sizeMM);
            double[][] ME = operations.generateRandomMatrix(sizeME, sizeME);
            double[][] MX = operations.generateRandomMatrix(sizeMX, sizeMX);
            double[] B = operations.generateRandomArray(sizeB);
            double[] D = operations.generateRandomArray(sizeD);

            String outputFilePath = new File(OUTPUT_FILENAME).getAbsolutePath();
            PrintWriter writer = new PrintWriter(outputFilePath);

            double[][] result1 = new double[sizeMM][sizeMM];
            double[][] result2 = new double[sizeME][sizeME];
            double[] result3 = new double[sizeD];

            Semaphore semaphore1 = new Semaphore(0);
            Semaphore semaphore2 = new Semaphore(0);
            Semaphore semaphore3 = new Semaphore(0);

            CountDownLatch latch = new CountDownLatch(3);

            Thread t1 = new Thread(() -> {
                synchronized(Main.class) {
                    double[][] r = operations.multiplyMatrix(MM, operations.subtractMatrix(ME, MX));
                    for (int i = 0; i < r.length; i++) {
                        System.arraycopy(r[i], 0, result1[i], 0, r[i].length);
                    }
                    System.out.println("\nResult 1: " + Arrays.deepToString(r));
                    writer.println("\nResult 1: " + Arrays.deepToString(result1));
                }
                semaphore1.release();
                latch.countDown();
            });

            Thread t2 = new Thread(() -> {
                synchronized(Main.class) {
                    double[][] r = operations.multiplyMatrixByScalar(operations.multiplyMatrix(ME, MX), q);
                    for (int i = 0; i < r.length; i++) {
                        System.arraycopy(r[i], 0, result2[i], 0, r[i].length);
                    }
                    System.out.println("\nResult 2: " + Arrays.deepToString(r));
                    writer.println("\nResult 2: " + Arrays.deepToString(result2));
                }
                semaphore2.release();
                latch.countDown();
            });

            Thread t3 = new Thread(() -> {
                synchronized(Main.class) {
                    double[] r = operations.multiplyVectorByScalar(D, operations.findMinValue(B));
                    System.arraycopy(r, 0, result3, 0, r.length);
                    System.out.println("\nResult 3: " + Arrays.toString(r));
                    writer.println("\nResult 3: " + Arrays.toString(result3));
                }
                semaphore3.release();
                latch.countDown();
            });

            t1.start();
            t2.start();
            t3.start();

            try {
                semaphore1.acquire();
                semaphore2.acquire();
                semaphore3.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            double[][] MA = new double[result1.length][result1[0].length];
            for (int i = 0; i < MA.length; i++) {
                for (int j = 0; j < MA[0].length; j++) {
                    MA[i][j] = result1[i][j] + result2[i][j];
                }
            }

            double[] Y = operations.multiplyVectorByMatrix(B, ME);
            for (int i = 0; i < Y.length; i++) {
                Y[i] = Y[i] + result3[i];
            }

            System.out.println("\nFinal Result: \nMA=" + Arrays.deepToString(MA) + "\n\nY=" + Arrays.toString(Y));
            writer.println("\nFinal Result: \nMA=" + Arrays.deepToString(MA) + "\n\nY=" + Arrays.toString(Y));

            long endTime = System.nanoTime();
            long resultTime = (endTime - startTime);
            System.out.println("\nDuration: " + resultTime + " ns");
            writer.println("\nDuration: " + resultTime + " ns");
            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}