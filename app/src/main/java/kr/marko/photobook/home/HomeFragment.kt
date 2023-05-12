package kr.marko.photobook.home

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.navGraphViewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.marko.photobook.MainViewModel
import kr.marko.photobook.R
import kr.marko.photobook.databinding.FragmentHomeBinding

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding<FragmentHomeBinding>()

    private val vm by activityViewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnCreateProduct.setOnClickListener {
            vm.onClickCreateProduct()
        }
        binding.btnLoadProject.setOnClickListener {
            vm.onClickLoadProject()
        }
    }
}