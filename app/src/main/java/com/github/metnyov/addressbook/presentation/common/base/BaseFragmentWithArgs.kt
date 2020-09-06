package com.github.metnyov.addressbook.presentation.common.base

import android.os.Parcelable
import androidx.annotation.LayoutRes
import androidx.core.os.bundleOf
import com.github.metnyov.addressbook.presentation.common.args.ArgsProvider

abstract class BaseFragmentWithArgs<TPresenter : BasePresenter<*>, TArgs : Parcelable> :
    BaseFragment<TPresenter>, ArgsProvider<TArgs> {

    private var _args: TArgs? = null
    final override var args: TArgs
        get() = run {
            if (_args == null) {
                _args = arguments!!.getParcelable(ARGUMENTS_KEY)!!
            }
            _args!!
        }
        set(args) {
            arguments = bundleOf(ARGUMENTS_KEY to args)
            _args = args
        }

    constructor() : super()
    constructor(@LayoutRes layoutRes: Int) : super(layoutRes)

    companion object {
        private const val ARGUMENTS_KEY = "FRAGMENT_ARGUMENTS"
    }
}