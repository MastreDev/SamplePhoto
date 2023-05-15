package kr.marko.photobook.presentation.gallery

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.marko.photobook.presentation.R
import kr.marko.photobook.presentation.databinding.FragmentGalleryBinding

@AndroidEntryPoint
class GalleryFragment : Fragment(R.layout.fragment_gallery) {

    private val binding: FragmentGalleryBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.tvHello.setOnClickListener {
            findNavController().navigate(GalleryFragmentDirections.actionGalleryFragmentToSketchFragment())
        }
    }
}