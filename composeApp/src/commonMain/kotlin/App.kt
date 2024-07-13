import HomeViewModel.HomeViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.seiko.imageloader.rememberImagePainter
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {

        AppContent(homeViewModel = HomeViewModel())

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppContent(homeViewModel: HomeViewModel) {

    val products = homeViewModel.products.collectAsState()

    BoxWithConstraints {
        val scope = this
        val maxWidth = scope.maxWidth

        var cols = 2
        var modifier = Modifier.fillMaxWidth()

        if (maxWidth > 840.dp) {
            cols = 3
            modifier = Modifier.widthIn(max = 1080.dp)
        }

        val scrollState = rememberLazyGridState()
        val coroutineScope = rememberCoroutineScope()

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {


            LazyVerticalGrid(
                columns = GridCells.Fixed(cols),
                state = scrollState,
                contentPadding = PaddingValues(16.dp),
                modifier = Modifier.draggable(
                    orientation = Orientation.Vertical,
                    state = rememberDraggableState { delta ->
                        coroutineScope.launch {
                            scrollState.scrollBy(delta)
                        }

                    })
            ) {

                item(span = { GridItemSpan(cols) }) {

                    Column {
                        SearchBar(
                            modifier = Modifier.fillMaxWidth(),
                            query = "",
                            onQueryChange = {},
                            onSearch = {},
                            active = false,
                            onActiveChange = {},
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = null
                                )
                            },
                            placeholder = { Text("Search Products") }
                        ) {}
                        Spacer(Modifier.height(16.dp))
                    }


                }

                items(
                    items = products.value,
                    key = { products -> products.id.toString() }) { product ->


                    Card(
                        shape = RoundedCornerShape(15.dp),
                        modifier = Modifier.padding(8.dp).fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {

                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            val painter = rememberImagePainter(url = product.images!![0].toString())
                            Image(
                                painter,
                                modifier = Modifier.height(130.dp).padding(8.dp).fillMaxWidth(),
                                contentDescription = product.title,
                                contentScale = ContentScale.Fit

                                )


                            Text(
                                product.title.toString(),
                                maxLines = 2,
                                textAlign = TextAlign.Start,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.padding(horizontal = 16.dp).heightIn(40.dp)
                                    .fillMaxWidth()
                            )

                            Spacer(Modifier.height(8.dp))

                            Text(
                                "${product.price.toString()} PKR",
                                maxLines = 2,
                                textAlign = TextAlign.Start,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.padding(horizontal = 16.dp).heightIn(40.dp)
                                    .fillMaxWidth()
                            )


                        }

                    }

                }

            }

        }


    }


}