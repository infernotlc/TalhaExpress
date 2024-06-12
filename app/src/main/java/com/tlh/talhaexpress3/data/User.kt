package com.tlh.talhaexpress3.data

data class User(
    val firstName: String,
    val lastName: String,
    val email: String,
    var imagePath: String = "" //new user doesnt have a image
){
    constructor(): this("","","","")
}