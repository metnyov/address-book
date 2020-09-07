package com.github.metnyov.addressbook.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.metnyov.addressbook.R
import com.github.metnyov.addressbook.di.presentationModule
import com.github.metnyov.addressbook.domain.source.AuthDao
import com.github.metnyov.addressbook.domain.source.isAuthorized
import com.github.metnyov.addressbook.presentation.common.navigation.AnimatedSupportAppNavigator
import com.github.metnyov.addressbook.presentation.common.navigation.RouterProvider
import com.github.metnyov.addressbook.presentation.common.navigation.toScreen
import com.github.metnyov.addressbook.presentation.screen.departmentlist.DepartmentListFragment
import com.github.metnyov.addressbook.presentation.screen.login.LoginFragment
import moxy.MvpAppCompatActivity
import org.kodein.di.DIAware
import org.kodein.di.DITrigger
import org.kodein.di.android.closestDI
import org.kodein.di.android.retainedDI
import org.kodein.di.diContext
import org.kodein.di.instance
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

class MainActivity : MvpAppCompatActivity(), DIAware, RouterProvider {

    private val parentDI by closestDI()

    // Using retainedDI will not recreate DI when the Activity restarts
    override val di by retainedDI {
        extend(parentDI)
        import(presentationModule)
    }
    override val diContext = diContext<AppCompatActivity>(this)
    override val diTrigger = DITrigger()

    override val router: Router by instance()
    private val navigatorHolder: NavigatorHolder by instance()
    private val navigator = AnimatedSupportAppNavigator(
        this,
        supportFragmentManager,
        R.id.mainActivityContainer
    )

    private val authDao: AuthDao by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_App_Base)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        nextRoute()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    private fun nextRoute() {
        val nextScreen = if (authDao.isAuthorized) {
            DepartmentListFragment.Args().toScreen()
        } else {
            LoginFragment().toScreen()
        }
        router.newRootScreen(nextScreen)
    }
}