package com.example.yemeksiparis.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.yemeksiparis.R
import com.example.yemeksiparis.data.entity.Yemekler
import com.example.yemeksiparis.databinding.CardTasarimAnasayfaBinding
import com.example.yemeksiparis.ui.fragment.AnasayfaFragmentDirections
import com.example.yemeksiparis.ui.viewmodel.AnasayfaFragmentViewModel
import com.google.android.material.snackbar.Snackbar


class YemeklerAdapter(var mContext:Context, var yemeklerListesi:List<Yemekler>) : RecyclerView.Adapter<YemeklerAdapter.CardTasarimTutucu>() {
    inner class CardTasarimTutucu(tasarim:CardTasarimAnasayfaBinding) : RecyclerView.ViewHolder(tasarim.root){
        var tasarim:CardTasarimAnasayfaBinding
        init {
            this.tasarim = tasarim
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val layoutInflater = LayoutInflater.from(mContext)
        val tasarim = CardTasarimAnasayfaBinding.inflate(layoutInflater,parent,false)
        return CardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val yemek = yemeklerListesi.get(position)
        val t = holder.tasarim


        t.imageViewYemekResim.setImageResource(mContext.resources.getIdentifier(yemek.yemek_resim_adi,"drawable", mContext.packageName))
        t.textViewYemekAdi.text = yemek.yemek_adi
        t.textViewYemekUcret.text = "${yemek.yemek_fiyat} ₺"
        //t.textViewYemekIcindekiler.text = yemek.yemekIcindeki


        t.imageAddButton.setOnClickListener {
            val gecis = AnasayfaFragmentDirections.detayGecis(yemek = yemek)
            Navigation.findNavController(it).navigate(gecis)
            //Snackbar.make(it,"${yemek.yemek_adi} sepete eklendi.", Snackbar.LENGTH_SHORT).show()
        }



        t.cardViewYemek.setOnClickListener {
            val gecis = AnasayfaFragmentDirections.detayGecis(yemek = yemek)
            Navigation.findNavController(it).navigate(gecis)
        }
    }

    override fun getItemCount(): Int {
        return yemeklerListesi.size
    }
}