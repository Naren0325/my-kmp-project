package org.billsplit.project

import androidx.compose.runtime.*
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import co.touchlab.kermit.Logger
import org.billsplit.project.ui.splitbill.SplitBillMainScreen
import org.billsplit.project.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    AppTheme {
        Navigator(SplitBillMainScreen(), onBackPressed = { currentScreen ->
            Logger.d("app.nav: Navigator: Pop screen #$currentScreen")
            true
        }) { navigator ->
            SlideTransition(navigator)
        }
    }
}