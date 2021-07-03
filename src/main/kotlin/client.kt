import component.Game
import kotlinx.browser.document
import kotlinx.browser.window
import react.dom.render
import util.child

fun main() {
    window.onload = {
        render(document.getElementById("root")) {
            child(Game::class)
        }
    }
}
