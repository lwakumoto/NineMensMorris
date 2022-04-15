data class Node<T: Any>( var value: T, var adjacentTo: List<Node<T>?> = listOf()){

    fun setAdjacencyList(newList: List<Node<T>?>){
        this.adjacentTo = newList
    }

    override fun toString(): String {
        var result = "Node: "
        result += "$value"

        return result
    }
}