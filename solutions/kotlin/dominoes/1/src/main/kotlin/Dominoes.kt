class ChainNotFoundException(msg: String) : RuntimeException(msg)

data class Domino(val left: Int, val right: Int)

object Dominoes {

    fun formChain(inputDominoes: List<Domino>): List<Domino> {
        if (inputDominoes.isEmpty()) return emptyList()

        if (inputDominoes.size == 1) {
            val domino = inputDominoes[0]
            if (domino.left == domino.right) return inputDominoes
            throw ChainNotFoundException("Single domino must have matching numbers")
        }

        // Check Eulerian cycle condition
        val degrees = mutableMapOf<Int, Int>()
        for (domino in inputDominoes) {
            degrees[domino.left] = degrees.getOrDefault(domino.left, 0) + 1
            degrees[domino.right] = degrees.getOrDefault(domino.right, 0) + 1
        }

        if (degrees.values.any { it % 2 != 0 }) {
            throw ChainNotFoundException("No valid chain exists")
        }

        // Try starting with each domino
        val used = BooleanArray(inputDominoes.size)
        val chain = mutableListOf<Domino>()

        for (i in inputDominoes.indices) {
            // Try normal orientation
            used[i] = true
            chain.add(inputDominoes[i])

            if (buildChain(inputDominoes, used, chain, inputDominoes[i].right, inputDominoes[i].left)) {
                return chain.toList()
            }

            // Try flipped orientation
            chain[0] = Domino(inputDominoes[i].right, inputDominoes[i].left)

            if (buildChain(inputDominoes, used, chain, inputDominoes[i].left, inputDominoes[i].right)) {
                return chain.toList()
            }

            used[i] = false
            chain.clear()
        }

        throw ChainNotFoundException("No valid chain found")
    }

    private fun buildChain(
        dominoes: List<Domino>,
        used: BooleanArray,
        chain: MutableList<Domino>,
        currentEnd: Int,
        targetEnd: Int
    ): Boolean {
        if (chain.size == dominoes.size) {
            return currentEnd == targetEnd
        }

        for (i in dominoes.indices) {
            if (used[i]) continue

            val domino = dominoes[i]

            // Try normal orientation
            if (domino.left == currentEnd) {
                used[i] = true
                chain.add(domino)

                if (buildChain(dominoes, used, chain, domino.right, targetEnd)) {
                    return true
                }

                chain.removeAt(chain.size - 1)
                used[i] = false
            }

            // Try flipped orientation
            if (domino.right == currentEnd) {
                used[i] = true
                chain.add(Domino(domino.right, domino.left))

                if (buildChain(dominoes, used, chain, domino.left, targetEnd)) {
                    return true
                }

                chain.removeAt(chain.size - 1)
                used[i] = false
            }
        }

        return false
    }
}
