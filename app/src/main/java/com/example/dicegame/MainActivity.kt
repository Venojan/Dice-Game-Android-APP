package com.example.dicegame


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiceGame()

        }
    }
}

@Composable
fun DiceGame(){
    Image(
        painter = painterResource(id = R.drawable.bg_menu),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop

    )
    var screen by remember { mutableStateOf("Menu") }
    when(screen){
        "Menu" -> GameMenu {screen = it }
        "About" -> AboutScreen{screen = "Menu"}
        "Game" -> GameScreen{screen = "Menu"}


    }
}

@Composable
fun GameMenu(onNavigate: (String) -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
    )
//    Image(
//        painter = painterResource(id = R.drawable.bg_menu),
//        contentDescription = null,
//        modifier = Modifier.fillMaxSize(),
//        contentScale = ContentScale.Crop
//
//    )
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { onNavigate("Game") }) { Text("New Game") }
        Button(onClick = { onNavigate("About") }) { Text("About") }

    }
}

@Composable
fun TargetScore(onBack: () -> Unit){

}

@Composable
fun AboutScreen(onBack: () -> Unit) {
//    Image(
//        painter = painterResource(id = R.drawable.bg_menu),
//        contentDescription = null,
//        modifier = Modifier.fillMaxSize(),
//        contentScale = ContentScale.Crop
//
//    )
    AlertDialog(
        onDismissRequest = onBack,
        title = { Text("About") },
        text = { Text("Student ID : 20230341\nName : venojan shanmugarajah\n\n I confirm that I understand what plagiarism is and have read and understood the section on Assessment Offences in the Essential Information for Students. The work that I have submitted is entirely my own. Any work from other authors is duly referenced and acknowledged. ") },
        confirmButton = {
            TextButton(onClick = onBack) { Text("OK") }
        }
    )

}

@Composable
fun GameScreen(onBack: () -> Unit) {
    var HumanPlayer by remember { mutableStateOf(mutableListOf(0,0,0,0,0)) }
    var ComputerPlayer by remember { mutableStateOf(mutableListOf(0,0,0,0,0)) }
    var HumanScore by remember { mutableStateOf(0) }
    var ComputerScore by remember { mutableStateOf(0) }
    var TargetScore by remember { mutableStateOf(101) }
//    var HumanWin by remember { mutableStateOf(0) }
//    var ComputerWin by remember { mutableStateOf(0) }
    var hasThrown by remember { mutableStateOf(false) }
    var HumanRolls by remember { mutableStateOf(0) }
    var ComputerRolls by remember { mutableStateOf(0) }
    var HumanAttempts by remember { mutableStateOf(0) }
    var ComputerAttempts by remember { mutableStateOf(0) }
    var GameOver by remember { mutableStateOf(false) }
    var Tie by remember { mutableStateOf(false) }


    Box(
        modifier = Modifier.fillMaxSize(),
    )
    Image(
        painter = painterResource(id = R.drawable.bg_game),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop

    )



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp),
        horizontalAlignment = Alignment.Start


    ) {
        Text("Target Score: $TargetScore")
        Text("Human Score: $HumanScore - Computer Score: $ComputerScore")
       // Text("Wins H: $HumanWin - Wins C: $ComputerWin")

    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        DiceRow(HumanPlayer)
        Text("Human Player", fontSize = 16.sp)
        Spacer(modifier = Modifier.height(16.dp))

        DiceRow(ComputerPlayer)
        Text("Computer Player", fontSize = 16.sp)
        Spacer(modifier = Modifier.height(16.dp))


//        Row {
//            Button(onClick = {
//                HumanPlayer = List(5) { (1..6).random() }.toMutableList()
//                ComputerPlayer = List(5) { (1..6).random() }.toMutableList()
//
//            }) { Text("Throw") }
//
//            Spacer(modifier = Modifier.width(16.dp))
//            Button(onClick = {
//                HumanScore += HumanPlayer.sum()
//                ComputerScore += ComputerPlayer.sum()
//            }) { Text("Score") }
//
//
//
//        }


        Row {
            Button(onClick = {
                if (!hasThrown) {
                    // Human rolls first
                    HumanPlayer = List(5) {
                        (1..6).random()
                    }.toMutableList()
                    HumanRolls = 1  // First roll done

                    // Computer rolls once (initial roll)
                    ComputerPlayer = List(5) { (1..6).random() }.toMutableList()
                    ComputerRolls = 1

                    hasThrown = true
                }else if (HumanRolls<3){
                    // Human decides to reroll
                    HumanPlayer = HumanPlayer.map { die ->
                        if ((0..1).random() == 1) {
                            (1..6).random()
                        } else die // 50% chance to reroll each die
                    }.toMutableList()
                    HumanRolls++
                }
            }) {
                Text(
                    when{
                        !hasThrown -> "Throw"
                        HumanRolls < 3 -> "Throw Again"
                        else -> "No More Rolls"
                    }
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(onClick = {
                if (hasThrown) {
                    // Human player scores
                    HumanScore += HumanPlayer.sum()
                    HumanAttempts++

                    // Computer decides to use its remaining rerolls (max 2)
                    while (ComputerRolls < 3 && (0..1).random() == 1) { // 50% chance to reroll
                        ComputerPlayer = ComputerPlayer.map { die ->
                            if ((0..1).random() == 1) {
                                (1..6).random()
                            } else die // 50% chance to reroll each die
                        }.toMutableList()
                        ComputerRolls++
                    }

                    // Computer final score
                    ComputerScore += ComputerPlayer.sum()
                    ComputerAttempts++

                    // Reset for the next round
                    hasThrown = false
                    HumanRolls = 0
                    ComputerRolls = 0

                  if (ComputerScore >= TargetScore || HumanScore >= TargetScore){
                      GameOver = true

                  }
                }
            }) {
                Text("Score")
            }
        }


    }

    if (GameOver){
        var Win:String=""


        if (HumanScore > ComputerScore){
            Win = "You Win"


        }else if (ComputerScore > HumanScore){
            Win = "You Lose"


        }else{
            Tie = true

            while (Tie) {

                HumanPlayer = List(5) { (1..6).random() }.toMutableList()
                HumanScore += HumanPlayer.sum()
                ComputerPlayer = List(5) { (1..6).random() }.toMutableList()
                ComputerScore += ComputerPlayer.sum()

                if (HumanScore > ComputerScore) {
                    Win = "You Win"
                    Tie= false
                }else if (ComputerScore > HumanScore){
                    Win = "You Lose"
                    Tie = false
                }else{
                    Tie = true
                }

            }


        }

        AlertDialog(
            onDismissRequest = onBack,
            title = {
                Text(
                    text = Win,
                    color = if (Win == "You Win") androidx.compose.ui.graphics.Color.Green else androidx.compose.ui.graphics.Color.Red
                )

            },


            confirmButton = {
                TextButton(onClick = onBack) { Text("OK") }
            }
        )
    }
}

fun DiceImage(dice: Int):Int {
    return when (dice) {
        1 -> R.drawable.dice1
        2 -> R.drawable.dice2
        3 -> R.drawable.dice3
        4 -> R.drawable.dice4
        5 -> R.drawable.dice5
        6 -> R.drawable.dice6
        else -> R.drawable.dice0
    }
}

@Composable
fun DiceRow(dice: List<Int>) {
    Row (
        horizontalArrangement = Arrangement.SpaceEvenly
    ){
        dice.forEachIndexed{index, dice ->
            Image(
                painter = painterResource(id = DiceImage(dice)),
                contentDescription = "dice $dice",
                modifier = Modifier.size(64.dp).padding(4.dp)
            )
        }
    }
}






