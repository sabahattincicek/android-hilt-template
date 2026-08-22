package com.saboon.template_hilt_starter.core.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers



/**
 * An abstraction layer over standard Coroutine Dispatchers ([Dispatchers.Main], [Dispatchers.IO], etc.).
 *
 * Decouples hardcoded coroutine dispatchers from ViewModels and UseCases, allowing custom or test dispatchers
 * (e.g., `StandardTestDispatcher`, `UnconfinedTestDispatcher`) to be injected during unit testing without
 * relying on static coroutine context overrides.
 *
 * Direct usages of hardcoded `Dispatchers.IO` or `Dispatchers.Main` inside business logic should be avoided;
 * inject [DispatcherProvider] instead.
 */
interface DispatcherProvider {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}


/**
 * Default production implementation of [DispatcherProvider] providing standard [Dispatchers] instances.
 */
class DefaultDispatchers : DispatcherProvider {
    override val main: CoroutineDispatcher get() = Dispatchers.Main
    override val io: CoroutineDispatcher get() = Dispatchers.IO
    override val default: CoroutineDispatcher get() = Dispatchers.Default
    override val unconfined: CoroutineDispatcher get() = Dispatchers.Unconfined
}