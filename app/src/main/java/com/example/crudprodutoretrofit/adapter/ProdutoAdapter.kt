package com.example.crudprodutoretrofit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.crudprodutoretrofit.R
import com.example.crudprodutoretrofit.model.Produto


//10.0.2.2
class ProdutoAdapter (private var listaProdutos:List<Produto>) : RecyclerView.Adapter<ProdutoAdapter.MyViewHolder>() {

    private val REQ_CADASTRO = 1;
    private val REQ_DETALHE = 2;

    var onItemClickListener: OnItemClickListener? = null


    inner class MyViewHolder:RecyclerView.ViewHolder{
        var textoId: TextView
        var textNome: TextView
        var textoPreco: TextView
        constructor(view: View) : super(view) {
            textoId = view.findViewById(R.id.textoId)
            textNome = view.findViewById(R.id.textoNome)
            textoPreco = view.findViewById(R.id.textoPreco)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.item_produto, parent, false)
        return MyViewHolder(inflate);
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textoId.text = listaProdutos.get(position).id.toString()
        holder.textNome.text = listaProdutos.get(position).nome
        holder.textoPreco.text = listaProdutos.get(position).preco.toString()
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClickedConsultar(
                listaProdutos.get(position).id
            )
        }    }

    override fun getItemCount() = listaProdutos.size



    interface OnItemClickListener {
        fun onItemClickedConsultar(id: Int)

    }
}