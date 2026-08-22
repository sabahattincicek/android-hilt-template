package com.template.compose_hilt_starter.core.common

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource



/**
 * A wrapper class used to decouple UI text representations from Android framework dependencies (like Context).
 *
 * Pass string resources (`R.string.x`) or dynamic strings from ViewModels to the UI layer without leaking
 * `Context` into the presentation or domain logic, keeping ViewModels clean and testable.
 *
 * Usage:
 * - Hardcoded / Dynamic text: `UiText.DynamicString("Hello World")`
 * - Resource ID text: `UiText.StringResource(R.string.hello_user, "John")`
 *
 * To render in Compose: `uiText.asString()`
 * To resolve in non-Composable Android contexts: `uiText.asString(context)`
 */
sealed class UiText {
    data class DynamicString(val value: String) : UiText()
    class StringResource(
        @StringRes val resId: Int,
        vararg val args: Any
    ) : UiText()

    fun asString(context: Context): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> context.getString(resId, *args)
        }
    }

    @Composable
    fun asString(): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> stringResource(resId, *args)
        }
    }
}