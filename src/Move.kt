
// Inputs:
// type: the type of move that the player wishes to perform
// piece: the piece the move should be operated on
// cell (optional): the cell the piece should be moved to (only valid for "move" moves)
class Move(val type: MoveType,val piece: GamePiece,val node: Node<Cell>? = null ) {

    override fun toString(): String {
        var str = ""
        str = when(type){
            MoveType.MOVE -> "MOVE"
            MoveType.ADD -> "ADD"
            MoveType.DELETE -> "DELETE"
        }

        str += " Piece $piece"

        if (node != null){
            str += " To cell ${node.value.id}"
        }

        return str
    }
}