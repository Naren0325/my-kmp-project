/*
 * *****************************************************************************
 * Copyright (C) IGGLOO, LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * *****************************************************************************
 */
package com.iggloo.hrs.b2b.common

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.intOrNull
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

fun parseErrorMessage(errorBody: String): String {
    return try {
        val jsonObject = Json.parseToJsonElement(errorBody).jsonObject
        val code = jsonObject["code"]?.toString() ?: "Unknown code"
        val message = jsonObject["message"]?.toString() ?: "Unknown error"
        "$code, $message"
    } catch (e: Exception) {
        "An error occurred while parsing the error message."
    }
}

