/*
 * *****************************************************************************
 * Copyright (C) IGGLOO, LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * *****************************************************************************
 */
package com.iggloo.hrs.b2b.common

data class ApiError(val code: Int, val message: String) {

    fun toMessage() = "$code : $message"
}
