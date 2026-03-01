data class FlowerFieldBoard(val gardenBoard: List<String>) {

    private fun countAdjacentFlowers(row: Int, col: Int): Int {
        var count = 0
        val directions = listOf(
            -1 to -1, -1 to 0, -1 to 1,
            0 to -1, 0 to 1,
            1 to -1, 1 to 0, 1 to 1
        )

        for ((rowOffset, colOffset) in directions) {
            val neighborRow = row + rowOffset
            val neighborCol = col + colOffset
            if (neighborRow in gardenBoard.indices &&
                neighborCol in gardenBoard[neighborRow].indices) {
                if (gardenBoard[neighborRow][neighborCol] == '*') {
                    count++
                }
            }
        }
        return count
    }

    fun withNumbers(): List<String> {
        val result = mutableListOf<String>()

        for (rowIndex in gardenBoard.indices) {
            val currentRow = gardenBoard[rowIndex]
            val newRow = StringBuilder()

            for (colIndex in currentRow.indices) {
                val currentChar = currentRow[colIndex]
                if (currentChar == '*') {
                    newRow.append('*')
                } else {
                    val count = countAdjacentFlowers(rowIndex, colIndex)
                    newRow.append(if (count > 0) count.toString()[0] else ' ')
                }
            }
            result.add(newRow.toString())
        }

        return result
    }
}
