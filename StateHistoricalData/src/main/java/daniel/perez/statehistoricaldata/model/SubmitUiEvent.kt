package daniel.perez.statehistoricaldata.model

import daniel.perez.core.StateKey

sealed class SubmitUiEvent
{
	class RequestData(val state: StateKey): SubmitUiEvent()
}