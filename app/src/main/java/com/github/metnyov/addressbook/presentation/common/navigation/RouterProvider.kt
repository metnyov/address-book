package com.github.metnyov.addressbook.presentation.common.navigation

import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.Router

interface RouterProvider {

    val router: Router
}

val Fragment.parentRouter: Router
    get() = (parentFragment as? RouterProvider)?.router
        ?: (requireActivity() as RouterProvider).router