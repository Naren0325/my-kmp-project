package org.billsplit.project.di

import org.billsplit.project.api.OrderApiService
import org.billsplit.project.ui.viewmodel.BillSplitViewModel
import org.koin.dsl.module

val commonModule = module {
    // service
    single { OrderApiService() }

    // ViewModel
    factory { BillSplitViewModel() }
}