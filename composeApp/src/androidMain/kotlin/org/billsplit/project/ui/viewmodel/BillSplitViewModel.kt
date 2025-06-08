package org.billsplit.project.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.iggloo.hrs.b2b.common.ApiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.billsplit.project.api.OrderApiService
import org.billsplit.project.model.OrderItem
import org.billsplit.project.model.OrderWrapper
import org.billsplit.project.ui.splitbill.SplitState
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BillSplitViewModel : ScreenModel, KoinComponent {

    private val api by inject<OrderApiService>()

    private val _splitState = MutableStateFlow(SplitState.INITIAL)
    val splitState: StateFlow<SplitState> = _splitState

    private val _items = MutableStateFlow<List<OrderItem>>(emptyList())
    val items: StateFlow<List<OrderItem>> = _items

    var orders = mutableStateOf<List<OrderWrapper>>(emptyList())
        private set

    var errorMessage = mutableStateOf<String?>(null)

    private val mutex = Mutex()

    fun loadOrders(tableId: Int) {
        _splitState.value = SplitState.LOADING
        screenModelScope.launch(Dispatchers.IO) {
            mutex.withLock {
                val result = api.getOrderByTableId(tableId)
                when (result) {
                    is ApiResult.Success -> {
                        orders.value = result.value.message ?: emptyList()
                        _splitState.value = SplitState.SUCCESS
                    }
                    is ApiResult.Failure -> {
                        _splitState.value = SplitState.FAILURE
                        errorMessage.value = result.error.message
                    }
                }
            }
        }
    }
}
