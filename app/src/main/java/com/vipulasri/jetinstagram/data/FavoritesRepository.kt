package com.vipulasri.jetinstagram.data

import com.vipulasri.jetinstagram.model.FavoriteUser

object FavoritesRepository {
    private val favoriteUsers = mutableListOf<FavoriteUser>()

    private fun populateUsers() {
        val generatedUsernames = listOf(
            "travellove",
            "fashionpassion",
            "fitnessexpert",
            "foodieadventures",
            "wanderlustlife",
            "musiclover",
            "photographydreams",
            "artisticmind",
            "bookwormreader",
            "petloverjoy"
        )

        val generatedNames = listOf(
            "Travel Love",
            "Fashion Passion",
            "Fitness Expert",
            "Foodie Adventures",
            "Wanderlust Life",
            "Music Lover",
            "Photography Dreams",
            "Artistic Mind",
            "Bookworm Reader",
            "Pet Lover Joy"
        )

        (0 until 10).forEach { index ->
            val user = FavoriteUser(
                id = index + 1,
                name = generatedNames[index],
                username = generatedUsernames[index],
                image = "https://source.unsplash.com/random/400x300?${index + 1}"
            )
            favoriteUsers.add(user)
        }
    }

    init {
        populateUsers()
    }

    fun getFavoriteUsers(): List<FavoriteUser> = favoriteUsers
    fun removeFavoriteUser(id: Int) {
        favoriteUsers.removeAll { it.id == id }
    }
}