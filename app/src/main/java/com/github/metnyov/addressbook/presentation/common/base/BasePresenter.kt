package com.github.metnyov.addressbook.presentation.common.base

import com.github.metnyov.addressbook.BuildConfig
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import moxy.MvpPresenter
import moxy.presenterScope
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.exitWithAnimation
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

abstract class BasePresenter<TView : BaseView> : MvpPresenter<TView>(), CoroutineScope {

    abstract val router: Router

    override val coroutineContext: CoroutineContext
        get() = presenterScope.coroutineContext + CoroutineExceptionHandler { _, t -> onError(t) }

    /**
     * @return true - for block router logic and activity back handling
     */
    open fun onBackPressed(): Boolean {
        router.exitWithAnimation()
        return false
    }

    open fun onError(t: Throwable) {
        if (BuildConfig.DEBUG) {
            Timber.e(t)
        }
    }

    suspend fun withLoading(withBackground: Boolean = true, block: suspend () -> Unit) = try {
        viewState.showLoading(withBackground)
        block()
    } finally {
        viewState.hideLoading()
    }
}