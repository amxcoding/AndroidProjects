package com.example.notetasklayout_v1.fragments.note

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.example.notetasklayout_v1.R
import com.example.notetasklayout_v1.databinding.FragmentNotesBinding

class NotesFragment : Fragment(R.layout.fragment_notes) {

    private var _binding: FragmentNotesBinding? = null // to avoid memory leaks initialize like this
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
        Report that this fragment would like to participate in populating the options menu by receiving a call
        to onCreateOptionsMenu and related methods.
         */
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNotesBinding.inflate(inflater, container, false) // view binding here
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // set clicklistener and initialize vals here
    }

    // inflate options menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.note_menu, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return if (item.itemId == R.id.noteBooksFragment) {
//            val action = NotesFragmentDirections.actionNotesFragmentToNoteBooksFragment() // safeargs navigation
//            findNavController().navigate(action)
//            return true
//        } else {
//            Toast.makeText(requireContext(), "${item.itemId}", Toast.LENGTH_SHORT).show()
//            super.onOptionsItemSelected(item)
//        }

        return item.onNavDestinationSelected(findNavController()) // if the ids of the menu item match --> you will navigate to the fragment
                || super.onOptionsItemSelected(item) // in case it couldn't navigate to it
    }


    // set binding to null to avoid memory leaks
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}