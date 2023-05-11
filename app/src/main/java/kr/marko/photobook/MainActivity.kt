package kr.marko.photobook

import android.os.Bundle
import android.util.Log
import android.viewbinding.library.activity.viewBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.marko.photobook.databinding.ActivityMainBinding
import kr.marko.photobook.home.HomeFragmentDirections
import kr.marko.photobook.presentation.protocol.EditorParams
import kr.marko.photobook.presentation.util.assistedViewModel
import org.orbitmvi.orbit.viewmodel.observe
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding()

    @Inject
    lateinit var vmFactory: MainViewModel.Factory

    private val vm: MainViewModel by assistedViewModel { vmFactory.create(MainViewState(foo = "Main Init !")) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("#Marko", "binding : ${vm.hashCode()}, $binding")
        vm.observe(lifecycleOwner = this, sideEffect = ::handleViewEffect)
    }

    private fun handleViewEffect(viewEffect: MainViewEffect) {
        when (viewEffect) {
            MainViewEffect.NavEditor -> {
                findNavController(R.id.nav_host_fragment_container).navigate(HomeFragmentDirections.actionHomeFragmentToEditorGraph(EditorParams(null)))
            }
        }
    }

}