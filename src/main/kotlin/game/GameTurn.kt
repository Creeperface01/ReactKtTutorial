package game

enum class GameTurn(
    val char: Char
) {
    PLAYER_X('X'),
    PLAYER_O('O');

    val other: GameTurn by lazy {
        if (this == PLAYER_X) {
            PLAYER_O
        } else {
            PLAYER_X
        }
    }
}