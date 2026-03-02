import java.util.LinkedList

data class Node<T>(
    val data: T,
    var left: Node<T>? = null,
    var right: Node<T>? = null
)

class BinarySearchTree<T : Comparable<T>> {

    var root: Node<T>? = null

    fun insert(value: T) {
        root = insertRecursive(root, value)
    }

    private fun insertRecursive(node: Node<T>?, value: T): Node<T> {
        if (node == null) {
            return Node(value)
        }

        when {
            value <= node.data -> {
                node.left = insertRecursive(node.left, value)
            }
            else -> {
                node.right = insertRecursive(node.right, value)
            }
        }

        return node
    }

    fun asSortedList(): List<T> {
        val result = mutableListOf<T>()
        inOrderTraversal(root, result)
        return result
    }

    private fun inOrderTraversal(node: Node<T>?, result: MutableList<T>) {
        if (node == null) return

        inOrderTraversal(node.left, result)
        result.add(node.data)
        inOrderTraversal(node.right, result)
    }

    fun asLevelOrderList(): List<T> {
        val result = mutableListOf<T>()
        if (root == null) return result

        val queue = LinkedList<Node<T>>()
        queue.add(root!!)

        while (queue.isNotEmpty()) {
            val node = queue.poll()
            result.add(node.data)

            node.left?.let { queue.add(it) }
            node.right?.let { queue.add(it) }
        }

        return result
    }
}