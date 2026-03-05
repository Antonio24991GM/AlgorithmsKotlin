import kotlin.collections.ArrayDeque


class EmptyBufferException : Exception("Buffer is empty")

class BufferFullException : Exception("Buffer is full")

class CircularBuffer<T>(private val capacity: Int) {
    private val buffer = arrayOfNulls<Any?>(capacity)
    private var readPos = 0
    private var writePos = 0
    private var size = 0

    private fun isEmpty(): Boolean = size == 0
    private fun isFull(): Boolean = size == capacity

    fun read(): T {
        if (isEmpty()) {
            throw EmptyBufferException()
        }

        val value = buffer[readPos] as T
        readPos = (readPos + 1) % capacity
        size--

        return value
    }

    fun write(value: T) {
        if (isFull()) {
            throw BufferFullException()
        }

        buffer[writePos] = value
        writePos = (writePos + 1) % capacity
        size++
    }

    fun overwrite(value: T) {
        if (isFull()) {
            buffer[readPos] = value
            readPos = (readPos + 1) % capacity
            writePos = (writePos + 1) % capacity
        } else {
            write(value)
        }
    }

    fun clear() {
        readPos = 0
        writePos = 0
        size = 0
    }
}