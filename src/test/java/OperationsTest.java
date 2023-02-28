import org.junit.jupiter.api.Test;
import voznytsia.lab2.Operations;

import static org.junit.jupiter.api.Assertions.*;

public class OperationsTest {
    private final Operations operations = new Operations();

    @Test
    public void testMultiplyMatrix() {
        double[][] a = {{1, 2}, {3, 4}};
        double[][] b = {{5, 6}, {7, 8}};
        double[][] expected = {{19, 22}, {43, 50}};
        double[][] result = operations.multiplyMatrix(a, b);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testInvalidDimensionsForMultiplyMatrix() {
        double[][] a = {{1, 2}, {3, 4}};
        double[][] b = {{5, 6}};
        assertThrows(IllegalArgumentException.class, () -> operations.multiplyMatrix(a, b));
    }

    @Test
    public void testFindMinValue() {
        double[] arr = {1, 2, 3, 4, 5};
        double expected = 1;
        double result = operations.findMinValue(arr);
        assertEquals(expected, result, 0.0);
    }

    @Test
    public void testSubtractMatrix() {
        double[][] a = {{1, 2}, {3, 4}};
        double[][] b = {{5, 6}, {7, 8}};
        double[][] expected = {{-4, -4}, {-4, -4}};
        double[][] result = operations.subtractMatrix(a, b);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testInvalidDimensionsForSubtractMatrix() {
        double[][] a = {{1, 2}, {3, 4}};
        double[][] b = {{5, 6}};
        assertThrows(IllegalArgumentException.class, () -> operations.subtractMatrix(a, b));
    }

    @Test
    public void testMultiplyMatrixByScalar() {
        double[][] a = {{1, 2}, {3, 4}};
        double scalar = 2.0;
        double[][] expected = {{2, 4}, {6, 8}};
        double[][] result = operations.multiplyMatrixByScalar(a, scalar);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testMultiplyMatrixByScalarWithZeroScalar() {
        double[][] a = {{1, 2}, {3, 4}};
        double scalar = 0.0;
        double[][] expected = {{0, 0}, {0, 0}};
        double[][] result = operations.multiplyMatrixByScalar(a, scalar);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testMultiplyVectorByScalar() {
        double[] vector = {1, 2, 3};
        double scalar = 2.0;
        double[] expected = {2, 4, 6};
        double[] result = operations.multiplyVectorByScalar(vector, scalar);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testMultiplyVectorByMatrix() {
        double[] vector = {1, 2};
        double[][] matrix = {{3, 4}, {5, 6}};
        double[] expected = {13, 16};
        double[] result = operations.multiplyVectorByMatrix(vector, matrix);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testMultiplyVectorByMatrixWithIncompatibleDimensions() {
        double[] vector = {1, 2, 3};
        double[][] matrix = {{1, 2}, {3, 4}};
        assertThrows(IllegalArgumentException.class, () -> operations.multiplyVectorByMatrix(vector, matrix));
    }

    @Test
    public void testGenerateRandomMatrix() {
        int rows = 3;
        int cols = 4;
        double[][] result = operations.generateRandomMatrix(rows, cols);
        assertEquals(rows, result.length);
        assertEquals(cols, result[0].length);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                assertTrue(result[i][j] >= 0 && result[i][j] < 1);
            }
        }
    }

    @Test
    public void testGenerateRandomArray() {
        int size = 5;
        double[] result = operations.generateRandomArray(size);
        assertEquals(size, result.length);
        for (int i = 0; i < size; i++) {
            assertTrue(result[i] >= 0 && result[i] < 1);
        }
    }

}
