package com.kaloglu.duels.presentation.interfaces

import com.kaloglu.duels.data.remote.model.SampleModel
import com.kaloglu.duels.presentation.interfaces.base.mapper.Mapper
import com.kaloglu.duels.viewobjects.CachedSample
import javax.inject.Inject

/**
 * Created by kaloglu on 9.12.2018.
 */
open class SampleMapper @Inject constructor() : Mapper<SampleModel, CachedSample> {
    override fun mapTo(inModel: SampleModel) =
            inModel.run { CachedSample("sampleParameter") }
}
