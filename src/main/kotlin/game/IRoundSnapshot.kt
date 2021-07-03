package game

interface IRoundSnapshot {

    val squares: Array<Char?>
    val turn: GameTurn

}