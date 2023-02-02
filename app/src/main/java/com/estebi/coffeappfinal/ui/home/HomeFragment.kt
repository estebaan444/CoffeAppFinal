package com.estebi.coffeappfinal.ui.home

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.estebi.coffeappfinal.Login
import com.estebi.coffeappfinal.databinding.FragmentHomeBinding
import java.util.*

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        binding.button2.setOnClickListener {
            Intent(requireContext(), Login::class.java).also {
                startActivity(it)
                requireActivity().finish()
            }
        }

        binding.imageButton.setOnClickListener {
            dialeg()
        }

        binding.imageButton2.setOnClickListener {
            dialeg2()
        }

        return root
    }
    fun dialeg(){
        val builder = androidx.appcompat.app.AlertDialog.Builder(requireContext())
        with(builder)
        {
            setTitle("FAQS?")
            setMessage("Com puc pagar la comanda?" +
                    "\nQuan acabis la comanda podràs pagar-la amb el botó PAGAR" +
                    "\n\nCom puc veure el meu historial de comandes?" +
                    "\nEl teu historial de comandes es troba a la pestanya HISTORIAL" +
                    "\n\nCom puc fer logout?" +
                    "\nPots fer logout amb el botó LOGOUT")
                .setPositiveButton("OK",
                    DialogInterface.OnClickListener { dialog, id ->
                    })
            show()
        }
    }

    fun dialeg2(){
        val builder = androidx.appcompat.app.AlertDialog.Builder(requireContext())
        with(builder)
        {
            setTitle("CONTACTE")
            setMessage("TLF:" +
                    "\n666 678 642" +
                    "\n\nCORREU:" +
                    "\ninfo@coffeshop.cat")

                .setPositiveButton("DIRECCIÓ GOOGLE MAPS",
                    DialogInterface.OnClickListener { dialog, id ->
                        val uri: String = java.lang.String.format(
                            Locale.ENGLISH,
                            "geo:%f,%f",
                            41.979781,
                            2.82032
                        )
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
                        context.startActivity(intent)
                    })
                .setNegativeButton("CANCEL",
                    DialogInterface.OnClickListener { dialog, id ->
                    })
            show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}