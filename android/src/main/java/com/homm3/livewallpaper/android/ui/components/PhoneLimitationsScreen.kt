package com.homm3.livewallpaper.android.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.homm3.livewallpaper.R
import com.homm3.livewallpaper.android.ui.theme.H3lwpnextTheme

@Composable
fun PhoneLimitations(actions: NavigationActions) {
    H3lwpnextTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(24.dp)
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            Row(modifier = Modifier.padding(vertical = 8.dp)) {
                Text(
                    textAlign = TextAlign.Center,
                    text = stringResource(R.string.phone_limitation_note)
                )
            }

            Row(modifier = Modifier.padding(vertical = 8.dp)) {
                Text(
                    textAlign = TextAlign.Center,
                    text = stringResource(R.string.phone_limitation_miui)
                )
            }

            Row(modifier = Modifier.padding(vertical = 8.dp)) {
                Text(
                    textAlign = TextAlign.Center,
                    text = stringResource(R.string.phone_limitation_no)
                )
            }

            Button(modifier = Modifier.padding(vertical = 8.dp),
                onClick = { actions.settings() }) {
                Text(text = stringResource(R.string.phone_limitation_okay))
            }
        }
    }
}