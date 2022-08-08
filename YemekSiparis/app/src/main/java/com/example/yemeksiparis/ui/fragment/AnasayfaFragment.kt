package com.example.yemeksiparis.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yemeksiparis.R
import com.example.yemeksiparis.data.entity.Yemekler
import com.example.yemeksiparis.data.repo.YemeklerDaoRepository
import com.example.yemeksiparis.databinding.FragmentAnasayfaBinding
import com.example.yemeksiparis.retrofit.YemeklerDao
import com.example.yemeksiparis.ui.adapter.YemeklerAdapter
import com.example.yemeksiparis.ui.viewmodel.AnasayfaFragmentViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnasayfaFragment : Fragment(),SearchView.OnQueryTextListener {
    private lateinit var tasarim:FragmentAnasayfaBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        tasarim = FragmentAnasayfaBinding.inflate(inflater,container,false)

        tasarim.toolbarAnasayfa.title = "Starvy"
        tasarim.rv.layoutManager = LinearLayoutManager(requireContext())

        val yemeklerListesi = ArrayList<Yemekler>()
        val y1 = Yemekler(1,"Hamburger","burger",72)
        val y2 = Yemekler(2,"Ayvalık Tostu","ayvalik",49)
        val y3 = Yemekler(3,"Pizza","pizza",219)
        val y4 = Yemekler(4,"Sosisli","hotdog",24)
        val y5 = Yemekler(5,"Domates Çorbası","soup",29)
        val y6 = Yemekler(6,"Cola","cola",14)

        yemeklerListesi.add(y1)
        yemeklerListesi.add(y2)
        yemeklerListesi.add(y3)
        yemeklerListesi.add(y4)
        yemeklerListesi.add(y5)
        yemeklerListesi.add(y6)

        val adapter = YemeklerAdapter(requireContext(),yemeklerListesi)
        tasarim.rv.adapter = adapter



        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_cikis -> {
                FirebaseAuth.getInstance().signOut()
                Navigation.findNavController(requireView()).navigate(R.id.anasayfaLoginGecis)
                Snackbar.make(requireView(), "Çıkış Başarılı !", Snackbar.LENGTH_SHORT).show()

                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        return true
    }
}