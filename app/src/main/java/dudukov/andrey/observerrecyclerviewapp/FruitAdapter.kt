package dudukov.andrey.observerrecyclerviewapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView

class FruitAdapter(val adapterOnDataChanged : (Fruit, Action) -> Unit): BaseAdapter<Fruit, BaseAdapter.BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false)
        return FruitViewHolder(view)
    }
    override fun getItemCount(): Int {
        return items.size
    }
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val currentItem = items[position]
        if(holder is FruitViewHolder) {
            holder.apply {
                tvName.text = currentItem.name
                tvPrice.text = currentItem.price.toString()
            }
        }
    }
    override fun swap(newItems: List<Fruit>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
    override fun addItem(item: Fruit) {
        items.add(item)
        notifyDataSetChanged()
        adapterOnDataChanged(item, Action.ADDED)
    }
    override fun removeItem(item: Fruit) {
        val index = items.indexOf(item)
        items.remove(item)
        notifyItemRemoved(index)
        adapterOnDataChanged(item, Action.REMOVED)
    }
    private class FruitViewHolder(itemView: View): BaseViewHolder(itemView) {
        val tvName: AppCompatTextView = itemView.findViewById(R.id.tvName)
        val tvPrice: AppCompatTextView = itemView.findViewById(R.id.tvPrice)
    }
}