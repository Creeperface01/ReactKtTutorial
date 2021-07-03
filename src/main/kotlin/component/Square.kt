package component

import kotlinx.html.classes
import kotlinx.html.js.onClickFunction
import org.w3c.dom.events.Event
import react.RProps
import react.dom.attrs
import react.dom.button
import react.functionalComponent

external interface SquareProps : RProps {

    var value: String
    var clickHandler: (Event) -> Unit

}

val SQUARE = functionalComponent<SquareProps> { props ->
    button {
        attrs {
            classes = setOf("square")

            onClickFunction = {
                props.clickHandler(it)
            }
        }

        +props.value
    }
}