
import java.util.*
import Board

enum class gamePhase{
    PLACING,
    ADDING,
    REMOVING
}

class NineMensMorris (override val opponent: String, override var yourTurn: Boolean = true) : Game() {
    override val id = UUID.randomUUID()
    override val name = "Nine Men's Morris"
    override var played = false
    override val instructions = "This would be the instructions for Nine Men's Morris. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris eget maximus massa, gravida consequat elit. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Morbi sit amet ultricies velit. Phasellus lobortis, nibh ut mattis feugiat, dui lectus eleifend dui, id scelerisque est ante vitae libero. Duis eu erat quis nunc pharetra ultrices. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vivamus magna lacus, tincidunt vel nisl at, lobortis tincidunt elit. Vivamus tempor augue at dictum sagittis. Curabitur at dui interdum, suscipit elit quis, luctus massa. Sed eget dui tempor, viverra enim ac, fringilla nibh. Duis mollis urna finibus eros feugiat, nec vestibulum nisl condimentum. Proin pellentesque, dui eget condimentum dapibus, enim risus venenatis quam, at vestibulum arcu enim eu odio." +
            "\n\n" +
            "Mauris et placerat neque. Suspendisse egestas leo vitae blandit viverra. Integer eget nibh fermentum, elementum velit at, laoreet lacus. Ut eu varius lorem. Sed maximus felis id eros semper vehicula. Duis tempor consequat quam. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Vestibulum non leo vitae enim viverra interdum eget id augue. Morbi tristique a neque nec egestas. Curabitur ante tellus, pharetra ac odio id, fringilla dapibus augue."

    var curr_phase = gamePhase.PLACING

    override val gameBoard: NineMensMorrisBoard = NineMensMorrisBoard()




    override fun PlayTurn() {
        yourTurn = !yourTurn
        played = false;
    }

    override fun UndoTurn() {
        played = false
    }

    override fun getValidMoves(yourTurn: Boolean): MutableList<Move> {
        return gameBoard.getValidMoves(yourTurn)
    }

    override fun performMove(move: Move) {
        gameBoard.performMove(move)
    }
}