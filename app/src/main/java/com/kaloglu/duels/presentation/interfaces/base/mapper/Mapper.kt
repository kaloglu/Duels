package com.kaloglu.duels.presentation.interfaces.base.mapper

/**
 * Created by kaloglu on 9.12.2018.
 */
/**
 * Interface for model mappers. It provides helper methods that facilitate
 * retrieving of models from outer layers
 *
 * @param <V> the view model input type
 * @param <D> the domain model output type
 */
interface Mapper<in InType, out OutType> {

    fun mapTo(inModel: InType): OutType

}
