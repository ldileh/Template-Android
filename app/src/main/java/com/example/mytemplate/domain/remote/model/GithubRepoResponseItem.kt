package com.example.mytemplate.domain.remote.model

import com.google.gson.annotations.SerializedName

data class GithubRepoResponseItem(
    @SerializedName("name")
    val name: String = "",
    @SerializedName("id")
    val id: Int = 0
)