package ru.terrakok.cicerone

import com.github.metnyov.addressbook.presentation.common.navigation.entity.AnimatedBack
import com.github.metnyov.addressbook.presentation.common.navigation.entity.AnimatedForward
import com.github.metnyov.addressbook.presentation.common.navigation.entity.AnimatedReplace
import com.github.metnyov.addressbook.presentation.common.navigation.entity.TransitionType
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.BackTo

fun Router.exitWithAnimation(transitionType: TransitionType = TransitionType.DEFAULT) {
    commandBuffer.executeCommands(
        arrayOf(
            AnimatedBack(transitionType.animation)
        )
    )
}

fun Router.navigateToWithAnimation(
    screen: Screen,
    transitionType: TransitionType = TransitionType.DEFAULT
) {
    commandBuffer.executeCommands(
        arrayOf(
            AnimatedForward(screen, transitionType.animation)
        )
    )
}

fun Router.replaceScreenWithAnimation(
    screen: SupportAppScreen,
    transitionType: TransitionType = TransitionType.DEFAULT
) {
    commandBuffer.executeCommands(
        arrayOf(
            AnimatedReplace(screen, transitionType.animation)
        )
    )
}

fun Router.newRootScreenWithAnimation(
    screen: Screen,
    transitionType: TransitionType = TransitionType.DEFAULT
) {
    commandBuffer.executeCommands(
        arrayOf(
            BackTo(null),
            AnimatedReplace(screen, transitionType.animation)
        )
    )
}

fun Router.backToWithOpenScreenWithAnimation(
    backToScreen: Screen?,
    nextScreen: Screen,
    transitionType: TransitionType = TransitionType.DEFAULT
) {
    commandBuffer.executeCommands(
        arrayOf(
            BackTo(backToScreen),
            AnimatedForward(nextScreen, transitionType.animation)
        )
    )
}