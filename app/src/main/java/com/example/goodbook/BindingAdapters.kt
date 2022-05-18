package com.example.goodbook

import android.content.Context
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
fun setImage(view: ImageView, imageSrc: String?) {
    val resID: Int = view.resources.getIdentifier(imageSrc, "drawable", view.context.packageName);
    view.setImageResource(resID)
}

@BindingAdapter("imagesrc")
fun setImage(view: ShapeableImageView, imageSrc: String?) {
    val resID: Int = view.resources.getIdentifier(imageSrc, "drawable", view.context.packageName);
    view.setImageResource(resID)
}

