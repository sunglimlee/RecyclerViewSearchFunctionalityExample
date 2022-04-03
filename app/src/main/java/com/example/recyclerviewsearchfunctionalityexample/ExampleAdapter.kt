package com.example.recyclerviewsearchfunctionalityexample

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//1. ExampleAdapter에다가 ExampleViewHolder, OnItemClickListener, mListener, setItemClickListener로 연결

class ExampleAdapter(var mExampleList : ArrayList<ExampleItem>) : RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder>() {
    private lateinit var mListener : OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position : Int) {

        }
        fun onDeleteClick(position : Int) {

        }
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        mListener = onItemClickListener
    }

    class ExampleViewHolder(itemView : View, listener : OnItemClickListener) : RecyclerView.ViewHolder(itemView) {
        var mImageView : ImageView? = null
        var mTextView1 : TextView? = null
        var mTextView2 : TextView? = null
        //private var mImageView_Delete : ImageView? = null 당장 안만들어도 되잖아...
        init {
            mImageView = itemView.findViewById(R.id.imageView)
            mTextView1 = itemView.findViewById(R.id.textview)
            mTextView2 = itemView.findViewById(R.id.textview2)
            //mImageView_Delete = itemView.findViewById(R.id.imageview_delete)
            //함수를 실행하면서 실행부분은 함수안에 들어있어야지.. 왜자꾸 클래스 바깥에다가 넣는냐고... 그러니깐 에러가 나지.. 이젠 이런실수를 하면 않되잖아?
            itemView.setOnClickListener {
                if (listener != null) {
                    val position : Int = adapterPosition //adapterPosition기억나니? 해당 아답의 Position이 기억되어 있는걸 사용하는거지.
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position)
                    }
                }
            }
            /* mImageView_Delete!!.setOnClickListener {
                if (listener != null) {
                    val position: Int = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onDeleteClick(position)
                    }
            } */
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        //여기서 inflate를 하는부분
        var v : View = LayoutInflater.from(parent.context).inflate(R.layout.example_item, parent, false)
        val exampleViewHolder = ExampleViewHolder(v, mListener).also { return it }
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        //여기서 직접 binding해서 해당 자식 view에다가 값을 넣어준다.
        val exampleItem : ExampleItem = mExampleList[position]
        //Log.e("lsl", "text1 is ${exampleItem.getText1()} and text2 is ${exampleItem.getText2()}" )
        holder.mTextView1!!.text = exampleItem.getText1()
        holder.mTextView2!!.text = exampleItem.getText2()
        holder.mImageView!!.setImageResource(exampleItem.getImageSource())
    }

    override fun getItemCount(): Int {
        return mExampleList.size
    }

    fun filterList(filteredList: ArrayList<ExampleItem>) {
        mExampleList = filteredList
        notifyDataSetChanged()
    }

}