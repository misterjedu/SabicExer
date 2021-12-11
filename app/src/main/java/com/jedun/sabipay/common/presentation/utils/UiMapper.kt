package com.jedun.sabipay.common.presentation.utils

interface UiMapper<Domain, Ui> {

    fun mapToUi(domain: Domain): Ui

}