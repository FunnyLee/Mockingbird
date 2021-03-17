package com.mocking.bird.kotlin.Entity

/**
 * Author: Funny
 * Time: 2021/3/17
 * Description: This is RegisterInfo
 */
data class LoginInfo(var admin: Boolean,
                     var chapterTops: List<*>,
                     var coinCount: String,
                     var collectIds: List<*>,
                     var email: String,
                     var icon: String,
                     var id: String,
                     var nickname: String? = null,
                     var password: String,
                     var publicName: String,
                     var token: String,
                     var type: String,
                     var username: String
){

}

//    "admin": false,
//    "chapterTops": [],
//    "coinCount": 0,
//    "collectIds": [],
//    "email": "",
//    "icon": "",
//    "id": 90972,
//    "nickname": "在最让我婆婆婆婆456",
//    "password": "",
//    "publicName": "在最让我婆婆婆婆456",
//    "token": "",
//    "type": 0,
//    "username": "在最让我婆婆婆婆456"


