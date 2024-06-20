import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ColorViewModel : ViewModel() {
    private val originalColors = listOf(Color.Gray, Color.Gray, Color.Gray, Color.Gray)
    private val _colors = MutableStateFlow(originalColors)
    val colors: StateFlow<List<Color>> get() = _colors

    private val _tapCounts = MutableStateFlow(listOf(0, 0, 0, 0))
    val tapCounts: StateFlow<List<Int>> get() = _tapCounts

    private var currentRedIndex: Int? = null

    fun changeColor(index: Int) {
        val newColors = originalColors.toMutableList()

        // Reset the previous red square to its original color
        currentRedIndex?.let {
            newColors[it] = originalColors[it]
        }

        // Set the clicked square to red
        newColors[index] = Color.Red
        currentRedIndex = index

        _colors.value = newColors

        // Update the tap count
        val newTapCounts = _tapCounts.value.toMutableList()
        newTapCounts[index] += 1
        _tapCounts.value = newTapCounts
    }
}
