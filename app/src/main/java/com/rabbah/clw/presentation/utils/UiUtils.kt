package com.rabbah.clw.presentation.utils

import com.rabbah.clw.presentation.wallet.CardState
import com.rabbah.domain.model.network.response.CardDto

object UiUtils {

    fun getCardState(cardDto: CardDto?): CardState? {
        if(cardDto == null) {
            return null
        }
        return if (cardDto.isSuspended ?: false) {
            CardState.Suspended
        } else if(cardDto.isLocked ?: false) {
            CardState.Locked
        } else {
            CardState.Unlocked
        }
    }
}