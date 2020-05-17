@file:Suppress("UNCHECKED_CAST", "TYPE_PARAMETER_AS_REIFIED_ARRAY_WARNING")

package matrix

import extensions.plus
import java.lang.IllegalArgumentException
import java.util.*

/**
 * Created by Maxim Hnatiuk in 14/05/20
 */

class Matrix<T> : Cloneable {

    var rowCount: Int = 0
        get() = mMatrix.size
        set(value) {
            changeRowCount(value)
            field = value
        }

    var colCount: Int = 0
        get() = mMatrix[0].size
        set(value) {
            changeColCount(value)
            field = value
        }

    var mMatrix: Array<Array<T?>>
        set(value) {
            field = value
        }

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
        if (row.size != colCount) {
            throw IllegalArgumentException("Parameter's row size is not equal to colCount")
        }

        mMatrix[indexRow] = row
    }

    operator fun set(indexRow: Int, indexCol: Int, element: T?) {
        mMatrix[indexRow][indexCol] = element
    }

    override fun toString(): String {
        val resultString = StringBuilder()

        mMatrix.forEach {
            resultString plus it?.let { array ->
                it.joinToString(prefix = "[", postfix = "]") + "\n"
            }
        }

        return resultString.toString()
    }

    public override fun clone(): Matrix<T> {
        return Matrix(mMatrix.clone())
    }

    override fun equals(other: Any?) =
         when {
            this === other -> true
            this.javaClass != other?.javaClass -> false
            else -> with(this) {
                var isEqual = true
                val otherMatrix = other as Matrix<T>

                mMatrix.forEachIndexed { index, it ->
                    isEqual = it.contentEquals(otherMatrix[index])
                }

                isEqual
            }
        }

    override fun hashCode(): Int {
        return mMatrix.contentDeepHashCode()
    }

    private fun changeRowCount(newRowCount: Int) {
        val newMMatrix = arrayOfNulls<Array<T>>(newRowCount)
        repeat(rowCount) {
            newMMatrix[it] = mMatrix[it] as Array<T>
        }
        mMatrix = newMMatrix as Array<Array<T?>>
    }

    private fun changeColCount(newColCount: Int) {
        mMatrix.forEachIndexed { index, row ->
            mMatrix[index] = row.copyOf(newColCount)
        }
    }
}