package com.dubedivine.samples.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by divine on 2017/09/10.
 */

// todo: should not deserialize the the nested objects? , Divine months later, like what was I saying here...?

class Question(val title: String, val body: String, var votes: Long, val tags: List<Tag>, val type: String) : Serializable {
    //for video we will add another constructor which has video here
    var id: String? = null  // protected because it has a setter in the Elastic question child class
    var comments: ArrayList<Comment>? = null
    @SerializedName("answers")
    var answers: ArrayList<Answer>? = null
    var user: User? = null
    var video: Media? = null
    var files: ArrayList<Media>? = null //this can be combined with video dwag
    var createdAt = Date()
    var answered: Boolean? = null


    constructor(id: String,
                title: String,
                body: String,
                votes: Long,
                tags: List<Tag>,
                type: String) : this(title, body, votes, tags, type) { this.id = id }

    override fun toString(): String {
        return "Question{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", votes=" + votes +
                ", comments=" + comments +
                ", answers=" + answers +
                ", tags=" + tags +
                ", user=" + user +
                ", type='" + type + '\'' +
                ", video=" + video +
                ", files=" + files +
                ", createdAt=" + createdAt +
                '}'
    }

    companion object {
        val TYPE_Q ="Q"
        val TYPE_P ="P" // this type is for past papers bro
    }
}
