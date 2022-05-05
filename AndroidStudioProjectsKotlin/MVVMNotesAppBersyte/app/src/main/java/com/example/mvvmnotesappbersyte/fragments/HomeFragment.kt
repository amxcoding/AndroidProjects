package com.example.mvvmnotesappbersyte.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mvvmnotesappbersyte.MainActivity
import com.example.mvvmnotesappbersyte.R
import com.example.mvvmnotesappbersyte.adapter.NoteAdapter
import com.example.mvvmnotesappbersyte.databinding.FragmentHomeBinding
import com.example.mvvmnotesappbersyte.model.Note
import com.example.mvvmnotesappbersyte.viewmodel.NoteViewModel


class HomeFragment : Fragment(R.layout.fragment_home),
    SearchView.OnQueryTextListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var noteAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // setup options menu
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.home_menu, menu)

        val mMenuSearch = menu.findItem(R.id.miSearch).actionView as SearchView
        mMenuSearch.isSubmitButtonEnabled = false
        mMenuSearch.setOnQueryTextListener(this)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteViewModel = (activity as MainActivity).noteViewModel
        setupRecyclerView()

        binding.fabAddNote.setOnClickListener { mView ->
            // same as below
//            val action = HomeFragmentDirections.actionHomeFragmentToNewNoteFragment()
//            Navigation.findNavController(view).navigate(action)
            mView.findNavController().navigate(R.id.action_homeFragment_to_newNoteFragment)

        }
    }

    private fun setupRecyclerView() {
        noteAdapter = NoteAdapter()

        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(
                2,
                StaggeredGridLayoutManager.VERTICAL
            )

            setHasFixedSize(true)
            adapter = noteAdapter
        }

        activity?.let {
            noteViewModel.getAllNotes().observe(viewLifecycleOwner, { note ->
                noteAdapter.differ.submitList(note)
                updateUI(note)
            })
        }
    }

    private fun updateUI(note: List<Note>) {

        if (note.isNotEmpty()) {
            binding.recyclerView.visibility = View.VISIBLE
            binding.cardView.visibility = View.GONE
        } else {
            binding.recyclerView.visibility = View.GONE
            binding.cardView.visibility = View.VISIBLE
        }
    }


    // because of memory use and leaks it is a good practice to set binding to null if not needed inside fragments
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            searchNotes(query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            searchNotes(newText)
        }
        return true
    }

    private fun searchNotes(query: String?) {
        val searchQuery = "%$query"
        noteViewModel.searchNote(searchQuery).observe(this, { list ->
            noteAdapter.differ.submitList(list)
        })
    }

}
