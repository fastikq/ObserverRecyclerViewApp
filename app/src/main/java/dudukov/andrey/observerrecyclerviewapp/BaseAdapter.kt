package dudukov.andrey.observerrecyclerviewapp

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<M, VH: BaseAdapter.BaseViewHolder<M>>: RecyclerView.Adapter<VH>() {

    protected val items: MutableList<M> = mutableListOf()
    abstract class BaseViewHolder<M>(itemView: View) : RecyclerView.ViewHolder(itemView){
        abstract fun bind(fruit: M)
    }
    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH
    abstract override fun onBindViewHolder(holder: VH, position: Int)
    override fun getItemCount() = items.size
    open fun replace(newItems: List<M>){
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
    open fun addItem(item: M){
        items.add(item)
        notifyItemInserted(items.size - 1)
    }
    open fun removeItem(item: M){
        val index = items.indexOf(item)
        items.remove(item)
        notifyItemRemoved(index)
    }

}