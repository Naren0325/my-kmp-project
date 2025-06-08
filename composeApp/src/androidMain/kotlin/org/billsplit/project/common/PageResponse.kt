/*
 * *****************************************************************************
 * Copyright (C) IGGLOO, LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * *****************************************************************************
 */
package com.iggloo.hrs.b2b.common

import kotlinx.serialization.Serializable

@Serializable
data class  PageResponse<T>(
    val totalPages: Int,
    val totalElements: Long,
    var currentPage: Int,
    var contents: List<T>? = null
)
