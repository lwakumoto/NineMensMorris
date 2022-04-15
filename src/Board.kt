
import java.util.*

abstract class Board {


    // The board will be represented as a graph (represented as a list of linked lists) of cells
    abstract var cells: List<Node<Cell>>
    abstract var pieces: MutableList<GamePiece>

    abstract fun boardCoordsToCartesianCoords() // converts "board coordinates" to normal x,y coordinates
    abstract fun cartesianCoordsToBoardCoords() // vice versa

    abstract fun getValidMoves(yourTurn: Boolean): MutableList<Move>
    abstract fun performMove(move: Move)

    // for each node in the linked list, draw it
    fun drawNodes(){

    }


}