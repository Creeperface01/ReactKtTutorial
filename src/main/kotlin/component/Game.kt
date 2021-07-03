package component

import game.GameTurn
import game.IRoundSnapshot
import game.RoundSnapshot
import game.checkForWinner
import kotlinx.html.classes
import kotlinx.html.js.onClickFunction
import react.*
import react.dom.*

external interface GameState : RState {

    var history: List<RoundSnapshot>

    var currentStep: Int
}

class Game : RComponent<RProps, GameState>() {

    private val currentSnapshot: IRoundSnapshot
        get() = state.history[state.currentStep]

    init {
        val snapshot = RoundSnapshot(
            arrayOfNulls(9),
            GameTurn.PLAYER_X
        )

        state.history = listOf(snapshot)
        state.currentStep = 0
    }

    private fun handleClick(i: Int) {
        val snapshot = currentSnapshot
        val squares = snapshot.squares.copyOf()

        if (state.history.lastIndex != state.currentStep || checkForWinner(snapshot) != null || squares[i] != null) {
            return
        }

        squares[i] = snapshot.turn.char

        val nextSnapshot = RoundSnapshot(
            squares,
            snapshot.turn.other
        )

        setState {
            this.history = this@Game.state.history + nextSnapshot
            this.currentStep = this.history.lastIndex
        }
    }

    private fun jumpTo(step: Int) {
        setState {
            this.currentStep = step
        }
    }

    override fun RBuilder.render() {
        val snapshot = currentSnapshot
        val winner = checkForWinner(snapshot)

        val status = if (winner != null) {
            "Winner: $winner"
        } else {
            "Next player: " + snapshot.turn.char
        }

        div {
            attrs.classes = setOf("game")

            div {
                attrs.classes = setOf("game-board")

                child(Board::class) {
                    attrs {
                        clickHandler = { index ->
                            handleClick(index)
                        }

                        squares = snapshot.squares
                        turn = snapshot.turn
                    }
                }
            }

            div {
                attrs.classes = setOf("game-info")

                div {
                    +status
                }

                ol {
                    state.history.indices.forEach { index ->
                        li {
                            key = index.toString()

                            button {
                                attrs {
                                    onClickFunction = {
                                        jumpTo(index)
                                    }
                                }

                                if (index == 0) {
                                    +"Go to game start"
                                } else {
                                    +"Go to move #$index"
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}