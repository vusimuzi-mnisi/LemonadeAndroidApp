package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme
import kotlin.random.Random


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeScreen()
                }
            }
        }
    }
}



@Composable
fun LemonadeScreen() {
    Column  {
        var appState by remember { mutableIntStateOf(1) }
        var lemonTapCount by remember { mutableIntStateOf(0) }
        val randomNum = Random.nextInt(2,5)
        Text(
            text = "Lemonade",
            color = Color.Black,
            modifier = Modifier
                .background(Color.Yellow)
                .fillMaxWidth()
                .padding(top = 40.dp, bottom = 8.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )


        when (appState) {
            1 -> LemonadeScreenImageAndText(

                image = R.drawable.lemon_tree,
                imageDesc = R.string.lemonTree,
                text = R.string.treeText,
                onTap = {appState = 2}
            )

            2 -> LemonadeScreenImageAndText(


                image = R.drawable.lemon_squeeze,
                imageDesc =  R.string.lemon,
                text = R.string.lemonText,
                onTap = {
                    lemonTapCount++
                    if(lemonTapCount==randomNum){
                        appState = 3
                    }
                }
            )

            3 -> LemonadeScreenImageAndText(

                image = R.drawable.lemon_drink,
                imageDesc = R.string.lemonade,
                text = R.string.lemonadeText,
                onTap = {appState = 4}
            )

            4 -> LemonadeScreenImageAndText(

                image = R.drawable.lemon_restart,
                imageDesc = R.string.emptyGlass,
                text = R.string.glassText,
                onTap = {appState = 1}
            )
        }
        lemonTapCount = 0
    }
    }


@Composable
fun LemonadeScreenImageAndText(
        modifier: Modifier = Modifier,

        image: Int,
        imageDesc:Int,
        text: Int,
        onTap:()->Unit

    ) {
        Column(

            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = image),
                contentDescription = stringResource(id = imageDesc),
                modifier
                    .clickable(onClick = onTap)
                    .width(250.dp)
                    .padding(16.dp)
                    .background(
                        colorResource(id = R.color.backgroundGreen),
                        shape = RoundedCornerShape(16.dp)
                    )


            )
            Text(
                text = stringResource(id = text)
            )

        }
    }

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LemonadeTheme {
        LemonadeScreen()
    }
}