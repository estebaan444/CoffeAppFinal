package com.estebi.coffeappfinal.ui.pagarcomanda

import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.estebi.coffeappfinal.R
import com.estebi.coffeappfinal.SharedViewModel.SharedViewModel.Companion.listOfPrices
import com.estebi.coffeappfinal.SharedViewModel.SharedViewModel.Companion.listOfProduct
import com.estebi.coffeappfinal.SharedViewModel.SharedViewModel.Companion.sumTotal
import com.estebi.coffeappfinal.SharedViewModel.SharedViewModel.Companion.userName
import com.estebi.coffeappfinal.databinding.FragmentSlideshowBinding
import com.estebi.coffeappfinal.model.History
import com.estebi.coffeappfinal.repositori.Repositori
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class PagarComandaFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val PagarComandaViewModel =
            ViewModelProvider(this).get(PagarComandaViewModel::class.java)

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val listView: ListView = binding.recibo
        PagarComandaViewModel.text.observe(viewLifecycleOwner) {
            listView.findViewById<ListView>(R.id.recibo)
        }

        val textView: TextView = binding.total
        PagarComandaViewModel.text.observe(viewLifecycleOwner) {
            textView.findViewById<TextView>(R.id.total)
        }

        val butonPagar: Button = binding.PAGAR
        PagarComandaViewModel.text.observe(viewLifecycleOwner) {
            butonPagar.findViewById<Button>(R.id.PAGAR)
        }

        textView.setText(sumTotal().toString() + "€")

        val arrayAdapter: ArrayAdapter<*>
        var mListView = binding.recibo
        arrayAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1, listOfProduct
        )
        mListView.adapter = arrayAdapter
        printCaffes()

        butonPagar.setOnClickListener {
            if(listOfProduct.isEmpty()){
                Toast.makeText(requireContext(), "No hi ha cap producte a la comanda", Toast.LENGTH_SHORT)
                    .show()
            }else{
                addHistoryAndPay()
            }
        }

        binding.cancelarComanda.setOnClickListener {
            dialegCancelarComanda()
        }
        return root
    }

    fun printCaffes() {
        for (item in listOfProduct) {
            println(item)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addHistoryAndPay(){
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val formatted = current.format(formatter)
        val totalPrice = sumTotal()
        Repositori.insertHistory(requireContext(), History(userName, formatted, totalPrice))
        listOfProduct.clear()
        listOfPrices.clear()
        Toast.makeText(requireContext(), "Gràcies per la seva comanda!", Toast.LENGTH_SHORT)
            .show()
        findNavController().navigate(R.id.navigation_home)
    }

    fun dialegCancelarComanda() {
        val builder = androidx.appcompat.app.AlertDialog.Builder(requireContext())
        with(builder)
        {
            setTitle("Segur que vols cancelar la comanda?")
                .setPositiveButton("OK",
                    DialogInterface.OnClickListener { dialog, id ->
                        listOfProduct.clear()
                        listOfPrices.clear()
                        findNavController().navigate(R.id.navigation_home)
                        Toast.makeText(requireContext(), "Comanda cancel·lada", Toast.LENGTH_SHORT)
                            .show()
                    })
            show()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}