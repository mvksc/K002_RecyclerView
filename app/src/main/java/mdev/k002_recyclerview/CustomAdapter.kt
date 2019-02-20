package mdev.k002_recyclerview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_view.view.*

class CustomAdapter(val dataSet: ArrayList<ModelItem> ) : RecyclerView.Adapter<CustomAdapter.ViewHolder>(){

    private var mClickItem: clickItem? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.item_view,parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
      return dataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtPosition.text = dataSet[position].position
        holder.txtTitle.text = dataSet[position].title

        holder.lnContentItem?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (mClickItem != null)
                    mClickItem?.onClickItem(position,dataSet[position].title,dataSet[position].position)
            }
        })
    }


    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val lnContentItem = itemView.lnContentItem
        val txtTitle = itemView.tvTitle
        val txtPosition = itemView.tvPosition
    }

    fun setOnClickItem(rClickItem: clickItem){
        mClickItem = rClickItem
    }
    interface clickItem{
        fun onClickItem(position: Int,title: String,subTitle: String)
    }
}