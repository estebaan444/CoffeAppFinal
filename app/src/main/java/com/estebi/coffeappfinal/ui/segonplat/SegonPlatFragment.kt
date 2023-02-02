package com.estebi.coffeappfinal.ui.segonplat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.estebi.coffeappfinal.SharedViewModel.SharedViewModel
import com.estebi.coffeappfinal.SharedViewModel.SharedViewModel.Companion.listOfPrices
import com.estebi.coffeappfinal.SharedViewModel.SharedViewModel.Companion.listOfProduct
import com.estebi.coffeappfinal.databases.CoffeShopDatabase
import com.estebi.coffeappfinal.databinding.FragmentSegonPlatBinding
import com.estebi.coffeappfinal.model.Coffes
import com.estebi.coffeappfinal.repositori.Repositori
import com.estebi.coffeappfinal.ui.segonplat.adapter.CoffeAdapter
import kotlinx.coroutines.launch

class SegonPlatFragment : Fragment() {

    private var _binding: FragmentSegonPlatBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSegonPlatBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onResume() {
        super.onResume()

        lifecycleScope.launch{


            val coffeList = Repositori.initializeDB(requireContext()).coffeShopDao().getAllCoffes()

            var rv = binding.recyclerview
            var adapter = CoffeAdapter(coffeList)
            rv.layoutManager = LinearLayoutManager(requireContext())
            rv.setHasFixedSize(true)
            rv.adapter = adapter

            adapter.setOnItemClickListener(object : CoffeAdapter.onItemClickListener {
                override fun onItemClick(coffes: Coffes) {
                    listOfProduct.add(coffes.coffeName)
                    listOfPrices.add(coffes.coffePrice)
                    Toast.makeText(requireContext(), "Has seleccionat ${coffes.coffeName}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}