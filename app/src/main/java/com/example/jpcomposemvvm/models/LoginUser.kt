package com.example.jpcomposemvvm.models

data class LoginUser(
    var strEmailAddress: String?,
    var strPassword: String?
) {
    fun isEmailValid(): Boolean {
        // Add your email validation logic here
        return strEmailAddress?.contains("@") ?: false
    }

    fun isPasswordLengthGreaterThan5(): Boolean {
        return strPassword?.length ?: 0 > 5
    }
}