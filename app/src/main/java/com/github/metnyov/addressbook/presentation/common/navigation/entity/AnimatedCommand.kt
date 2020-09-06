package com.github.metnyov.addressbook.presentation.common.navigation.entity

import ru.terrakok.cicerone.Screen
import ru.terrakok.cicerone.commands.Back
import ru.terrakok.cicerone.commands.Forward
import ru.terrakok.cicerone.commands.Replace

interface AnimatedCommand {
    val animation: ScreenAnimation
}

class AnimatedForward(
    screen: Screen,
    override val animation: ScreenAnimation
) : Forward(screen), AnimatedCommand

class AnimatedBack(
    override val animation: ScreenAnimation
) : Back(), AnimatedCommand

class AnimatedReplace(
    screen: Screen,
    override val animation: ScreenAnimation
) : Replace(screen), AnimatedCommand