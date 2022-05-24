package com.example.goodbook

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.google.android.material.imageview.ShapeableImageView


@BindingAdapter("imageResource")
fun setImageUri(view: ImageView, imageUri: String?) {
    if (imageUri == null) {
        view.setImageURI(null)
    } else {
        view.setImageURI(Uri.parse(imageUri))
    }
}

@BindingAdapter("imagesrc")
fun setImage(view: ImageView, imageSrc: Bitmap?) {
    view.setImageBitmap(imageSrc)
}


@BindingAdapter("imagesrc")
fun setImage(view: ShapeableImageView, imageSrc: Bitmap?) {
    view.setImageBitmap(imageSrc)
}


