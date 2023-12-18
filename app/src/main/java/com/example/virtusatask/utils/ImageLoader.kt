package com.example.virtusatask.utils

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.virtusatask.R
import com.example.virtusatask.domain.model.MovieDetails

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ImageLoader(item: MovieDetails) {
    GlideImage(
        model = item.imageUrl,
        contentDescription = item.fullTitle,
        modifier = Modifier
            .width(400.dp)
            .height(400.dp),
        loading = placeholder(R.drawable.circular_progress_bar),
    )
}