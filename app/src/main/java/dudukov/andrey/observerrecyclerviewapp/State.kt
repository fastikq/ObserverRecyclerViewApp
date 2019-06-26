package dudukov.andrey.observerrecyclerviewapp

sealed class State {
    object Added: State()
    object Removed: State()
}