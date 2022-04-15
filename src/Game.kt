
import java.io.Serializable
import java.util.*

abstract class Game : Serializable {
    abstract val id: UUID
    abstract val name: String
    abstract var yourTurn: Boolean
    abstract var played: Boolean
    abstract val opponent: String
    abstract val instructions: String

    abstract val gameBoard: Board


    abstract fun PlayTurn()
    abstract fun UndoTurn()



    abstract fun getValidMoves(yourTurn: Boolean): MutableList<Move>
    abstract fun performMove(move: Move)



}