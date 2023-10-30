package de.check24.quiz.ui.navigation

import androidx.annotation.StringRes
import de.check24.quiz.R

sealed class Route(val name: String, @StringRes val resourceId: Int) {
    object MenuScreen : Route("menu_screen", R.string.menu_screen)
    object GameScreen : Route("game_screen", R.string.game_screen)
}