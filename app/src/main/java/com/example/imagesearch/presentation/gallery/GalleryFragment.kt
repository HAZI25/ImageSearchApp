package com.example.imagesearch.presentation.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.imagesearch.common.observe
import com.example.imagesearch.databinding.FragmentGalleryBinding
import com.example.imagesearch.presentation.ImageSearchApp
import com.example.imagesearch.presentation.ViewModelFactory
import com.example.imagesearch.presentation.gallery.adapter.UnsplashPhotoAdapter
import javax.inject.Inject

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    private val component by lazy {
        (activity?.application as ImageSearchApp).component
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: GalleryViewModel

    private val photoAdapter: UnsplashPhotoAdapter by lazy {
        UnsplashPhotoAdapter()
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
    }

    private fun observeViewModel() {
        viewModel.photos.observe(viewLifecycleOwner) {
            photoAdapter.submitData(it)
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.adapter = photoAdapter
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)[GalleryViewModel::class.java]
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}