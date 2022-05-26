package jeffliu.nestedrecyclerview

import android.os.Bundle
import android.os.StrictMode
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jeffliu.nestedrecyclerview.ui.theme.MyApplicationxxxTheme
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        turnOnStrictMode()

        val subList1 = ArrayList<Int>()
        for (i in 0..49) {
            subList1.add(i)
        }
        val list = ArrayList<ArrayList<Int>>()
        for (i in 0..49) {
            list.add(subList1)
        }

        setContent {
            MyApplicationxxxTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NestedLists(list)
                }
            }
        }
    }

    private fun turnOnStrictMode() {
        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder().detectAll()
                .penaltyLog().penaltyFlashScreen().build()
        )
        StrictMode.setVmPolicy(
            StrictMode.VmPolicy.Builder().detectAll()
                .penaltyLog().build()
        )
    }
}


@Composable
fun NestedLists(nestedList: List<List<Int>>) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(nestedList) { item ->
            ComposeRow(item)
        }
    }
}

@Composable
fun ComposeRow(list: List<Int>) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(list) { item ->
            Surface(
                shadowElevation = 8.dp,
                shape = RoundedCornerShape(8.dp),
            ) {
                Text(text = item.toString(), modifier = Modifier.padding(16.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val subList1 = ArrayList<Int>()
    for (i in 0..49) {
        subList1.add(i)
    }
    val list = ArrayList<ArrayList<Int>>()
    for (i in 0..49) {
        list.add(subList1)
    }
    MyApplicationxxxTheme {
        NestedLists(list)
    }
}