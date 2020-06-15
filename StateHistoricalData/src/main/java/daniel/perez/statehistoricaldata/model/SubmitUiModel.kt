package daniel.perez.statehistoricaldata.model

sealed class SubmitUiModel
{
	class Success(val stateData: List<ViewStateData>): SubmitUiModel()
	class Failure(val e: Throwable): SubmitUiModel()
	object InTransit: SubmitUiModel()
}