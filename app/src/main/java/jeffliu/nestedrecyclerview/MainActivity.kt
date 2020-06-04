package jeffliu.nestedrecyclerview

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        val subList1 = ArrayList<Int>()
        for (i in 0..49) {
            subList1.add(i)
        }
        val list = ArrayList<ArrayList<Int>>()
        for (i in 0..49) {
            list.add(subList1)
        }

        recyclerView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        val adapter = VerticalRecyclerAdapter(list)
        recyclerView.adapter = adapter
        adapter.SetOnItemClickListener(object : HorizontalAdapter.OnItemClickListener {
            override fun onItemClick(view: View, data: Int) {
                Toast.makeText(applicationContext, "click $data", Toast.LENGTH_SHORT).show()
            }

            override fun onItemLongClick(view: View, data: Int) {
                Toast.makeText(applicationContext, "long click $data", Toast.LENGTH_SHORT).show()
            }
        })
    }
}