package com.example.crudprodutoretrofit.retrofit.service

import com.example.crudprodutoretrofit.model.Produto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ProdutoService {

    @GET("/produtos")
    fun listarProdutos():Call<List<Produto>>

    @POST("/produtos")
    fun cadastrarProduto(@Body produto: Produto):Call<Unit>
}