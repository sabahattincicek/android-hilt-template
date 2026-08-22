package com.saboon.template_hilt_starter.core.common



/**
 * A generic wrapper class representing the state of execution for data operations (e.g., API requests, DB queries).
 *
 * Encapsulates data alongside status states ([Loading], [Success], [Error]) to communicate operational status
 * and payload downstream from the Data and Domain layers to the UI layer.
 *
 * Usage:
 * - Emit [Loading] before invoking an asynchronous task.
 * - Emit [Success] with the retrieved data payload upon successful completion.
 * - Emit [Error] with an error message and optional fallback data on failure.
 */
sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}