package com.example.sqlitedemo

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlitedemo.databinding.CardItemsStdBinding

class StudentAdapter : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    private val TAG = "StudentAdapter"
    private var stdList: List<StudentModel> = ArrayList()

    private var onClickItem: ((StudentModel) -> Unit)? = null // lambda fuctions saved to a variable
    private var onClickDeleteItem: ((StudentModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_items_std, parent, false)
        return StudentViewHolder(view)
    }

    /*
    Binds the view for every recyclerView item --> holder
     */
    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val std = stdList.get(position)
        holder.bindView(std)
        holder.binding.ibtnDelete.setOnClickListener { onClickDeleteItem?.invoke(std) }

        /*
        Dit is een onClickListener die luistert naar clicks op items van de recyclerView.
        De listener is gezet op holder.
         */
        holder.itemView.setOnClickListener {
            /*
            In the variable onClickItem is een lambda functie opgeslagen die als parameter een student neemt en Unit returnt.
            Omdat de variable op null is gezet kun je niet onClickItem(std) gebruiken maar moet je:
            1.  onClickItem?.invoke(std)
            OF
            2. onClickItem?.let { it -> it(std) }
            gebruiken.
             */
            onClickItem?.let { it(std) }
        }
        Log.d(TAG, "onBindViewHolder: position is $position")
    }

    override fun getItemCount(): Int {
        return stdList.size
    }

    fun setStudentList(stdList: List<StudentModel>) {
        this.stdList = stdList
        notifyDataSetChanged()
    }

    /*
    setOnClickItem is een lambda functie met een parameter. Die parameter is van de type student.
    Dus wanneer je dit functie oproept, gebruik je als input een student.
     */
    fun setOnClickItem(callback: (StudentModel) -> Unit) {
        this.onClickItem = callback
    }

    fun setOnClickDeleteItem(callback: (StudentModel) -> Unit) {
        this.onClickDeleteItem = callback
    }


    /**
     *  INNER CLASS
     */
    inner class StudentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding: CardItemsStdBinding
        /*
        init is called after the primary constructor,
        here you can initialize or doe commutation for the constructor
         */
        init {
            binding = CardItemsStdBinding.bind(view)
        }

        // here you set the recyclerView items
        fun bindView(studentModel: StudentModel) {
            binding.tvId.text = studentModel.id.toString()
            binding.tvName.text = studentModel.name
            binding.tvEmail.text = studentModel.email
            Log.d(TAG, "bindView: called")
        }
    }


}