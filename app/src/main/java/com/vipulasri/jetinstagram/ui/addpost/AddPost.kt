package com.vipulasri.jetinstagram.ui.addpost

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.vipulasri.jetinstagram.ui.theme.grey100
import com.vipulasri.jetinstagram.ui.theme.grey200
import com.vipulasri.jetinstagram.ui.theme.grey50
import com.vipulasri.jetinstagram.ui.theme.lightblue

@Composable
fun AddPost() {
    val imagePreview: String = "https://source.unsplash.com/random"
    var caption by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TopAppBar(
            backgroundColor = Color.Transparent,
            elevation = 0.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "New Post",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "Share",
                    style = MaterialTheme.typography.h6,
                    color = lightblue,
                    modifier = Modifier.clickable(onClick = { /* Handle share click */ })
                )
            }
        }

        Divider(color = Color.Gray, thickness = 2.dp)

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Image(
                painter = rememberImagePainter(data = imagePreview),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(56.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            TextField(
                value = caption,
                onValueChange = { caption = it },
                modifier = Modifier.fillMaxWidth().height(112.dp),
                label = { Text("Write a caption...") },
                singleLine = false,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.surface,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Divider(color = grey200, thickness = 1.dp)


        ExpandableCard(title = "Tag People")
        Divider(color = grey200, thickness = 1.dp)
        ExpandableCard(title = "Add Location")
        Divider(color = grey200, thickness = 1.dp)
        ExpandableCard(title = "Add Fundraiser")
        Divider(color = grey200, thickness = 1.dp)
        ExpandableCard(title = "Post to Other Instagram Accounts")

        Divider(color = grey200, thickness = 1.dp)

        ToggleButtonRow(title = "Facebook")
        ToggleButtonRow(title = "Tumblr")
        ToggleButtonRow(title = "Flickr")

        Divider(color = grey200, thickness = 1.dp)

        ExpandableCard(title = "Advanced Settings", smallText = true)
        Divider(color = grey200, thickness = 1.dp)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExpandableCard(title: String, smallText: Boolean = false) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 0.dp,
        shape = RoundedCornerShape(0.dp), // no border on sides
        onClick = { expanded = !expanded }
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = title,
                style = if (smallText) MaterialTheme.typography.body2 else MaterialTheme.typography.body1,
                fontWeight = if (smallText) FontWeight.Light else FontWeight.Bold
            )
            Spacer(Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Expandable Icon",
                modifier = Modifier.rotate(if (expanded) 180f else 0f)
            )
        }
        if (expanded) {
            // Add the content of the expanded card here
        }
    }
}

@Composable
fun ToggleButtonRow(title: String) {
    var isChecked by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Switch(
            checked = isChecked,
            onCheckedChange = { isChecked = it }
        )
    }
}