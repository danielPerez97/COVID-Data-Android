package daniel.perez.testedvsuntested.ui

import daniel.perez.testedvsuntested.model.ViewStateData

sealed class SubmitUiModel
{
    class Success(val stateData: ViewStateData): SubmitUiModel()
    class Failure(val e: Throwable): SubmitUiModel()
    object InTransit: SubmitUiModel()
}