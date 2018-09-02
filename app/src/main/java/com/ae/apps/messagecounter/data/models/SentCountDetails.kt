/*
 * Copyright 2018 Midhun Harikumar
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ae.apps.messagecounter.data.models

/**
 * Model for the data to be used in the fragment
 */
data class SentCountDetails(val cycleLimit:Int,
                            val sentToday:Int,
                            val sentCycle:Int,
                            val sentInWeek:Int,
                            val startYearCount:Int,
                            val sentLastCycle:Int,
                            val cycleDuration: String,
                            val prevCycleDuration: String
                            )