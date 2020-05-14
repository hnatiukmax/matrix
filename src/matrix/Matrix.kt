@file:Suppress("UNCHECKED_CAST", "TYPE_PARAMETER_AS_REIFIED_ARRAY_WARNING")

package matrix

/**
 * Created by Maxim Hnatiuk in 14/05/20
 */

class Matrix<T> : Cloneable {

    val rowCount: Int
        get() = mMatrix.size

    val colCount: Int
        get() = mMatrix[0].size

    var mMatrix: Array<Array<T?>>

    companion object {
        const val INITIAL_CAPACITY = 5

        fun isCorrectOperation(firstMatrix: Matrix<*>, secondMatrix: Matrix<*>) : Boolean {
            return firstMatrix.rowCount == secondMatrix.rowCount && firstMatrix.colCount == secondMatrix.colCount
        }
    }

    constructor(matrix: Array<Array<T?>>) {
        mMatrix = matrix
    }

    operator fun get(indexRow: Int) : Array<T?> {
        return mMatrix[indexRow]
    }

    operator fun get(indexRow: Int, indexCol: Int) : T? {
        return mMatrix[indexRow][indexCol]
    }

    operator fun set(indexRow: Int, row: Array<T?>) {
        mMatrix[indexRow] = row
    }

    operator fun set(indexRow: Int, indexCol: Int, element: T?) {
        mMatrix[indexRow][indexCol] = element
    }

    override fun toString(): String {
        val resultString = StringBuilder()

        mMatrix.forEachIndexed { i, row ->
            row.forEach { element ->
                resultString.append(element.toString() + ", ")
            }
            if (i != rowCount - 1) {
                resultString.append("\n")
            }
        }
        resultString.delete(resultString.length - 2, resultString.length)

        return resultString.toString()
    }

    public override fun clone(): Matrix<T> {
        return Matrix(mMatrix.clone())
    }
}