package com.elfefe.common.pager.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.jcraft.jsch.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.awt.event.KeyEvent
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

class Ssh : PageImpl() {
    private val session = JSch().getSession("elfefe", "localhost").apply {
        setPassword("elfefe")
        setConfig("StrictHostKeyChecking", "no")
    }

    private val scope = CoroutineScope(Dispatchers.Default)

    private fun executeSsh(command: String, onOutput: (String) -> Unit) {
        scope.launch(Dispatchers.IO) {
            println("Is session connected ${session.isConnected}")

            if (!session.isConnected)
                session.connect(CONNECTION_TIMEOUT)

            val channel = (session.openChannel("exec") as ChannelExec)

            channel.inputStream = null

            val errorStream = ByteArrayOutputStream()
            channel.setErrStream(errorStream)

            channel.setCommand(command)

            channel.connect()

            do {
                delay(20)

                onOutput(channel.inputStream.readAllBytes().decodeToString())
                onOutput(errorStream.toByteArray().decodeToString())
            } while (channel.isConnected)
        }
    }

    @Composable
    override fun Show() {
        var logs by remember { mutableStateOf("") }
        var command by remember { mutableStateOf("") }

        Column(
            modifier =
            Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(72.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                OutlinedTextField(
                    value = command,
                    onValueChange = {
                        command = it.removeSuffix("\n")
                    },
                    modifier = Modifier
                        .fillMaxHeight(0.75f)
                        .fillMaxWidth(0.7f)
                        .onKeyEvent {
                            if (it.key == Key(KeyEvent.VK_ENTER)){
                                executeSsh(command) { text ->
                                    logs += text
                                }
                            }
                            true
                        },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.Black,
                        backgroundColor = Color.White,
                        cursorColor = Color.Black,
                        unfocusedBorderColor = Color.Black
                    )
                )
                IconButton(
                    onClick = {
                    executeSsh(command) {
                        logs += it
                    }
                },
                    modifier = Modifier
                        .width(72.dp)
                        .fillMaxHeight()
                ) {
                    Icon(Icons.Default.Send, "", Modifier.size(24.dp))
                }
                IconButton(
                    onClick = {
                    executeSsh(command) {
                        logs = ""
                    }
                },
                    modifier = Modifier
                        .width(72.dp)
                        .fillMaxHeight()
                ) {
                    Icon(Icons.Default.Delete, "", Modifier.size(24.dp))
                }
            }
            OutlinedTextField(
                value = logs,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier
                    .fillMaxSize(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.White,
                    backgroundColor = Color.Black,
                    cursorColor = Color.White,
                    unfocusedBorderColor = Color.White
                )
            )
        }
    }

    companion object {
        private const val CONNECTION_TIMEOUT = 1 * 60 * 1000
    }
}

class News : PageImpl() {
    @Composable
    override fun Show() {
        Column(
            modifier =
            Modifier
                .fillMaxSize()
                .background(Color.Cyan),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(Icons.Default.AccountBox, "", Modifier.size(72.dp))
        }
    }

}