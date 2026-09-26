package com.reynanfc.cinelog.ui.home

sealed interface HomeUiEvent {
    data object OnRetryClicked : HomeUiEvent
    data object OnRefresh : HomeUiEvent
}