/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kaloglu.duels.viewobjects

import com.kaloglu.duels.domain.enums.Status
import com.kaloglu.duels.domain.enums.Status.*

/**
 * A generic class that holds a value with its loading status.
 * @param <M>
</M> */
data class Resource<out M>(val status: Status, val data: M? = null, val message: String? = null) {
    companion object {
        fun <M> success(data: M?): Resource<M> {
            return when (data) {
                null -> empty()
                else -> Resource(SUCCESS, data, null)
            }
        }

        fun <M> error(msg: String, data: M? = null) = Resource(ERROR, data, msg)

        fun <M> loading(data: M? = null) = Resource(LOADING, data, null)

        fun <M> empty() = Resource<M>(EMPTY)
    }
}
