package kr.marko.photobook.presentation.landing

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import kr.marko.photobook.presentation.R
import kr.marko.photobook.presentation.util.assistedViewModel
import org.orbitmvi.orbit.viewmodel.observe
import javax.inject.Inject

@AndroidEntryPoint
class PrepareEditorFragment : Fragment(R.layout.fragment_prepare_editor) {

    private val args: PrepareEditorFragmentArgs by navArgs()

    @Inject
    lateinit var vmFactory: PrepareEditorViewModel.Factory

    private val vm: PrepareEditorViewModel by assistedViewModel {
        vmFactory.create(PrepareEditorViewState(editorParams = args.editorParams))
    }

    //NavArgs 를 통해 전달받은 Data로 Gallery 로 갈지 Page 로 갈지 분기 처리 한다.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        vm.observe(viewLifecycleOwner, sideEffect = ::handleViewEffect)
    }

    private fun handleViewEffect(viewEffect: PrepareEditorViewEffect) {
        when (viewEffect) {
            PrepareEditorViewEffect.NavGallery -> findNavController().navigate(PrepareEditorFragmentDirections.actionPrepareEditorFragmentToGalleryFragment())
            PrepareEditorViewEffect.NavSketch -> findNavController().navigate(PrepareEditorFragmentDirections.actionPrepareEditorFragmentToSketchFragment())
        }
    }

}