package voznytsia.lab2;

import java.util.Random;

public class Operations {
    private final Random random = new Random();

    public double[][] generateRandomMatrix(int rows, int cols) {
        double[][] matrix = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = random.nextDouble();
            }
        }
        return matrix;
    }

    public double[] generateRandomArray(int size) {
        double[] array = new double[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextDouble();
        }
        return array;
    }

    public double[][] multiplyMatrix(double[][] a, double[][] b) {
        int rowsA = a.length;
        int colsA = a[0].length;
        int rowsB = b.length;
        int colsB = b[0].length;

        if (colsA != rowsB) {
            throw new IllegalArgumentException("Invalid dimensions for matrix multiplication");
        }

        double[][] result = new double[rowsA][colsB];
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                double sum = 0.0;
                double c = 0.0;
                for (int k = 0; k < colsA; k++) {
                    double y = a[i][k] * b[k][j] - c;
                    double t = sum + y;
                    c = (t - sum) - y;
                    sum = t;
                }
                result[i][j] = sum;
            }
        }
        return result;
    }

    public double[][] multiplyMatrixByScalar(double[][] matrix, double scalar) {
        int numRows = matrix.length;
        int numCols = matrix[0].length;
        double[][] result = new double[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                result[i][j] = matrix[i][j] * scalar;
            }
        }
        return result;
    }

    public double[][] subtractMatrix(double[][] a, double[][] b) {
        int rowsA = a.length;
        int colsA = a[0].length;
        int rowsB = b.length;
        int colsB = b[0].length;

        if (rowsA != rowsB || colsA != colsB) {
            throw new IllegalArgumentException("Invalid dimensions for matrix subtraction");
        }

        double[][] result = new double[rowsA][colsA];

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsA; j++) {
                result[i][j] = a[i][j] - b[i][j];
            }
        }
        return result;
    }

    public double[] multiplyVectorByScalar(double[] vector, double scalar) {
        double[] result = new double[vector.length];
        for (int i = 0; i < vector.length; i++) {
            result[i] = vector[i] * scalar;
        }
        return result;
    }

    public double[] multiplyVectorByMatrix(double[] vector, double[][] matrix) {
        if (vector.length != matrix.length) {
            throw new IllegalArgumentException("Vector and matrix dimensions are incompatible for multiplication");
        }

        double[] result = new double[matrix[0].length];
        for (int j = 0; j < matrix[0].length; j++) {
            double sum = 0.0;
            for (int i = 0; i < vector.length; i++) {
                sum += vector[i] * matrix[i][j];
            }
            result[j] = sum;
        }

        return result;
    }

    public double findMinValue(double[] arr) {
        double minValue = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < minValue) {
                minValue = arr[i];
            }
        }
        return minValue;
    }
}
