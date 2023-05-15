package kr.marko.photobook.presentation.sketch

import android.os.Bundle
import android.util.Log
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import kr.marko.photobook.domain.project.TestProjectUseCase
import kr.marko.photobook.presentation.R
import kr.marko.photobook.presentation.databinding.FragmentSketchBinding
import javax.inject.Inject

@AndroidEntryPoint
class SketchFragment : Fragment(R.layout.fragment_sketch) {

    private val binding: FragmentSketchBinding by viewBinding()

    @Inject
    lateinit var hoi: TestProjectUseCase

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("#Marko", "hoi : $hoi")
    }


}