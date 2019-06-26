package dudukov.andrey.observerrecyclerviewapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val fruitsList: MutableList<Fruit> = mutableListOf()
    private val adapter: FruitAdapter by lazy { FruitAdapter{ item, state -> adapterCallBack(item, state)} }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fruitsList.addAll(getFruitList())
        recyclerList.adapter = adapter
        adapter.swap(fruitsList)
        btnAdd.setOnClickListener { adapter.addItem(getRandomFruit()) }
        btnRemove.setOnClickListener{ adapter.removeItem(getRandomFruitForRemove()) }
        calculateCost()
    }
    private fun adapterCallBack(item: Fruit, state: State) {
        when(state){
            is State.Added -> fruitsList.add(item)
            is State.Removed -> fruitsList.remove(item)
        }
        btnRemove.isEnabled = fruitsList.size != 0
        calculateCost()
    }
    private fun calculateCost(){
        val allCost = fruitsList.sumBy { it.price }
        val sum = StringBuilder()
        sum.append(getString(R.string.tv_sum))
        sum.append(" ")
        sum.append(allCost)
        sum.append(" ")
        sum.append(getString(R.string.uah))
        tvSum.text = sum
    }
    private fun getFruitList(): MutableList<Fruit> {
        return mutableListOf(
            Fruit("Banana", 235),
            Fruit("Kiwi", 145),
            Fruit("Cherry", 300),
            Fruit("Lemon", 265),
            Fruit("Mandarin", 400),
            Fruit("Grapefruit", 500),
            Fruit("Mango", 160),
            Fruit("Apricot", 465),
            Fruit("Lime", 310),
            Fruit("Avocado", 565)
        )
    }
    private fun getRandomFruit() : Fruit{
        val randomIndex = (0 until 9).random()
        return getFruitList()[randomIndex]
    }
    private fun getRandomFruitForRemove() : Fruit{
        val randomIndex = if(fruitsList.size > 1) (0 until fruitsList.size - 1).random() else 0
        return fruitsList[randomIndex]
    }
}
