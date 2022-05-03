package com.android.rickandmorty.data.network.model

data class Response(
    val info: Info,
    val characters: List<Character>
)