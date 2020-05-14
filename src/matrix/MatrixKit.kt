@file:Suppress("UNCHECKED_CAST", "TYPE_PARAMETER_AS_REIFIED_ARRAY_WARNING")

package matrix

/**
 * Created by Maxim Hnatiuk in 14/05/20
 */

import extensions.isPositive
import java.util.*

inline operator fun <reified T> Matrix.Companion.invoke() : Matrix<T> {
    return Matrix.invoke(INITIAL_CAPACITY, INITIAL_CAPACITY)
}

inline operator fun <reified T> Matrix.Companion.invoke(rowCount: Int, colCount: Int) : Matrix<T> {
    val isSizeCorrect = rowCount.isPositive && colCount.isPositive

    if (!isSizeCorrect) {
        throw IllegalArgumentException("Size must be bigger than 0")
    }

    val array = Array(rowCount) { arrayOfNulls<T>(colCount) }

    return Matrix(array)
}

/**
 * Read Matrix
 */
inline fun <reified T> Scanner.readMatrix() : Matrix<T>? where T : Number {
    println("Enter row count:")
    val rowCount = readLine()?.toInt() ?: return null
    println("Enter col count:")
    val colCount = readLine()?.toInt() ?: return null

    val matrix = Matrix<T>(rowCount, colCount)

    println("Enter your Matrix in one line:")
    val numbers = readLine()?.split(" ")?.map { it as T }

    var count = 0
    repeat(rowCount) { i ->
        repeat(colCount) { j ->
            matrix[i][j] = numbers?.get(count)
            count++
        }
    }

    return matrix
}

/**
 * Plus.
 */
inline operator fun <reified T> Matrix<T>.plus(matrix: Matrix<T>) : Matrix<T> {
    return calculateMatrixByOperation(this, matrix, Operation.PLUS)
}

inline operator fun <reified T> Matrix<T>.plus(element: T) : Matrix<T> {
    return calculateByOperationWithElement(element, Operation.PLUS)
}

inline operator fun <reified T> T.plus(matrix: Matrix<T>) = matrix + this

/**
 * Minus
 */
inline operator fun <reified T> Matrix<T>.minus(matrix: Matrix<T>) : Matrix<T> {
    return calculateMatrixByOperation(this, matrix, Operation.MINUS)
}

inline operator fun <reified T> Matrix<T>.minus(element: T) : Matrix<T> {
    return calculateByOperationWithElement(element, Operation.MINUS)
}

inline operator fun <reified T> T.minus(matrix: Matrix<T>) = matrix - this

/**
 * Multiply
 */
inline operator fun <reified T> Matrix<T>.times(matrix: Matrix<T>) : Matrix<T> {
    return calculateMatrixByOperation(this, matrix, Operation.MULTIPLY)
}

inline operator fun <reified T> Matrix<T>.times(element: T) : Matrix<T> {
    return calculateByOperationWithElement(element, Operation.MULTIPLY)
}

inline operator fun <reified T> T.times(matrix: Matrix<T>) = matrix * this

/**
 * Dividing
 */
inline operator fun <reified T> Matrix<T>.div(matrix: Matrix<T>) : Matrix<T> {
    return calculateMatrixByOperation(this, matrix, Operation.PLUS)
}

inline operator fun <reified T> Matrix<T>.div(element: T) : Matrix<T> {
    return calculateByOperationWithElement(element, Operation.PLUS)
}

inline operator fun <reified T> T.div(matrix: Matrix<T>) = matrix / this


inline fun <reified T> Matrix<T>.calculateByOperationWithElement(element: T, operation: Operation) : Matrix<T> {
    val newMatrix = Matrix<T>(rowCount, colCount)

    repeat(rowCount) { i ->
        repeat(colCount) { j ->
            newMatrix[i][j] = calculateByOperation(this[i][j], element, operation)
        }
    }

    return newMatrix
}

inline fun <reified T> calculateMatrixByOperation(first: Matrix<T>, second: Matrix<T>, operation: Operation) : Matrix<T> {
    if (!Matrix.isCorrectOperation(first, second)) {
        throw IllegalArgumentException("Matrix's sizes are not the same")
    }

    val newMatrix = Matrix<T>(first.rowCount, second.colCount)

    repeat(first.rowCount) { i ->
        repeat(first.colCount) { j ->
            newMatrix[i][j] = calculateByOperation(first[i][j], second[i][j], operation)
        }
    }

    return newMatrix
}

fun <T> calculateByOperation(first: T, second: T, operation: Operation) : T {
    return when(first) {
        is Int -> {
            when (operation) {
                Operation.PLUS -> (first + (second as Int)) as T
                Operation.MINUS -> (first - (second as Int)) as T
                Operation.MULTIPLY -> (first * (second as Int)) as T
                Operation.DIVIDE -> (first / (second as Int)) as T
            }
        }
        is Double -> {
            when (operation) {
                Operation.PLUS -> (first + (second as Double)) as T
                Operation.MINUS -> (first - (second as Double)) as T
                Operation.MULTIPLY -> (first * (second as Double)) as T
                Operation.DIVIDE -> (first / (second as Double)) as T
            }
        }
        is Float -> {
            when (operation) {
                Operation.PLUS -> (first + (second as Float)) as T
                Operation.MINUS -> (first - (second as Float)) as T
                Operation.MULTIPLY -> (first * (second as Float)) as T
                Operation.DIVIDE -> (first / (second as Float)) as T
            }
        }
        else -> throw IllegalArgumentException("Type is not a number")
    }
}