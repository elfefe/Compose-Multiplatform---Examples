package com.elfefe.common

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import com.elfefe.common.drawer.Drawer
import com.elfefe.common.firebase.authentication.OAuthConsoleApp
import com.elfefe.common.pager.page.News
import com.elfefe.common.pager.page.Ssh

@Composable
fun App() {
    OAuthConsoleApp().auth(
        "id",
        "sq"
    )

    val pages = listOf(Ssh(), News())

    var pageIndex by remember { mutableStateOf(0) }

    val scope = rememberCoroutineScope()

    MaterialTheme {
        Drawer(
            drawerContent = {

            },
            pageContent = {

            }
        )
    }
}

