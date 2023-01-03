package com.example.daggermvvm.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.daggermvvm.Model.ProductsResponse
import com.example.daggermvvm.R


class ProductAdapter(private val itemList: List<ProductsResponse>) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_card, parent, false)

        return ViewHolder(view, onTapListener)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val response = itemList.get(position)

        holder.titleText.setText(response.title)
        Glide.with(holder.itemView.context).load(response.image).into(holder.imageView)
    }

    override fun getItemCount(): Int {

        return itemList.size
    }

    private lateinit var  onTapListener: OnTapListener

    interface OnTapListener{
        fun onItemClick(position: Int)
    }

    public fun setOnItemClickListener(onTapListener: OnTapListener){
        this.onTapListener = onTapListener
    }
    class ViewHolder(view: View, onClickListener: OnTapListener) : RecyclerView.ViewHolder(view) {
        val titleText: TextView = view.findViewById(R.id.titleText)
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val addButton: AppCompatButton = view.findViewById(R.id.addProductButton)


        init {
            addButton.setOnClickListener {
                onClickListener.onItemClick(adapterPosition)
            }
        }
    }

}





