package com.github.metnyov.addressbook.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.metnyov.addressbook.R
import moxy.MvpAppCompatActivity
import org.kodein.di.DIAware
import org.kodein.di.DITrigger
import org.kodein.di.android.closestDI
import org.kodein.di.android.retainedDI
import org.kodein.di.diContext

class MainActivity : MvpAppCompatActivity(), DIAware {

    private val parentDI by closestDI()

    // Using retainedDI will not recreate DI when the Activity restarts
    override val di by retainedDI {
        extend(parentDI)
    }
    override val diContext = diContext<AppCompatActivity>(this)
    override val diTrigger = DITrigger()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_App_Base)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
    }
}