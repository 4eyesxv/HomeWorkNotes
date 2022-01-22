package com.example.homeworknotes.models

import java.io.Serializable

data class DataNotes(
    var id: Int,
    var nameUser: String ?= null,
    var shortDescription: String ?= null,
    var detailedDescription: String ?= null,
    var startDateTime: String ?= null,
    var endDateTime: String ?= null,
    var userNotes: String ?= null,
):Serializable