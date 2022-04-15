import java.util.*
import java.math.*
import kotlin.math.floor

class RecursiveTicTacToeBoard: Board {

    constructor(){
        cells = listOf(
            Node(value=Cell(0,{}, 0, 0)),
            Node(value=Cell(1,{}, 1, 0)),
            Node(value=Cell(2,{}, 2, 0)),

            Node(value=Cell(3,{}, 0, 1)),
            Node(value=Cell(4,{}, 1, 1)),
            Node(value=Cell(5,{}, 2, 1)),

            Node(value=Cell(6,{}, 0, 2)),
            Node(value=Cell(7,{}, 1, 2)),
            Node(value=Cell(8,{}, 2, 2)),


            Node(value=Cell(9,{}, 3, 0)),
            Node(value=Cell(10,{}, 4, 0)),
            Node(value=Cell(11,{}, 5, 0)),

            Node(value=Cell(12,{}, 3, 1)),
            Node(value=Cell(13,{}, 4, 1)),
            Node(value=Cell(14,{}, 5, 1)),

            Node(value=Cell(15,{}, 3, 2)),
            Node(value=Cell(16,{}, 4, 2)),
            Node(value=Cell(17,{}, 5, 2)),


            Node(value=Cell(18,{}, 6, 0)),
            Node(value=Cell(19,{}, 7, 0)),
            Node(value=Cell(20,{}, 8, 0)),

            Node(value=Cell(21,{}, 6, 1)),
            Node(value=Cell(22,{}, 7, 1)),
            Node(value=Cell(23,{}, 8, 1)),

            Node(value=Cell(24,{}, 6, 2)),
            Node(value=Cell(25,{}, 7, 2)),
            Node(value=Cell(26,{}, 8, 2)),

//---------------------------------------------------------------------------------

            Node(value=Cell(27,{}, 0, 3)),
            Node(value=Cell(28,{}, 1, 3)),
            Node(value=Cell(29,{}, 2, 3)),

            Node(value=Cell(30,{}, 0, 4)),
            Node(value=Cell(31,{}, 1, 4)),
            Node(value=Cell(32,{}, 2, 4)),

            Node(value=Cell(33,{}, 0, 5)),
            Node(value=Cell(34,{}, 1, 5)),
            Node(value=Cell(35,{}, 2, 5)),


            Node(value=Cell(36,{}, 3, 3)),
            Node(value=Cell(37,{}, 4, 3)),
            Node(value=Cell(38,{}, 5, 3)),

            Node(value=Cell(39,{}, 3, 4)),
            Node(value=Cell(40,{}, 4, 4)),
            Node(value=Cell(41,{}, 5, 4)),

            Node(value=Cell(42,{}, 3, 5)),
            Node(value=Cell(43,{}, 4, 5)),
            Node(value=Cell(44,{}, 5, 5)),


            Node(value=Cell(45,{}, 6, 3)),
            Node(value=Cell(46,{}, 7, 3)),
            Node(value=Cell(47,{}, 8, 3)),

            Node(value=Cell(48,{}, 6, 4)),
            Node(value=Cell(49,{}, 7, 4)),
            Node(value=Cell(50,{}, 8, 4)),

            Node(value=Cell(51,{}, 6, 5)),
            Node(value=Cell(52,{}, 7, 5)),
            Node(value=Cell(53,{}, 8, 5)),

//---------------------------------------------------------------------------------

            Node(value=Cell(54,{}, 0, 6)),
            Node(value=Cell(55,{}, 1, 6)),
            Node(value=Cell(56,{}, 2, 6)),

            Node(value=Cell(57,{}, 0, 7)),
            Node(value=Cell(58,{}, 1, 7)),
            Node(value=Cell(59,{}, 2, 7)),

            Node(value=Cell(60,{}, 0, 8)),
            Node(value=Cell(61,{}, 1, 8)),
            Node(value=Cell(62,{}, 2, 8)),


            Node(value=Cell(63,{}, 3, 6)),
            Node(value=Cell(64,{}, 4, 6)),
            Node(value=Cell(65,{}, 5, 6)),

            Node(value=Cell(66,{}, 3, 7)),
            Node(value=Cell(67,{}, 4, 7)),
            Node(value=Cell(68,{}, 5, 7)),

            Node(value=Cell(69,{}, 3, 8)),
            Node(value=Cell(70,{}, 4, 8)),
            Node(value=Cell(71,{}, 5, 8)),


            Node(value=Cell(72,{}, 6, 6)),
            Node(value=Cell(73,{}, 7, 6)),
            Node(value=Cell(74,{}, 8, 6)),

            Node(value=Cell(75,{}, 6, 7)),
            Node(value=Cell(76,{}, 7, 7)),
            Node(value=Cell(77,{}, 8, 7)),

            Node(value=Cell(78,{}, 6, 8)),
            Node(value=Cell(79,{}, 7, 8)),
            Node(value=Cell(80,{}, 8, 8)),

            //----------These final nodes are the nodes that represent the samller boards, they can only be set once a smaller board is completed
            // there positions are negative to differentiate them from the other nodes

            Node(value=Cell(81,{}, -1, -1)),
            Node(value=Cell(82,{}, -4, -1)),
            Node(value=Cell(83,{}, -7, -1)),

            Node(value=Cell(84,{}, -1, -4)),
            Node(value=Cell(85,{}, -4, -4)),
            Node(value=Cell(86,{}, -7, -4)),

            Node(value=Cell(87,{}, -1, -7)),
            Node(value=Cell(88,{}, -4, -7)),
            Node(value=Cell(89,{}, -7, -7))
        )
    }

