
import java.util.*

class NineMensMorrisBoard: Board {

    constructor(){
        println("Constructed!")
        cells = listOf(
            Node<Cell>(value = cell_list[0]),
            Node<Cell>(value = cell_list[1]),
            Node<Cell>(value = cell_list[2]),

            Node<Cell>(value = cell_list[3]),
            Node<Cell>(value = cell_list[4]),
            Node<Cell>(value = cell_list[5]),

            Node<Cell>(value = cell_list[6]),
            Node<Cell>(value = cell_list[7]),
            Node<Cell>(value = cell_list[8]),

            Node<Cell>(value = cell_list[9]),
            Node<Cell>(value = cell_list[10]),
            Node<Cell>(value = cell_list[11]),
            Node<Cell>(value = cell_list[12]),
            Node<Cell>(value = cell_list[13]),
            Node<Cell>(value = cell_list[14]),

            Node<Cell>(value = cell_list[15]),
            Node<Cell>(value = cell_list[16]),
            Node<Cell>(value = cell_list[17]),

            Node<Cell>(value = cell_list[18]),
            Node<Cell>(value = cell_list[19]),
            Node<Cell>(value = cell_list[20]),

            Node<Cell>(value = cell_list[21]),
            Node<Cell>(value = cell_list[22]),
            Node<Cell>(value = cell_list[23]) )


        cells[0].adjacentTo = listOf(cells[1], null, null, cells[9])
        cells[1].adjacentTo = listOf(cells[2], null, cells[0], cells[4])
        cells[2].adjacentTo = listOf(null, null, cells[1], cells[14])

        cells[3].adjacentTo = listOf(cells[4],null,null,cells[10])
        cells[4].adjacentTo = listOf(cells[5],cells[1],cells[3],cells[7])
        cells[5].adjacentTo = listOf(null,null,cells[4],cells[13])

        cells[6].adjacentTo = listOf(cells[7],null,null,cells[11])
        cells[7].adjacentTo = listOf(cells[8],cells[4],cells[6],null)
        cells[8].adjacentTo = listOf(null,null,cells[7],cells[12])

        cells[9].adjacentTo = listOf(cells[10],cells[0],null,cells[21])
        cells[10].adjacentTo = listOf(cells[11],cells[3],cells[9],cells[18])
        cells[11].adjacentTo = listOf(null,cells[6],cells[10],cells[15])
        cells[12].adjacentTo = listOf(cells[13],cells[8],null,cells[17])
        cells[13].adjacentTo = listOf(cells[14],cells[5],cells[12],cells[20])
        cells[14].adjacentTo = listOf(null,cells[2],cells[13],cells[23])

        cells[15].adjacentTo = listOf(cells[16],cells[11],null,null)
        cells[16].adjacentTo = listOf(cells[17],null,cells[15],cells[19])
        cells[17].adjacentTo = listOf(null,cells[12],cells[16],null)

        cells[18].adjacentTo = listOf(cells[19],cells[10],null,null)
        cells[19].adjacentTo = listOf(cells[20],cells[16],cells[18],cells[22])
        cells[20].adjacentTo = listOf(null,cells[5],cells[19],null)

        cells[21].adjacentTo = listOf(cells[22],cells[9],null,null)
        cells[22].adjacentTo = listOf(cells[23],cells[19],cells[21],null)
        cells[23].adjacentTo = listOf(null,cells[14],cells[22],null)
    }



    enum class gameState {
        PLACING,
        MOVING,
        REMOVING
    }


    var gamePhase: gameState = gameState.PLACING
    var prevGamePhase: gameState? = null
    var piecesPlaced: Int = 0

