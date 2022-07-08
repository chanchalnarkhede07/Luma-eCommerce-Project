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
import com.born.ecommerce.databinding.CatProductListGridBinding
import com.born.ecommerce.graphql.CategoryProductQuery

class CategoryProductsAdapter :
    ListAdapter<CategoryProductQuery.Item, CategoryProductViewHolder>(CategoryProductDiffUtil()) {

    var onItemClicked: ((CategoryProductQuery.Item) -> Unit)? = null
    var onCartButtonClicked: ((CategoryProductQuery.Item) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryProductViewHolder {
        val binding: CatProductListGridBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.cat_product_list_grid,
                parent,
                false
            )
        Log.d("TAG", "In Category Adapter")
        return CategoryProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryProductViewHolder, position: Int) {
        holder.binding.product1 = getItem(position)
        Log.d("TAG", "In Category Adpater")
        val product = getItem(position)
        holder.binding.root.setOnClickListener {
            Log.d("Category Adapter", "On Products clicked !!!")
            onItemClicked?.invoke(product)
        }
        holder.binding.addToCartBtn.setOnClickListener {
            Log.d("Category Adapter", "Add to cart Button clicked !!!")
            onCartButtonClicked?.invoke(product)
        }
    }

    override fun getItemCount(): Int {
        val size = currentList.size
        return size
    }

}

class CategoryProductDiffUtil : DiffUtil.ItemCallback<CategoryProductQuery.Item>() {

    override fun areItemsTheSame(
        oldItem: CategoryProductQuery.Item,
        newItem: CategoryProductQuery.Item
    ): Boolean {
        return oldItem.sku == newItem.sku
    }

    override fun areContentsTheSame(
        oldItem: CategoryProductQuery.Item,
        newItem: CategoryProductQuery.Item
    ): Boolean {
        return oldItem == newItem
    }

}

class CategoryProductViewHolder(val binding: CatProductListGridBinding) :
    RecyclerView.ViewHolder(binding.root) {
}

//to set imageurl in imageView
@BindingAdapter("imageUrlCatProduct")
fun setImageUrlCatProduct(imageView: ImageView, url: String?) {
    imageView.load(url) { crossfade(true) }
}

