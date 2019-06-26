package dudukov.andrey.observerrecyclerviewapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import kotlinx.android.synthetic.main.item_recycler.view.*

class FruitAdapter(val adapterOnDataChanged : (Fruit, State) -> Unit): BaseAdapter<Fruit, BaseAdapter.BaseViewHolder<Fruit>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Fruit> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false)
        return FruitViewHolder(view)
    }
    override fun getItemCount(): Int {
        return items.size
    }
    override fun onBindViewHolder(holder: BaseViewHolder<Fruit>, position: Int) {
        holder.bind(items[position])
    }
    override fun swap(newItems: List<Fruit>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
    override fun addItem(item: Fruit) {
        items.add(item)
        notifyDataSetChanged()
        adapterOnDataChanged(item, State.Added)
    }
    override fun removeItem(item: Fruit) {
        val index = items.indexOf(item)
        items.remove(item)
        notifyItemRemoved(index)
        adapterOnDataChanged(item, State.Removed)
    }
    private class FruitViewHolder(itemView: View): BaseViewHolder<Fruit>(itemView) {
        override fun bind(f: Fruit) {
            itemView.tvName.text = f.name
            itemView.tvPrice.text = f.price.toString()
        }

    }
}