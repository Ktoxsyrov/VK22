package com.example.ht1

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.ht1.databinding.FragmentRecyclerBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONObject.NULL


class RecyclerFragment : Fragment() {
    lateinit var binding: FragmentRecyclerBinding
    private val viewModel: ItemsViewModel by viewModels {
        ItemsViewModelFactory(this)
    }
    lateinit var adapter: RecyclerAdapter
    lateinit var recycler: RecyclerView
    lateinit var addButton: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecyclerBinding.inflate(inflater)

        adapter = RecyclerAdapter()
        recycler = binding.recycler
        addButton = binding.addFab


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val columns: Int = when (this.resources.configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> resources.getInteger(R.integer.landscape_cols)
            else -> resources.getInteger(R.integer.portrait_cols)
        }

        viewModel.count.observe(viewLifecycleOwner) {
            adapter.setCount(it)
        }
        recycler.layoutManager = GridLayoutManager(requireContext(), columns)

        recycler.adapter = adapter
        addButton.setOnClickListener {
            viewModel.addItem()
            viewModel.saveList()
            recycler.scrollToPosition(adapter.getCount() - 1)
        }
        recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy < 0) {
                    addButton.hide()
                } else if (dy > 0)
                    addButton.show()
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }
}