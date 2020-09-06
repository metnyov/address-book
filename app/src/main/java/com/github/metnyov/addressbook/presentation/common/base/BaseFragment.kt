package com.github.metnyov.addressbook.presentation.common.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.github.metnyov.addressbook.BuildConfig
import com.github.metnyov.addressbook.R
import com.github.metnyov.addressbook.presentation.common.navigation.BackButtonListener
import com.github.metnyov.addressbook.presentation.view.LoadingView
import moxy.MvpAppCompatFragment
import org.kodein.di.*
import org.kodein.di.android.x.di

abstract class BaseFragment<TPresenter : BasePresenter<*>> : MvpAppCompatFragment,
    BaseView, BackButtonListener, DIAware {

    abstract val presenter: TPresenter

    final override val di: DI by di()
    final override val diContext: DIContext<Fragment> = diContext(this)
    final override val diTrigger: DITrigger?
        get() = if (BuildConfig.DEBUG) DITrigger() else super.diTrigger

    private val loadingView: LoadingView?
        get() = view?.findViewById(R.id.loadingView)

    constructor() : super()
    constructor(@LayoutRes layoutRes: Int) : super(layoutRes)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        diTrigger?.trigger()
    }

    override fun onBackPressed(): Boolean = presenter.onBackPressed()

    override fun showLoading(withBackground: Boolean) {
        loadingView?.run {
            setBackgroundResource(
                if (withBackground) {
                    R.color.background
                } else {
                    R.color.background_loading
                }
            )
            show()
        }
    }

    override fun hideLoading() {
        loadingView?.hide()
    }
}