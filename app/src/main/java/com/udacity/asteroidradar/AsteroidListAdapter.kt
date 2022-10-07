package com.udacity.asteroidradar


import android.view.LayoutInflater
import androidx.recyclerview.widget.ListAdapter
import com.udacity.asteroidradar.Asteroid
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.databinding.AsteroidItemListBinding



class AsteroidListAdapter(private val itemClickListener: AsteroidClickListener) : ListAdapter<Asteroid, AsteroidViewHolder>(AsteroidDiffCallBack()){

    /**
     * we need to update the views held by this viewHolder to show an item's position
     * in other words does recycling
     */
    override fun onBindViewHolder(holder: AsteroidViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item,itemClickListener )
    }

    /**
     * how to create a new viewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidViewHolder {
       return AsteroidViewHolder.create(parent)
    }
}

class AsteroidViewHolder(private val binding : AsteroidItemListBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind (item : Asteroid ,clickListener: AsteroidClickListener){
         binding.clicklistener = clickListener
         binding.asteroid = item
         binding.executePendingBindings()

    }
    companion object{
    fun create(parent: ViewGroup): AsteroidViewHolder{
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = AsteroidItemListBinding.inflate(layoutInflater, parent, false)
        return AsteroidViewHolder(binding)
     }
    }


}


class  AsteroidClickListener(val clickListener: (Asteroid) -> Unit ){
    fun onclick(data: Asteroid) = clickListener(data)
}

/**
 * calculating differences between two lists
 */
class AsteroidDiffCallBack : DiffUtil.ItemCallback<Asteroid>() {
    override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
       return oldItem == newItem
    }


}