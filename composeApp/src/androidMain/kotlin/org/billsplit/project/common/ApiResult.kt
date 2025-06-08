/*
 * *****************************************************************************
 * Copyright (C) IGGLOO, LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * *****************************************************************************
 */
package com.iggloo.hrs.b2b.common

sealed class ApiResult<out Success> {
    data class Success<out Success>(val value: Success) : ApiResult<Success>()
    data class Failure(val error: ApiError) : ApiResult<Nothing>()
}
