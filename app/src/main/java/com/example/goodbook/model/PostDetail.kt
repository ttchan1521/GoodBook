package com.example.goodbook.model

import android.graphics.Bitmap
import androidx.room.Embedded
import java.util.*

class PostDetail (
    val title: String ?= null,
    val img_scr: Bitmap ?= null,
    val book_writer: String ?= null,
    val description: String ?= null,
    val time: Date ?= null,
    val avt: Bitmap ?= null,
    val type: String ?= null,
    val star: Double ?= null

        )