package com.example.goodbook.model

import android.graphics.Bitmap

data class DetailComment (
        val id: Int,
        val name: String,
        val avt: Bitmap ?= null,
        val description: String
        )