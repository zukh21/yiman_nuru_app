package com.zim.yiman_nuru

class Data{
    var title: String? = null
    var fullText: String? = null
    constructor(){}

    constructor(
        title: String?,
        fullText: String?
    ){
        this.title = title
        this.fullText = fullText
    }
}