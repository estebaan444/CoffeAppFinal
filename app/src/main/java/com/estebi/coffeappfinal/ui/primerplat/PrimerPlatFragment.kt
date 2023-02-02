package com.estebi.coffeappfinal.ui.primerplat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.estebi.coffeappfinal.SharedViewModel.SharedViewModel.Companion.listOfPrices
import com.estebi.coffeappfinal.SharedViewModel.SharedViewModel.Companion.listOfProduct
import com.estebi.coffeappfinal.databinding.FragmentPrimerPlatBinding
import com.estebi.coffeappfinal.model.Pastes
import com.estebi.coffeappfinal.repositori.Repositori
import com.estebi.coffeappfinal.ui.primerplat.adapter.PastesAdapter
import kotlinx.coroutines.launch

class PrimerPlatFragment : Fragment() {

    private var _binding: FragmentPrimerPlatBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val primerPlatViewModel =
            ViewModelProvider(this).get(PrimerPlatViewModel::class.java)

        _binding = FragmentPrimerPlatBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch{
            val pastesList = Repositori.initializeDB(requireContext()).coffeShopDao().getAllPastes()
            var rv = binding.recyclerview
            var adapter = PastesAdapter(pastesList)
            rv.layoutManager = LinearLayoutManager(requireContext())
            rv.setHasFixedSize(true)
            rv.adapter = adapter

            adapter.setOnItemClickListener(object : PastesAdapter.onItemClickListener {
                override fun onItemClick(pastes: Pastes) {
                    listOfProduct.add(pastes.pastaName)
                    listOfPrices.add(pastes.pastaPrice)
                    Toast.makeText(requireContext(), "Has seleccionat ${pastes.pastaName}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}