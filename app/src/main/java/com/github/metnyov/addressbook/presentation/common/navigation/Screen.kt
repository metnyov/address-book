@file:Suppress("unused")

package com.github.metnyov.addressbook.presentation.common.navigation

import com.github.metnyov.addressbook.presentation.common.args.FragmentArgs
import com.github.metnyov.addressbook.presentation.common.base.BaseFragment
import com.github.metnyov.addressbook.presentation.common.base.BaseFragmentWithArgs
import ru.terrakok.cicerone.android.support.SupportAppScreen

/**
 * Get SupportAppScreen for navigate with cicerone router
 *
 * Using: router.navigateTo(BaseFragment().toScreen())
 */
fun BaseFragment<*>.toScreen() = object : SupportAppScreen() {
    override fun getFragment() = this@toScreen
    override fun getScreenKey() = this@toScreen.javaClass.simpleName
}

/**
 * Need for block get SupportAppScreen by BaseFragmentWithArgs without providing args
 */
fun BaseFragmentWithArgs<*, *>.toScreen() = NotImplementedError()

/**
 * Get SupportAppScreen for navigate with cicerone router and provide fragment arguments
 *
 * Using: router.navigateTo(BaseFragmentWithArgs.FragmentArgs("arg1",...,"argN").toScreen())
 */
inline fun <reified TFragment : BaseFragmentWithArgs<*, *>, TArgs : FragmentArgs<TFragment>> TArgs.toScreen() =
    object : SupportAppScreen() {
        override fun getFragment() = TFragment::class.java.newInstance().also {
            @Suppress("UNCHECKED_CAST")
            (it as BaseFragmentWithArgs<*, TArgs>).args = this@toScreen
        }
        override fun getScreenKey() = this@toScreen.javaClass.simpleName
    }