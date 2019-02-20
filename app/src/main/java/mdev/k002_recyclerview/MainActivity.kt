package mdev.k002_recyclerview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var customAdapter: CustomAdapter
    private lateinit var dataSet : ArrayList<ModelItem>
    private var arrChar = 'A'..'Z'

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        if (outState != null) {
            outState.putParcelableArrayList("dataSet",dataSet)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState != null) {
            dataSet = savedInstanceState.getParcelableArrayList("dataSet")
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        onSetClickView()
        onSetDataToList()
    }
    private fun initView() {
        dataSet = ArrayList()
        reyView.layoutManager = GridLayoutManager(this,2)
        reyView.setHasFixedSize(true)
        customAdapter = CustomAdapter(dataSet)
        reyView.adapter = customAdapter
    }
    private fun onSetClickView(){
        customAdapter.setOnClickItem(object : CustomAdapter.clickItem{
            override fun onClickItem(position: Int, title: String, subTitle: String) {
                Toast.makeText(this@MainActivity,"คุณคลิ๊ก : " + title + "\n" + subTitle,Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun onSetDataToList(){
        for (title in arrChar){
            dataSet.add(ModelItem(title.toString(),"ตำแหน่งที่ : " + (dataSet.size + 1)))
            customAdapter.notifyDataSetChanged()
        }
    }
}
