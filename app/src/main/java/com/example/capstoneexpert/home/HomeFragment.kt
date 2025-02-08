package com.example.capstoneexpert.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.capstoneexpert.R
import com.example.capstoneexpert.databinding.FragmentHomeBinding
import com.example.capstoneexpert.detail.DetailActivity
import com.example.core.data.Resource
import com.example.core.domain.model.Movie
import com.example.core.ui.MovieAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val movieAdapter = MovieAdapter()
            setupRecyclerView(movieAdapter)
            observeMovie(movieAdapter)

            movieAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }
        }
    }

    private fun setupRecyclerView(adapter: MovieAdapter) {
        with(binding.rvMovie) {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            this.adapter = adapter
        }
    }

    private fun observeMovie(adapter: MovieAdapter) {
        homeViewModel.movie.observe(viewLifecycleOwner) { response ->
            handleResponse(response, adapter)
        }
    }

    private fun handleResponse(response: Resource<List<Movie>>, adapter: MovieAdapter) {
        when (response) {
            is Resource.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
                binding.viewError.root.visibility = View.GONE
            }
            is Resource.Success -> {
                binding.progressBar.visibility = View.GONE
                adapter.submitList(response.data)
                binding.viewError.root.visibility = View.GONE
            }
            is Resource.Error -> {
                binding.progressBar.visibility = View.GONE
                binding.viewError.root.visibility = View.VISIBLE
                binding.viewError.tvError.text = response.message ?: getString(R.string.something_wrong)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}