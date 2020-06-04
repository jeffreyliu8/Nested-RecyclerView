package jeffliu.nestedrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class HorizontalAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mList: ArrayList<Int>? = null
    private var mItemClickListener: OnItemClickListener? = null

    fun updateList(list: ArrayList<Int>?) {
        mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CellViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.detail_list_item_type_title, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        mList?.let {
            if (holder is CellViewHolder) {
                holder.bindViews(it[position], listener = mItemClickListener)
            }
        }
    }


    override fun getItemCount(): Int {
        return if (mList == null) 0 else mList!!.size
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, data: Int)
        fun onItemLongClick(view: View, data: Int)
    }

    // for both short and long click
    fun setOnItemClickListener(mItemClickListener: OnItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    private interface UpdateViewHolder {
        fun bindViews(data: Int, listener: OnItemClickListener?)
    }

    private class CellViewHolder(view: View) : RecyclerView.ViewHolder(view), UpdateViewHolder, View.OnClickListener, OnLongClickListener {
        private var num = 0
        private var listener: OnItemClickListener? = null
        override fun bindViews(data: Int, listener: OnItemClickListener?) {
            num = data
            val textView: TextView = itemView.findViewById(R.id.text)
            this.listener = listener
            textView.text = data.toString()
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
        }

        override fun onClick(v: View) {
            listener?.onItemClick(v, num)
        }

        override fun onLongClick(v: View): Boolean {
            listener?.onItemLongClick(v, num)
            return true
        }
    }
}