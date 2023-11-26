package com.example.contactinfo.viewUi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.contactinfo.R
import com.example.contactinfo.databinding.CardViewBinding
import com.example.contactinfo.room.UserEntity

class recyclerviewAdapter(private val user:List<UserEntity>,
                          private val clickListener: (UserEntity) -> Unit
):RecyclerView.Adapter<recyclerviewAdapter.myViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
       val layoutInflater=LayoutInflater.from(parent.context)
        val binding:CardViewBinding=DataBindingUtil.inflate(layoutInflater, R.layout.card_view,parent,false)
        return myViewHolder(binding)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        holder.bind(user[position],clickListener)
    }

    override fun getItemCount(): Int {
        return user.size
    }

    class myViewHolder(val binding:CardViewBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(userEntity: UserEntity, clickListener: (UserEntity) -> Unit){
            binding.tvname.text=userEntity.name
            binding.tvemail.text=userEntity.email

            binding.linearlayout.setOnClickListener(){
                clickListener(userEntity)
            }
        }
    }


}