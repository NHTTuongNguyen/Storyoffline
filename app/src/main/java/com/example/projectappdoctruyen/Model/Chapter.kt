package com.example.projectappdoctruyen.Model

import java.io.Serializable

class Chapter : Serializable {
    var idChapter: Int? = null
    var idStory: Int? = null
    var title: String? = null
    var detail: String? = null

    constructor() {}
    constructor(idChapter: Int) {
        this.idChapter = idChapter
    }

    constructor(idStory: Int, idChapter: Int, title: String?) {
        this.idStory = idStory
        this.idChapter = idChapter
        this.title = title
    }

    constructor(title: String?, idStory: Int) {
        this.title = title
        this.idStory = idStory
    }

    constructor(idChapter: Int, idStory: Int, title: String?, detail: String?) {
        this.idChapter = idChapter
        this.idStory = idStory
        this.title = title
        this.detail = detail
    }
}