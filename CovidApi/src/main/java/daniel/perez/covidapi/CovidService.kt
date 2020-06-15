package daniel.perez.covidapi

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *  https://covidtracking.com/api/
 */
interface CovidService
{
    @GET("v1/states/{state}/current.json")
    fun currentState(@Path("state") state: String): Observable<StateData>

    @GET("v1/states/{state}/daily.json")
    fun stateHistory(@Path("state") state: String): Observable<List<StateData>>
}