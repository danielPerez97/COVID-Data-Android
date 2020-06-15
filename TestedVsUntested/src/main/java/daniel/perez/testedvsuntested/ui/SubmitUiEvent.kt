package daniel.perez.testedvsuntested.ui

import daniel.perez.core.StateKey

sealed class SubmitUiEvent
{
    class RequestData(val state: StateKey): SubmitUiEvent()
}