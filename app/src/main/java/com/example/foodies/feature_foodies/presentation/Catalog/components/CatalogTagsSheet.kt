package com.example.foodies.feature_foodies.presentation.Catalog.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.foodies.R
import com.example.foodies.common.components.CustomPrimaryButton
import com.example.foodies.feature_foodies.domain.model.Tag


@Composable
fun CatalogTagsSheet(
    tagsList: List<Tag>,
    selectedTagsList: List<Int>,
    onTagClickListener: (Int) -> Unit,
    onButtonClickListener: () -> Unit
) {

    Column(
        Modifier
            .padding(horizontal = 24.dp)
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Text(text = "Подобрать блюда", style = MaterialTheme.typography.headlineMedium.copy(
            color = Color.Black.copy(0.87f)
        ))
        Spacer(modifier = Modifier.height(28.dp))
        LazyColumn(){
            items(tagsList.size){ index ->
                TagsItem(
                    name = tagsList[index].name,
                    isSelected = selectedTagsList.contains(tagsList[index].id),
                    isLastElement = index == tagsList.size-1,
                    onTagClickListener = { onTagClickListener(tagsList[index].id?:0) }
                )
            }
        }
        CustomPrimaryButton(
            title = "Готово",
            onClickListener = onButtonClickListener,
            modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun TagsItem(
    name: String,
    isSelected: Boolean,
    isLastElement: Boolean,
    onTagClickListener: () -> Unit
) {
    SideEffect {
        Log.i("ProductLog", isSelected.toString())
    }
    Column(Modifier.fillMaxWidth()) {
        Row(
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = name, style = MaterialTheme.typography.bodyMedium.copy(
                color = Color.Black.copy(0.87f)
            ))
            CheckBox(
                isSelected = isSelected,
                modifier = Modifier.clickable { onTagClickListener() }
            )
        }
        Spacer(modifier = Modifier.height(12.dp))

        if (!isLastElement){
            Divider(
                Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.Black.copy(0.12f))
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
    }

}

@Composable
fun CheckBox(
    isSelected: Boolean,
    modifier: Modifier
) {
    Box(
        modifier.size(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            Modifier
                .size(18.dp)
                .clip(MaterialTheme.shapes.small)
                .border(
                    if (isSelected) 0.dp else 3.dp,
                    Color.Black.copy(0.12f),
                    MaterialTheme.shapes.small
                ),
            contentAlignment = Alignment.Center
        ) {
            if (isSelected){
                Image(painter = painterResource(id = R.drawable.ic_selected_checkbox), contentDescription = null)
            }
        }
    }

    
}