import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ColorGridScreen(colorViewModel: ColorViewModel = viewModel()) {
    val colors by colorViewModel.colors.collectAsState()
    val tapCounts by colorViewModel.tapCounts.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        Row(Modifier.fillMaxWidth()) {
            ColorSquare(color = colors[0], count = tapCounts[0], onClick = { colorViewModel.changeColor(0) })
            Spacer(modifier = Modifier.width(16.dp))
            ColorSquare(color = colors[1], count = tapCounts[1], onClick = { colorViewModel.changeColor(1) })
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(Modifier.fillMaxWidth()) {
            ColorSquare(color = colors[2], count = tapCounts[2], onClick = { colorViewModel.changeColor(2) })
            Spacer(modifier = Modifier.width(16.dp))
            ColorSquare(color = colors[3], count = tapCounts[3], onClick = { colorViewModel.changeColor(3) })
        }
    }
}

@Composable
fun ColorSquare(color: Color, count: Int, onClick: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(100.dp)
            .background(color)
            .clickable { onClick() }
    ) {
        Text(text = count.toString(), fontSize = 24.sp, color = Color.White)
    }
}
