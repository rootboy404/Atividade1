package com.example.crudprodutoretrofit

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crudprodutoretrofit.adapter.ProdutoAdapter
import com.example.crudprodutoretrofit.model.Produto
import com.example.crudprodutoretrofit.retrofit.ProdutoClient

class MainActivity() : AppCompatActivity() {

    private val REQ_CADASTRO = 1;
    private val REQ_DETALHE = 2;
    val produtoClient:ProdutoClient by lazy{
        ProdutoClient()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buscarProdutos()
    }

    private fun buscarProdutos(){
        produtoClient.listarProdutos(
            onSucess = { it ->
            listarProdutos(it)
            }, onFail = {
                    erro ->
                Toast.makeText(this, erro, Toast.LENGTH_SHORT).show()
            }
        )
    }

    private fun listarProdutos(produtos: List<Produto>) {
        val viewManager = LinearLayoutManager(this)
        var viewAdapter = ProdutoAdapter(produtos!!);

        var recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {

            setHasFixedSize(true)

            layoutManager = viewManager

            adapter = viewAdapter
        }
    }

    fun abrirFormulario(view: View) {
        val it = Intent(this, CadastroActivity::class.java)
        startActivityForResult(it, REQ_CADASTRO)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_CADASTRO) {
            if (resultCode == Activity.RESULT_OK) {
                buscarProdutos()
                Toast.makeText(this, "Cadastro realizada com sucesso!", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}

