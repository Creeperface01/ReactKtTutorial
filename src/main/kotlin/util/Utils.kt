package util

import react.Component
import react.RBuilder
import react.RProps
import react.ReactElement
import kotlin.reflect.KClass

fun <P : RProps> RBuilder.child(
    klazz: KClass<out Component<P, *>>,
): ReactElement = child(klazz) {}

