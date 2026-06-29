package mate.academy

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class MatrixTest {

    @Test
    fun constructor_InitializeWithRowsAndCols_AllElementsZero() {
        // given
        val rows = 2
        val cols = 3

        // when
        val matrix = Matrix(rows, cols)

        // then
        for (row in 0 until rows) {
            for (col in 0 until cols) {
                assertEquals(0, matrix[row, col], "Matrix should be initialized with zeros.")
            }
        }
    }

    @Test
    fun setAndGetElement_SetValue_ValueUpdatedAndRetrievedCorrectly() {
        // given
        val matrix = Matrix(2, 2)
        val value = 5

        // when
        matrix[1, 1] = value

        // then
        assertEquals(value, matrix[1, 1], "Set value should be equal to retrieved value.")
    }

    @Test
    fun setAndGetElement_SetBoundaryValues_ValuesUpdatedAndRetrievedCorrectly() {
        // given
        val matrix = Matrix(3, 3)
        val topLeftValue = 1
        val bottomRightValue = 9

        // when
        matrix[0, 0] = topLeftValue
        matrix[2, 2] = bottomRightValue

        // then
        assertEquals(topLeftValue, matrix[0, 0], "Top left value should be updated and retrieved correctly.")
        assertEquals(bottomRightValue, matrix[2, 2], "Bottom right value should be updated and retrieved correctly.")
    }

    @Test
    fun plus_TwoMatrices_CorrectlyAdded() {
        // given
        val matrix1 = Matrix(2, 2).apply {
            this[0, 0] = 1
            this[0, 1] = 2
            this[1, 0] = 3
            this[1, 1] = 4
        }
        val matrix2 = Matrix(2, 2).apply {
            this[0, 0] = 4
            this[0, 1] = 3
            this[1, 0] = 2
            this[1, 1] = 1
        }

        // when
        val result = matrix1 + matrix2

        // then
        assertEquals(5, result[0, 0], "Result of addition is incorrect at [0,0].")
        assertEquals(5, result[0, 1], "Result of addition is incorrect at [0,1].")
        assertEquals(5, result[1, 0], "Result of addition is incorrect at [1,0].")
        assertEquals(5, result[1, 1], "Result of addition is incorrect at [1,1].")
    }

    @Test
    fun minus_TwoMatrices_CorrectlySubtracted() {
        // given
        val matrix1 = Matrix(2, 2).apply {
            this[0, 0] = 5
            this[0, 1] = 5
            this[1, 0] = 5
            this[1, 1] = 5
        }
        val matrix2 = Matrix(2, 2).apply {
            this[0, 0] = 1
            this[0, 1] = 2
            this[1, 0] = 3
            this[1, 1] = 4
        }

        // when
        val result = matrix1 - matrix2

        // then
        assertEquals(4, result[0, 0], "Result of subtraction is incorrect at [0,0].")
        assertEquals(3, result[0, 1], "Result of subtraction is incorrect at [0,1].")
        assertEquals(2, result[1, 0], "Result of subtraction is incorrect at [1,0].")
        assertEquals(1, result[1, 1], "Result of subtraction is incorrect at [1,1].")
    }

    @Test
    fun zeroSizedMatrix_Initialize_ZeroSizedMatrixHandledGracefully() {
        // given
        val rows = 0
        val cols = 0

        // when
        val matrix = Matrix(rows, cols)

        // then
        // No exception thrown, matrix handled gracefully
        assertThrows(IndexOutOfBoundsException::class.java) { matrix[0, 0] }
    }

    @Test
    fun dataIntegrity_PerformOperations_OriginalMatricesUnchanged() {
        // given
        val originalMatrix1 = Matrix(2, 2).apply {
            this[0, 0] = 1
            this[0, 1] = 2
            this[1, 0] = 3
            this[1, 1] = 4
        }
        val originalMatrix2 = Matrix(2, 2).apply {
            this[0, 0] = 4
            this[0, 1] = 3
            this[1, 0] = 2
            this[1, 1] = 1
        }
        val expectedMatrix1 = Matrix(2, 2).apply {
            this[0, 0] = 1
            this[0, 1] = 2
            this[1, 0] = 3
            this[1, 1] = 4
        }
        val expectedMatrix2 = Matrix(2, 2).apply {
            this[0, 0] = 4
            this[0, 1] = 3
            this[1, 0] = 2
            this[1, 1] = 1
        }

        // when
        val ignored = originalMatrix1 + originalMatrix2

        // then
        for (row in 0 until 2) {
            for (col in 0 until 2) {
                assertEquals(
                    expectedMatrix1[row, col],
                    originalMatrix1[row, col],
                    "Original matrix1 should remain unchanged."
                )
                assertEquals(
                    expectedMatrix2[row, col],
                    originalMatrix2[row, col],
                    "Original matrix2 should remain unchanged."
                )
            }
        }
    }
}
