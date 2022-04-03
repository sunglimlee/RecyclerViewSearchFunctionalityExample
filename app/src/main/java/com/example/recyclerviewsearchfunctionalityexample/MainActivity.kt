package com.example.recyclerviewsearchfunctionalityexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

//작업하는 순서.. 이건 머리에 외우고 있어야지..
// 1. create ExampleItem for ExampleList
// 2. ExampleAdapter, all view variables
// 3. ExampleViewHolder, all Views by Id, Create Function, Event Functions, additional functions
// 4. new Listener Interface
// 5. Activity : All Views by Id, ExampleList value, Adapter, implements Interface,

class MainActivity : AppCompatActivity() {
    private lateinit var mExampleList : ArrayList<ExampleItem>
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mExampleAdapter : ExampleAdapter
    private lateinit var mLayoutManager : LinearLayoutManager
    //private late init var mEditText_Insert : EditText
    //private late init var mEditText_Delete : EditText
    //private late init var mButton_Insert : Button
    //private late init var mButton_Delete : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createExampleList()
        buildRecyclerView()
        val editText = findViewById<EditText>(R.id.edittext)
        //괄호안에 대괄호를 넣어야 하는데.. 이런 사소한데서 틀리니깐 찾기가 힘들지..
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                filter(p0.toString())
            }
        })
        //mButton_Insert = findViewById(R.id.button_insert)
        //mEditText_Insert = findViewById<R.id.edittext_insert>()
        /*mButton_Insert.setOnClickListener {
            insertItem(Integer.parseInt(mEditText_Insert.text.toString()))
        }
        mButton_Delete = findViewById(R.id.button_delete)
        mEditText_Delete = findViewById<EditText>(R.id.edittext_delete)
        mButton_Delete.setOnClickListener {
            removeItem(Integer.parseInt(mEditText_Delete.text.toString()))
        } */
    }

    private fun filter(text: String) {
        val filteredList : ArrayList<ExampleItem> = ArrayList()
        for (item : ExampleItem in mExampleList) {
            if (item.getText1().lowercase().contains(text.lowercase())) {
                filteredList.add(item)
            }
        }
        mExampleAdapter.filterList(filteredList) //이렇게 해주는 이유가 뭘까? 일단 Adapter 에서 값을 모두 조정한는게 필요하다는 거지!
    }

    /* private fun insertItem(position: Int) {
        if (position <= mExampleList.size) {
            mExampleList.add(
                position,
                ExampleItem(R.drawable.ic_sun, "Line 100 added", "Line 101 added")
            )
            mExampleAdapter.notifyItemInserted(Integer.parseInt(mEditText_Insert.text.toString()))
        } else {
            Toast.makeText(this, "${mExampleList.size} 이하의 숫자를 입력하여 주십시오.", Toast.LENGTH_SHORT).show()
            mEditText_Insert.requestFocus()
        }
    }

    private fun removeItem(position : Int) {
        Toast.makeText(this, "열의 값은 $position 입니다.", Toast.LENGTH_SHORT).show()

        if (position < mExampleList.size) {
            mExampleList.removeAt(position)
            //Adapter 가 데이터를 다 관리하니깐.. notifyItemInserted 는 Adapter 에 있는게 맞지..
            mExampleAdapter.notifyItemRemoved(position)
        } else {
            Toast.makeText(this, "${mExampleList.size - 1} 이하의 숫자를 입력하여 주십시오.", Toast.LENGTH_SHORT).show()
            mEditText_Delete.requestFocus()
        }
    } */

    private fun buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerview)
        mRecyclerView.setHasFixedSize(true)
        mLayoutManager = LinearLayoutManager(this)
        mExampleAdapter = ExampleAdapter(mExampleList)
        mRecyclerView.layoutManager = mLayoutManager //왜 이걸 안넣어놨지???
        mRecyclerView.adapter = mExampleAdapter
        mExampleAdapter.setItemClickListener(object : ExampleAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                super.onItemClick(position)
                mExampleList[position].also {it.changeText1("Clicked")}
                mExampleAdapter.notifyItemChanged(position)
            }

            override fun onDeleteClick(position: Int) {
                super.onItemClick(position)
                removeItem(position)
            }
        })
    }

    private fun removeItem(position: Int) {
        if (position < mExampleList.size) {
            mExampleList.removeAt(position)
            mExampleAdapter.notifyItemRemoved(position)
        } else {
            Toast.makeText(this, "${mExampleList.size - 1} 이하의 숫자를 입력하여 주십시오. ", Toast.LENGTH_SHORT).show()
            //mEditText_Delete.requestFocus()
        }
    }

    private fun createExampleList() {
        mExampleList = ArrayList()
        mExampleList.add(0, ExampleItem(R.drawable.ic_android, "Line1", "Line2"))
        mExampleList.add(1, ExampleItem(R.drawable.ic_audio, "Line3", "Line4"))
        mExampleList.add(2, ExampleItem(R.drawable.ic_sun, "Line5", "Line6"))
        mExampleList.add(0, ExampleItem(R.drawable.ic_android, "Line1", "Line2"))
        mExampleList.add(1, ExampleItem(R.drawable.ic_audio, "Line3", "Line4"))
        mExampleList.add(2, ExampleItem(R.drawable.ic_sun, "Line5", "Line6"))
        mExampleList.add(0, ExampleItem(R.drawable.ic_android, "Line1", "Line2"))
        mExampleList.add(1, ExampleItem(R.drawable.ic_audio, "Line3", "Line4"))
        mExampleList.add(2, ExampleItem(R.drawable.ic_sun, "Line5", "Line6"))
        mExampleList.add(0, ExampleItem(R.drawable.ic_android, "Line1", "Line2"))
        mExampleList.add(1, ExampleItem(R.drawable.ic_audio, "Line3", "Line4"))
        mExampleList.add(2, ExampleItem(R.drawable.ic_sun, "Line5", "Line6"))

    }
}