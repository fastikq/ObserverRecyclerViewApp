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
    override fun addItem(item: Fruit) {
        super.addItem(item)
        adapterOnDataChanged(item, State.Added)
    }
    override fun removeItem(item: Fruit) {
        super.removeItem(item)
        adapterOnDataChanged(item, State.Removed)
    }
    private class FruitViewHolder(itemView: View): BaseViewHolder<Fruit>(itemView) {
        override fun bind(fruit: Fruit) {
            itemView.tvName.text = fruit.name
            itemView.tvPrice.text = fruit.price.toString()
        }
    }
}