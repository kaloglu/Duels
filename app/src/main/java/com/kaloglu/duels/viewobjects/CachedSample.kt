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

import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "user", primaryKeys = ["sampleParameter"])
data class CachedSample(
        @field:SerializedName("sampleParamater")
        val sampleParameter: String,
        @field:Expose()
        val lastRefresh: Date = Date()
) {
    fun dataExpired(maxRefreshTime: Date) = lastRefresh <= maxRefreshTime
}

