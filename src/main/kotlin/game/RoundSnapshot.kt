package game

class RoundSnapshot(
    override val squares: Array<Char?>,
    override val turn: GameTurn,
) : IRoundSnapshot