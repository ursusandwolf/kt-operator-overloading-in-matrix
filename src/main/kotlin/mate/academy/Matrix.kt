package mate.academy

class Matrix(private val rows: Int, private val cols: Int) {
    private val data = Array(rows) { IntArray(cols) }

    operator fun get(row: Int, col: Int): Int = data[row][col]
    operator fun set(row: Int, col: Int, value: Int) {
        data[row][col] = value
    }
    operator fun plus(other: Matrix): Matrix = elementWise(other) { a, b -> a + b }
    operator fun minus(other: Matrix): Matrix = elementWise(other) { a, b -> a - b }

    private inline fun elementWise(other: Matrix, op: (Int, Int) -> Int): Matrix {
        require(rows == other.rows && cols == other.cols) { "Matrix dimensions must match" }
        val result = Matrix(rows, cols)
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                result[i, j] = op(this[i, j], other[i, j])
            }
        }
        return result
    }

    override fun toString(): String {
        return data.joinToString(separator = "\n") { row -> row.joinToString(" ") }
    }
}
