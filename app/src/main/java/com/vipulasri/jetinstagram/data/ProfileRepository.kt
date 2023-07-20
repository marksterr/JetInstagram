package com.vipulasri.jetinstagram.data

import com.vipulasri.jetinstagram.model.Post
import com.vipulasri.jetinstagram.model.currentUser

object ProfileRepository {

    private val posts = mutableListOf<Post>()

    private fun populatePosts() {
        val _posts = ArrayList<Post>()
        (0..14).forEach { index ->
            val post = Post(
                id = index + 1,
                image = "https://source.unsplash.com/random/400x300?${index + 1}",
                user = currentUser,
                isLiked = false,
                likesCount = index + 100,
                commentsCount = index + 20,
                timeStamp = System.currentTimeMillis()
            )
            _posts.add(post)
        }

        posts.clear()
        posts.addAll(_posts)
    }

    init {
        populatePosts()
    }

    fun getProfilePosts(): List<Post> = posts
}