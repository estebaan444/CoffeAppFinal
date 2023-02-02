package com.estebi.coffeappfinal.ui.historial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.estebi.coffeappfinal.SharedViewModel.SharedViewModel
import com.estebi.coffeappfinal.SharedViewModel.SharedViewModel.Companion.userName
import com.estebi.coffeappfinal.databinding.FragmentHistorialBinding
import com.estebi.coffeappfinal.model.History
import com.estebi.coffeappfinal.model.Pastes
import com.estebi.coffeappfinal.repositori.Repositori
import com.estebi.coffeappfinal.ui.historial.adapter.HistoryAdapter
import com.estebi.coffeappfinal.ui.primerplat.adapter.PastesAdapter
import kotlinx.coroutines.launch

class HistorialFragment : Fragment() {

    private var _binding: FragmentHistorialBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val historialViewModel =
            ViewModelProvider(this).get(HistorialViewModel::class.java)

        _binding = FragmentHistorialBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch{
            val historyList = Repositori.initializeDB(requireContext()).coffeShopDao().getHistoryByCurrentUser(userName)
            var rv = binding.recyclerView3
            var adapter = HistoryAdapter(historyList)
            rv.layoutManager = LinearLayoutManager(requireContext())
            rv.setHasFixedSize(true)
            rv.adapter = adapter

            adapter.setOnItemClickListener(object : HistoryAdapter.onItemClickListener {
                override fun onItemClick(history: History) {
                    Toast.makeText(requireContext(), "Dia de la comanda seleccionada: ${history.date}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}