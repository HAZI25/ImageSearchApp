package com.example.imagesearch.presentation.gallery

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.imagesearch.R
import com.example.imagesearch.databinding.FragmentGalleryBinding
import com.example.imagesearch.presentation.ImageSearchApp
import com.example.imagesearch.presentation.ViewModelFactory
import com.example.imagesearch.presentation.gallery.adapter.UnsplashPhotoAdapter
import com.example.imagesearch.presentation.gallery.adapter.UnsplashPhotoLoadStateAdapter
import com.example.imagesearch.presentation.model.UnsplashPhoto
import kotlinx.coroutines.launch
import javax.inject.Inject

class GalleryFragment : Fragment(), UnsplashPhotoAdapter.OnItemClickListener {

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    private val component by lazy {
        (activity?.application as ImageSearchApp).component
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: GalleryViewModel

    private val photoAdapter: UnsplashPhotoAdapter by lazy {
        UnsplashPhotoAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        component.inject(this)

        _binding = FragmentGalleryBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        setupRecyclerView()
        observeViewModel()
        setupGalleryMenu()
    }

    private fun setupGalleryMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_gallary, menu)

                val searchItem = menu.findItem(R.id.action_search)
                val searchView = searchItem.actionView as SearchView

                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        if (query != null) {
                            binding.recyclerView.scrollToPosition(0)
                            viewModel.searchPhotos(query)
                            searchView.clearFocus()
                        }
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        return true
                    }
                })
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return true
            }
        }, viewLifecycleOwner)
    }

    private fun observeViewModel() {
        lifecycleScope.launch() {
            viewModel.photos.collect {
                photoAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }
    }

    private fun setupRecyclerView() {

        with(binding) {
            with(recyclerView) {
                adapter = photoAdapter.withLoadStateHeaderAndFooter(
                    header = UnsplashPhotoLoadStateAdapter { photoAdapter.retry() },
                    footer = UnsplashPhotoLoadStateAdapter { photoAdapter.retry() }
                )
                setHasFixedSize(true)
                itemAnimator = null
            }

            buttonRetry.setOnClickListener {
                photoAdapter.retry()
            }
        }

        photoAdapter.addLoadStateListener { loadState ->
            with(binding) {
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
                buttonRetry.isVisible = loadState.source.refresh is LoadState.Error
                textViewError.isVisible = loadState.source.refresh is LoadState.Error

                if (loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    photoAdapter.itemCount < 1
                ) {
                    recyclerView.isVisible = false
                    textViewEmpty.isVisible = true
                } else {
                    textViewEmpty.isVisible = false
                }
            }
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)[GalleryViewModel::class.java]
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(photo: UnsplashPhoto) {
        val action = GalleryFragmentDirections.actionGalleryFragmentToDetailsFragment(photo)
        findNavController().navigate(action)
    }
}