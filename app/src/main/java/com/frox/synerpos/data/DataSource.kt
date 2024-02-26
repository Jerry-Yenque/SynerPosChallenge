package com.frox.synerpos.data

import com.frox.synerpos.domain.model.Response
import java.lang.Exception

object DataSource {
    data class Product(val code: String, val name: String, val price: Double)
    val users = listOf(
        Pair("Test1", "123456"),
        Pair("Test2", "123456"),
        Pair("Test3", "123456"),
        Pair("Test4", "123456"),
        Pair("Test5", "123456"),
    )

    val products = listOf(
        Product("001", "CRISTAL LATA 355ML", 4.30),
        Product("002", "TURRON DOÑA PEPA 500G", 10.40),
        Product("003", "Galletas Vivo Cereales Semillas 168 gr", 3.70)
    )

    fun productByCode(code: String): Product? {
        val product = products.find { it.code == code }
        return product
    }

    fun login(user: String, password: String): Response<Pair<String, String>> {
        val pair = users.find { pair -> pair.first == user }
        if (pair != null && pair.second == password)
            return Response.Success(pair)
        return Response.Failure(Exception("Usuario o Contraseña incorrectos"))
    }
}