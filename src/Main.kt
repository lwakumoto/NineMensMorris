import java.util.Scanner

fun main(){
    var input = 0
    var game1 = RecursiveTicTacToe("Player 2", true)
    val Scan = Scanner(System.`in`)
    var yourTurn = true
    while (true){
        var move_list = game1.getValidMoves(yourTurn)
        move_list.forEachIndexed { index, move ->
            println("$index: $move")
        }

        println(yourTurn)

        println("Select your next move")
        input = Scan.nextInt()

        if (input > -1 && input < move_list.size){
            game1.performMove(move_list[input])
            yourTurn = !yourTurn
            println("Performed move!")
        }


        if (input == -1){
            "Take Input"
            break
        }

    }


//    var input = 0
//    val Scan = Scanner(System.`in`)
//    var yourTurn = true
//    while (true){
//        var move_list = game1.getValidMoves(yourTurn)
//        move_list.forEachIndexed { index, move ->
//            println("$index: $move")
//        }
//        println(game1.gameBoard.gamePhase)
//        println(yourTurn)
//
//        println("Select your next move")
//        input = Scan.nextInt()
//
//        if (input > -1 && input < move_list.size){
//            game1.performMove(move_list[input])
//            if (game1.gameBoard.gamePhase != NineMensMorrisBoard.gameState.REMOVING)yourTurn = !yourTurn
//
//            println("Performed move!")
//        }
//
//
//        if (input == -1){
//            "Take Input"
//            break
//        }
//    }

}