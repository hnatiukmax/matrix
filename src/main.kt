import matrix.*

fun main() {
    val matrixB = Matrix<Int>(2,2)
    matrixB[0][0] = 4
    matrixB[0][1] = 4
    matrixB[1][0] = 4
    matrixB[1][1] = 4

    val matrixA = Matrix<Int>(2,2)
    matrixA[0][0] = 2
    matrixA[0][1] = 2
    matrixA[1][0] = 2
    matrixA[1][1] = 1

    val newMatrix = Matrix.readMatrix<Double>()

    print("Print newMatrix\n")
    print(newMatrix.toString())

    println("\nPrint matrixB\n")
    println(matrixB.toString())

    println("\nPrint matrixA")
    print(matrixA.toString())

    println("\nPrint matrixB + matrixA")
    print((matrixB + matrixA).toString())

    println("\nPrint matrixB - matrixA")
    print((matrixB - matrixA).toString())

    println("\nPrint matrixB * matrixA")
    print((matrixB * matrixA).toString())

    println("\nPrint matrixB / matrixA")
    print((matrixB / matrixA).toString())
}
