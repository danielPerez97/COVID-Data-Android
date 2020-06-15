package daniel.perez.populationapi.internal

import daniel.perez.populationapi.Population
import daniel.perez.populationapi.PopulationList
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

/**
 * ROOT URL: https://datausa.io/api/
 */
interface PopulationApi
{
    @GET("data?drilldowns=State&measures=Population&year=latest")
    fun getStatePopulation(): Observable<PopulationList>
}