    // initialize all of the cells, there's probably a better way of creating the board
    // (0,0) (3,0) (6,0) | (1,1) (3,1) (5,1) | (2,2) (3,2) (4,2) | (0,3) (1,3) (2,3) (4,3) (5,3) (6,3) | (2,4) (3,4) (4,4) | (1,5) (3,5) (5,5) | (0,6) (3,6) (6,6)
    val cell_list: List<Cell> = listOf(
        Cell(0,{}, 0, 0),
        Cell(1,{}, 3, 0),
        Cell(2,{}, 6, 0),

        Cell(3,{}, 1, 1),
        Cell(4,{}, 3, 1),
        Cell(5,{}, 5, 1),

        Cell(6,{}, 2, 2),
        Cell(7,{}, 3, 2),
        Cell(8,{}, 4, 2),

        Cell(9,{}, 0, 3),
        Cell(10,{}, 1, 3),
        Cell(11,{}, 2, 3),
        Cell(12,{}, 4, 3),
        Cell(13,{}, 5, 3),
        Cell(14,{}, 6, 3),

        Cell(15,{}, 2, 4),
        Cell(16,{}, 3, 4),
        Cell(17,{}, 4, 4),

        Cell(18,{}, 1, 5),
        Cell(19,{}, 3, 5),
        Cell(20,{}, 5, 5),

        Cell(21,{}, 0, 6),
        Cell(22,{}, 3, 6),
        Cell(23,{}, 6, 6)
    )

    // the graph that represents the actual board
    override var cells: List<Node<Cell>> = listOf()

    override var pieces: MutableList<GamePiece> = mutableListOf()


    override fun boardCoordsToCartesianCoords() {
        TODO("Not yet implemented")
    }

    override fun cartesianCoordsToBoardCoords() {
        TODO("Not yet implemented")
    }

    override fun getValidMoves(yourTurn: Boolean): MutableList<Move> {
        var valid_moves = mutableListOf<Move>()

        when(gamePhase){
            gameState.MOVING -> valid_moves = getValidMovesMoving(yourTurn)
            gameState.PLACING -> valid_moves = getValidMovesPlacing(yourTurn)
            gameState.REMOVING -> valid_moves = getValidMovesRemove(yourTurn)
        }


        return valid_moves
    }



    override fun performMove(move: Move) {
        val moveType = move.type
        val targetPiece = move.piece
        val targetNode = move.node

        when (moveType){
            MoveType.MOVE -> {movePiece(targetPiece, targetNode!!)}

            MoveType.ADD -> {addPiece(targetPiece,targetNode!!)}

            MoveType.DELETE -> {removePiece(targetPiece)}
        }
    }

    fun getValidMovesPlacing(yourTurn: Boolean):MutableList<Move>{ // the initial phase where both players are placing their pieces
        var valid_moves = mutableListOf<Move>()

        // find each cell in the board that isn't being occupied by a piece, those are all possible places to create and move a piece to
        for(node in cells){
            if (node.value.occupyingPiece == null){
                valid_moves.add(Move(MoveType.ADD,GamePiece(UUID.randomUUID(),node,yourTurn) ,node))
            }
        }

        return valid_moves
    }

    fun getValidMovesMoving(yourTurn: Boolean):MutableList<Move>{ // get valid moves in the "moving" phase where players move pieces
        //   Log.d("SANITY CHECK", cells[0].adjacentTo[0].id.toString())
        // get valid moves in the "placing" phase were players move their pieces and try to remove the other player's pieces
        var valid_moves = mutableListOf<Move>()

        for (piece in pieces){ // it's possible to move pieces to adjacent cells
            if (piece.ownerIsPlayer1 == yourTurn) { // we only want to be able to move your own pieces
                val position = piece.position
                for (adjacent_cell in position.adjacentTo) {
                    if (adjacent_cell != null && adjacent_cell.value.occupyingPiece == null) {
                        valid_moves.add(Move(MoveType.MOVE, piece, adjacent_cell))
                    }
                }
            }
        }



        return valid_moves

    }




