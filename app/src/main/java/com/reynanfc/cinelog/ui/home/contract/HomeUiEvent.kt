package com.reynanfc.cinelog.ui.home.contract

sealed interface HomeUiEvent {
    data object OnRetryClicked : HomeUiEvent
    data object OnRefresh : HomeUiEvent
}
