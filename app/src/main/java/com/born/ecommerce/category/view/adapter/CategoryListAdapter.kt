package com.born.ecommerce.category.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.PopupMenu
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.born.ecommerce.R
import com.born.ecommerce.category.model.CategoryDataClass
import com.born.ecommerce.category.view.ui.CategoryFragmentDirections
import com.born.ecommerce.databinding.CategoryListBinding


class CategoryListAdapter(val context: Context, val list: ArrayList<CategoryDataClass>) :
    RecyclerView.Adapter<CategoryListAdapter.CategoryViewHolder>() {

    //creating viewholder inner class
    class CategoryViewHolder(val binding: CategoryListBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding: CategoryListBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.category_list,
                parent,
                false
            )
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        //setting positon of category object to layout databinding object
        holder.binding.category = list.get(position)

        var count = 0
        holder.binding.catImagebutton.setOnClickListener {
            Log.d("TAG", "Onclick on ${list.get(position).title}")

            //creating popupmenu varibales
            val popup = PopupMenu(context, holder.binding.catImagebutton)

            //Logic for arrow
            count++
            if (count == 1) {
                holder.binding.catImagebutton.setImageResource(R.drawable.outline_arrow_drop_up_24)
            }
            if (count == 2) {
                holder.binding.catImagebutton.setImageResource(R.drawable.outline_arrow_drop_down_24)
                count = 0
            }

            //inflating menu from xml resource
            when (position) {
                0 -> popup.inflate(R.menu.man_category_menu)
                1 -> popup.inflate(R.menu.women_categoey_menu)
                2 -> popup.inflate(R.menu.gear_category_menu)
                3 -> popup.inflate(R.menu.electonics_category_menu)
            }

            //adding click listener
            popup.setOnMenuItemClickListener { item ->
                when (item.getItemId()) {

                    R.id.cat_man_sweatshirts -> {
                        Log.d("TAG", "Onclick1 SweatShirts")
                        val id: String = "15"
                        it.findNavController()
                            .navigate(
                                CategoryFragmentDirections.actionCategoryFragmentToCategoryProductListFragment(
                                    id
                                )
                            )
                    }
                    R.id.cat_man_jackets -> {
                        Log.d("TAG", "Onclick2 jackets")
                        val id: String = "14"
                        it.findNavController()
                            .navigate(
                                CategoryFragmentDirections.actionCategoryFragmentToCategoryProductListFragment(
                                    id
                                )
                            )
                    }
                    R.id.cat_man_pants -> {
                        Log.d("TAG", "Onclick3 Pant")
                        val id: String = "18"
                        it.findNavController()
                            .navigate(
                                CategoryFragmentDirections.actionCategoryFragmentToCategoryProductListFragment(
                                    id
                                )
                            )
                    }
                    R.id.cat_man_shorts -> {
                        Log.d("TAG", "Onclick3 shorts")
                        val id: String = "19"
                        it.findNavController()
                            .navigate(
                                CategoryFragmentDirections.actionCategoryFragmentToCategoryProductListFragment(
                                    id
                                )
                            )
                    }
                    R.id.cat_man_tanks -> {
                        Log.d("TAG", "Onclick3 tanks")
                        val id: String = "17"
                        it.findNavController()
                            .navigate(
                                CategoryFragmentDirections.actionCategoryFragmentToCategoryProductListFragment(
                                    id
                                )
                            )
                    }
                    R.id.cat_man_tees -> {
                        Log.d("TAG", "Onclick3 tees")
                        val id: String = "16"
                        it.findNavController()
                            .navigate(
                                CategoryFragmentDirections.actionCategoryFragmentToCategoryProductListFragment(
                                    id
                                )
                            )
                    }
                    R.id.cat_man_shoes -> {
                        Log.d("TAG", "Onclick3 shoes")
                        val id: String = "51"
                        it.findNavController()
                            .navigate(
                                CategoryFragmentDirections.actionCategoryFragmentToCategoryProductListFragment(
                                    id
                                )
                            )
                    }
                    R.id.cat_women_pant -> {
                        Log.d("TAG", "Onclick3 wpant")
                        val id: String = "32"
                        it.findNavController()
                            .navigate(
                                CategoryFragmentDirections.actionCategoryFragmentToCategoryProductListFragment(
                                    id
                                )
                            )
                    }
                    R.id.cat_women_tees -> {
                        Log.d("TAG", "Onclick3 wtees")
                        val id: String = "33"
                        it.findNavController()
                            .navigate(
                                CategoryFragmentDirections.actionCategoryFragmentToCategoryProductListFragment(
                                    id
                                )
                            )
                    }
                    R.id.cat_electronics_laptop -> {
                        Log.d("TAG", "Onclick3 electronics laptop")
                        val id: String = "42"
                        it.findNavController()
                            .navigate(
                                CategoryFragmentDirections.actionCategoryFragmentToCategoryProductListFragment(
                                    id
                                )
                            )
                    }
                    R.id.cat_electronics_software -> {
                        Log.d("TAG", "Onclick3 electronics software")
                        val id: String = "47"
                        it.findNavController()
                            .navigate(
                                CategoryFragmentDirections.actionCategoryFragmentToCategoryProductListFragment(
                                    id
                                )
                            )
                    }
                    R.id.cat_gear_bags -> {
                        Log.d("TAG", "Onclick3 bags")
                        val id: String = "4"
                        it.findNavController()
                            .navigate(
                                CategoryFragmentDirections.actionCategoryFragmentToCategoryProductListFragment(
                                    id
                                )
                            )
                    }
                    R.id.cat_gear_fitness_equipment -> {
                        Log.d("TAG", "Onclick3 fitness")
                        val id: String = "5"
                        it.findNavController()
                            .navigate(
                                CategoryFragmentDirections.actionCategoryFragmentToCategoryProductListFragment(
                                    id
                                )
                            )
                    }
                    R.id.cat_gear_watches -> {
                        Log.d("TAG", "Onclick3 watches")
                        val id: String = "6"
                        it.findNavController()
                            .navigate(
                                CategoryFragmentDirections.actionCategoryFragmentToCategoryProductListFragment(
                                    id
                                )
                            )
                    }
                }
                false
            }
            popup.show()
        }
    }


    override fun getItemCount(): Int {
        return list.size
    }
}

//to set imageurl in imageView
@BindingAdapter("imageUrlCatList")
fun setImageUrlToCategoryList(imageView: ImageView, url: String?) {
    imageView.load(url) { crossfade(true) }
}