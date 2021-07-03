package game

fun checkForWinner(snapshot: IRoundSnapshot): Char? {
    val lines = arrayOf(
        arrayOf(0, 1, 2),
        arrayOf(3, 4, 5),
        arrayOf(6, 7, 8),

        arrayOf(0, 3, 6),
        arrayOf(1, 4, 7),
        arrayOf(2, 5, 8),

        arrayOf(2, 4, 6),
        arrayOf(0, 4, 8),
    )

    lines.forEach { line ->
        return line
            .map { index -> snapshot.squares[index] }
            .distinct()
            .singleOrNull() ?: return@forEach
    }

    return null
}