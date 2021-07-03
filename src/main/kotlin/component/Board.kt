package component

import game.GameTurn
import game.IRoundSnapshot
import kotlinx.html.classes
import react.*
import react.dom.div

interface BoardProps : RProps, IRoundSnapshot {

    override var squares: Array<Char?>

    override var turn: GameTurn

    var clickHandler: (Int) -> Unit

}

class Board(props: BoardProps) : RComponent<BoardProps, RState>(props) {

    private fun RBuilder.renderSquare(i: Int) {
        child(SQUARE) {
            attrs {
                value = props.squares[i]?.toString() ?: ""
                clickHandler = {
                    props.clickHandler(i)
                }
            }
        }
    }

    override fun RBuilder.render() {
        div {
            for (i in 0 until 3) {
                div {
                    attrs.classes = setOf("board-row")
                    for (j in 0 until 3) {
                        renderSquare(i * 3 + j)
                    }
                }
            }
        }
    }
}