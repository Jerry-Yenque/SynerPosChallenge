package com.frox.synerpos.presentation.screens

import com.frox.synerpos.data.DataSource
import com.frox.synerpos.domain.model.Response
import kotlin.reflect.typeOf


fun main() {
    val product = DataSource.productByCode("005")
    print(product)

}