    fun getValidMovesRemove(yourTurn: Boolean): MutableList<Move>{
        var valid_moves = mutableListOf<Move>()
        gamePhase = prevGamePhase!!
        prevGamePhase = gameState.REMOVING


        for (piece in pieces) { // it's possible to move pieces to adjacent cells
            if (piece.ownerIsPlayer1 == !yourTurn) { // we only want to remove opposing pieces
                valid_moves.add(Move(MoveType.DELETE, piece))
            }
        }

        return valid_moves


    }

    fun addPiece(piece: GamePiece, node: Node<Cell>){
        pieces.add(piece)
        node.value.occupyingPiece = piece

        // change the phase so we know to remove a piece
        if (doesMillExist(piece)){
            prevGamePhase = gamePhase
            gamePhase = gameState.REMOVING
        }
        piecesPlaced ++
        if (piecesPlaced >= 18){
            prevGamePhase = gamePhase
            gamePhase = gameState.MOVING
        }
    }

    fun movePiece(piece: GamePiece, node: Node<Cell>){
        if (node.value.occupyingPiece == null){
            var prev_location = piece.position
            prev_location.value.occupyingPiece = null
            piece.position = node
            node.value.occupyingPiece = piece

            // change the phase so we know to remove a piece
            if (doesMillExist(piece)){
                prevGamePhase = gamePhase
                gamePhase = gameState.REMOVING
            }
        } else{
            println("CANNOT MOVE A PIECE TO A CELL THAT IS ALREADY OCCUPIED")
            //TODO: Throw some kind of error
        }

    }



    fun removePiece(piece: GamePiece){
        pieces.remove(piece)
        piece.position.value.occupyingPiece = null
    }

    fun doesMillExist(piece: GamePiece): Boolean{ // return true if a mill has been formed
        var flag = false

        // for (piece in pieces){
        var counter = 0 // if this counter reaches 3, a mill has been encountered
        var owner = piece.ownerIsPlayer1

        var curr_node: Node<Cell>? = piece.position
        while(curr_node?.value?.occupyingPiece != null && curr_node.value.occupyingPiece?.ownerIsPlayer1 == piece.ownerIsPlayer1){ // continually check to the right of the piece, until you hit null or the other player's piece
            counter ++
            curr_node = curr_node.adjacentTo[0]


        }



        if (counter == 3){
            return true
        }


        counter = 0

        curr_node = piece.position // reset the position
        while(curr_node?.value?.occupyingPiece != null && curr_node.value.occupyingPiece?.ownerIsPlayer1 == piece.ownerIsPlayer1){ // continually check upwards of the piece, until you hit null
            counter ++
            curr_node = curr_node.adjacentTo[1]

        }

        if (counter == 3){
            return true
        }

        counter = 0

        curr_node = piece.position // reset the position
        while(curr_node?.value?.occupyingPiece != null && curr_node.value.occupyingPiece?.ownerIsPlayer1 == piece.ownerIsPlayer1){ // continually check upwards of the piece, until you hit null
            counter ++
            curr_node = curr_node.adjacentTo[2]

        }

        if (counter == 3){
            return true
        }


        if (counter == 3){
            return true
        }


        counter = 0

        curr_node = piece.position // reset the position
        while(curr_node?.value?.occupyingPiece != null && curr_node.value.occupyingPiece?.ownerIsPlayer1 == piece.ownerIsPlayer1){ // continually check upwards of the piece, until you hit null
            counter ++
            curr_node = curr_node.adjacentTo[3]

        }

        if (counter == 3){
            return true
        }





        counter = 0


        //  }



        return false
    }


    fun test1(){
        addPiece(GamePiece(UUID.randomUUID(),cells[0],true),cells[0])

        addPiece(GamePiece(UUID.randomUUID(),cells[9],true),cells[9])
        addPiece(GamePiece(UUID.randomUUID(),cells[21],false),cells[21])


        //  removePiece(cells[0].value.occupyingPiece!!)

        // movePiece(cells[0].value.occupyingPiece!!,cells[9])
        //  removePiece(cells[0].value.occupyingPiece!!)
    }

    fun test(){
        test1()


    }

}