    var forced_board = -1 // the index of which board is being "forced", can be numbered 0-8 or -1 if any board can be played

    override var cells: List<Node<Cell>>    // this board will have 3*3*3*3 + 9 (90) cells, and adjacency doesn't really matter for this game
 = listOf()





    override var pieces: MutableList<GamePiece> = mutableListOf()

    override fun boardCoordsToCartesianCoords() {
        TODO("Not yet implemented")
    }

    override fun cartesianCoordsToBoardCoords() {
        TODO("Not yet implemented")
    }

    override fun getValidMoves(yourTurn: Boolean): MutableList<Move> {
        // find the cells that don't have pieces on them from the forced board.
        var move_list = mutableListOf<Move>()

        for (i in 0..8){
            var offset = 9*i
            if (forced_board == -1 || i == forced_board){
                for (j in offset..offset+8){
                    if (!findWin(i)) {
                        if (cells[j].value.occupyingPiece == null) {
                            move_list.add(
                                Move(
                                    MoveType.ADD,
                                    GamePiece(UUID.randomUUID(), cells[j], yourTurn),
                                    cells[j]
                                )
                            )
                        }
                    }
                }

            }
        }

        return move_list
    }

    fun findWin(board_offset: Int): Boolean{  // find horizontal, veritcal, and diagonal wins for each of the boards

        var flag = false

         val offset = board_offset*9

        // check vertical matches
        for(j in offset..offset+2){
            if (cells[j].value.occupyingPiece != null &&
                cells[j + 3].value.occupyingPiece != null &&
                cells[j + 6].value.occupyingPiece != null &&
                cells[j].value.occupyingPiece!!.ownerIsPlayer1 == cells[j+3].value.occupyingPiece!!.ownerIsPlayer1 && cells[j].value.occupyingPiece!!.ownerIsPlayer1 == cells[j + 6].value.occupyingPiece!!.ownerIsPlayer1){
                addPiece(GamePiece(UUID.randomUUID(), cells[board_offset + 81],cells[j].value.occupyingPiece!!.ownerIsPlayer1),cells[board_offset + 81])
                flag = true
            }

        }

        // check horizontal matches
        for(j in offset..offset+6 step 3){
            if (cells[j].value.occupyingPiece != null &&
                cells[j+1].value.occupyingPiece != null &&
                cells[j+2].value.occupyingPiece != null &&
                cells[j].value.occupyingPiece!!.ownerIsPlayer1 == cells[j + 1].value.occupyingPiece!!.ownerIsPlayer1 && cells[j].value.occupyingPiece!!.ownerIsPlayer1 == cells[j + 2].value.occupyingPiece!!.ownerIsPlayer1){
                addPiece(GamePiece(UUID.randomUUID(), cells[board_offset + 81],cells[j].value.occupyingPiece!!.ownerIsPlayer1),cells[board_offset + 81])
                flag = true
            }
        }

        // check diagonal and anti diagonal
        if (cells[offset].value.occupyingPiece != null &&
            cells[offset + 4].value.occupyingPiece != null &&
            cells[offset + 8].value.occupyingPiece != null &&
            cells[offset].value.occupyingPiece!!.ownerIsPlayer1 == cells[offset + 4].value.occupyingPiece!!.ownerIsPlayer1 && cells[offset].value.occupyingPiece!!.ownerIsPlayer1 == cells[offset + 8].value.occupyingPiece!!.ownerIsPlayer1){
            addPiece(GamePiece(UUID.randomUUID(), cells[board_offset + 81],cells[offset].value.occupyingPiece!!.ownerIsPlayer1),cells[board_offset + 81])
            flag = true
        }

        if (cells[offset + 2].value.occupyingPiece != null &&
            cells[offset + 4].value.occupyingPiece != null &&
            cells[offset + 6].value.occupyingPiece != null &&
            cells[offset + 2].value.occupyingPiece!!.ownerIsPlayer1 == cells[offset + 4].value.occupyingPiece!!.ownerIsPlayer1 && cells[offset + 2].value.occupyingPiece!!.ownerIsPlayer1 == cells[offset + 6].value.occupyingPiece!!.ownerIsPlayer1){
            addPiece(GamePiece(UUID.randomUUID(), cells[board_offset + 81],cells[offset + 2].value.occupyingPiece!!.ownerIsPlayer1),cells[board_offset + 81])
            flag = true
        }



        return flag
    }

    fun addPiece(piece: GamePiece, node: Node<Cell>){
        if (node.value.occupyingPiece == null){
            pieces.add(piece)
            node.value.occupyingPiece = piece
            forced_board = floor(node.value.id.toDouble()/9).toInt()
            if (findWin(forced_board)){
                forced_board = -1
            }

        }
    }


    override fun performMove(move: Move) {
        if (move.type != MoveType.ADD){
            println("INVALID MOVE IN THIS GAME")
        }
        if (move.node != null){
            addPiece(move.piece,move.node)
        } else{
            println("NODE VAR MUST NOT BE NULL")
        }


    }

}