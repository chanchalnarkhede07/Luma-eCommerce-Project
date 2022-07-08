package com.born.ecommerce.category.view.adapter


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.born.ecommerce.R
import com.born.ecommerce.databinding.ProductListGridBinding
import com.born.ecommerce.graphql.SearchProductQuery

class ProductsAdapter : ListAdapter<SearchProductQuery.Item, ProductViewHolder>(ProductDiffUtil()) {

    var onItemClicked: ((SearchProductQuery.Item) -> Unit)? = null
    var onCartButtonClicked: ((SearchProductQuery.Item) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding: ProductListGridBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.product_list_grid,
                parent,
                false
            )
        Log.d("TAG", "In Products Adapter")
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.binding.product1 = getItem(position)
        Log.d("TAG", "In Products Adpater")
        val product = getItem(position)
        holder.binding.root.setOnClickListener {
            Log.d("Products Adapter", "On Products clicked !!!")
            onItemClicked?.invoke(product)
        }
        holder.binding.addToCartBtn.setOnClickListener {
            Log.d("Products Adapter", "Add to cart Button clicked !!!")
            onCartButtonClicked?.invoke(product)
        }
    }

    override fun getItemCount(): Int {
        val size = currentList.size
        return size
    }

}

class ProductDiffUtil : DiffUtil.ItemCallback<SearchProductQuery.Item>() {

    override fun areItemsTheSame(
        oldItem: SearchProductQuery.Item,
        newItem: SearchProductQuery.Item
    ): Boolean {
        return oldItem.sku == newItem.sku
    }
    override fun areContentsTheSame(
        oldItem: SearchProductQuery.Item,
        newItem: SearchProductQuery.Item
    ): Boolean {
        return oldItem == newItem
    }
}

class ProductViewHolder(val binding: ProductListGridBinding) :
    RecyclerView.ViewHolder(binding.root) {
}


//to set imageurl in imageView
@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    imageView.load(url) { crossfade(true) }
}
