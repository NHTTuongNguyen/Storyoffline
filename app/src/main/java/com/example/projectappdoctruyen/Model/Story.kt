package com.example.projectappdoctruyen.Model

import java.io.Serializable

class Story : Serializable {
    var id :Int? = null
    var name: String? = null
    var conTent: String? = null

    constructor() {}
    constructor(id: Int, name: String?) {
        this.id = id
        this.name = name
    }

    constructor(id: Int, name: String?, conTent: String?) {
        this.id = id
        this.name = name
        this.conTent = conTent
    }
}