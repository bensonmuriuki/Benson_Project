package com.example.benson_project.models

class Upload{
    var name:String=""
    var email:String=""
    var post:String=""
    var imageUrl:String=""
    var id:String=""

    constructor(name:String,email:String,post:String,imageUrl:String,id:String){

        this.name=name
        this.email=email
        this.post = post
        this.imageUrl=imageUrl
        this.id=id

    }
    constructor()

}