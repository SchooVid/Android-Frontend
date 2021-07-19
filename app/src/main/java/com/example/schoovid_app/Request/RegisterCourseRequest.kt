package com.example.loginapi.Request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RegisterCourseRequest {

    @SerializedName("courseId")
    @Expose
    var courseId:String? = null

    @SerializedName("participantId")
    @Expose
    var participantId:String? = null

}