package com.github.metnyov.addressbook.presentation.common.navigation

import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.github.metnyov.addressbook.presentation.common.navigation.entity.AnimatedCommand
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command

open class AnimatedSupportAppNavigator(
    activity: FragmentActivity,
    fragmentManager: FragmentManager,
    containerId: Int
) : SupportAppNavigator(activity, fragmentManager, containerId) {

    override fun setupFragmentTransaction(
        command: Command,
        currentFragment: Fragment?,
        nextFragment: Fragment?,
        fragmentTransaction: FragmentTransaction
    ) {
        if (command is AnimatedCommand) {
            currentFragment?.view?.let {
                // because next fragment under current
                ViewCompat.setTranslationZ(it, -1f)
            }
            fragmentTransaction.setCustomAnimations(
                command.animation.enter,
                command.animation.exit,
                command.animation.popEnter,
                command.animation.popExit
            )
        }
    }

    private fun FragmentManager.findLastVisibleChildRecursively(): Fragment? {
        val child = fragments.findLast { it.isVisible }
        val childFragments = child?.childFragmentManager?.fragments ?: return child

        return if (childFragments.isNotEmpty()) {
            childFragments.findLast {
                it.isVisible
            }?.childFragmentManager?.findLastVisibleChildRecursively() ?: return child
        } else {
            child
        }
    }
}