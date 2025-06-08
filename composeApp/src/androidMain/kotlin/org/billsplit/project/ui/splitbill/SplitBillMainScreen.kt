package org.billsplit.project.ui.splitbill

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalFocusManager
import cafe.adriel.voyager.core.lifecycle.LifecycleEffect
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import co.touchlab.kermit.Logger
import org.billsplit.project.ui.viewmodel.BillSplitViewModel


class SplitBillMainScreen : Screen {

    override val key: ScreenKey = uniqueScreenKey

    @Composable
    override fun Content() {
        LifecycleEffect(
            onStarted = { Logger.d("Navigator: Start LoginScreen") },
            onDisposed = { Logger.d("Navigator: Dispose LoginScreen") }
        )
        val viewModel = getScreenModel<BillSplitViewModel>()

        SplitBillMainView(viewModel)
    }
}
