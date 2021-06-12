package com.example.crudprodutoretrofit.retrofit

import com.example.crudprodutoretrofit.model.Produto
import com.example.crudprodutoretrofit.retrofit.service.ProdutoService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProdutoClient (val produtoService: ProdutoService = AppRetrofit().produtoService){

    fun listarProdutos(  onSucess: (produtos:List<Produto>) -> Unit,
                         onFail: (erro: String?) -> Unit){
        produtoService.listarProdutos().enqueue(object : Callback<List<Produto>> {
            override fun onFailure(call: Call<List<Produto>>, t: Throwable) {
                onFail(t.message)
            }

            override fun onResponse(call: Call<List<Produto>>, response: Response<List<Produto>>) {
                if(response.isSuccessful)
                    response.body()?.let { onSucess(it) }
                else
                    onFail("Erro nao identificado!")
            }
        })

    }
    
}