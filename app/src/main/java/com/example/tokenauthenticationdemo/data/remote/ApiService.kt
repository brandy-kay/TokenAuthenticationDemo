package com.example.tokenauthenticationdemo.data.remote

import com.example.tokenauthenticationdemo.data.remote.request.LoginRequest
import com.example.tokenauthenticationdemo.data.remote.request.RegisterRequest
import com.example.tokenauthenticationdemo.data.remote.response.LoginResponse
import com.example.tokenauthenticationdemo.data.remote.response.RegisterResponse
import com.example.tokenauthenticationdemo.models.*
import com.example.tokenauthenticationdemo.utils.Constants.FORGOT_PASSWORD_ENDPOINT
import com.example.tokenauthenticationdemo.utils.Constants.LOGIN_ENDPOINT
import com.example.tokenauthenticationdemo.utils.Constants.REGISTER_ENDPOINT
import com.example.tokenauthenticationdemo.utils.Constants.RESET_PASSWORD_ENDPOINT
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @POST(REGISTER_ENDPOINT)
   suspend fun registerUser(
        @Body registerRequest: RegisterRequest
    ): RegisterResponse

    @POST(LOGIN_ENDPOINT)
     suspend fun loginUser(
        @Body loginRequest: LoginRequest
    ):LoginResponse

    @POST(FORGOT_PASSWORD_ENDPOINT)
    @FormUrlEncoded
    fun forgotPassword(
        @Body forgotPasswordModel: ForgotPasswordModel
    ):Call<RefreshModel>

    @POST(RESET_PASSWORD_ENDPOINT)
    @FormUrlEncoded
    fun resetPassword(
        @Body resetPasswordModel: ResetPasswordModel
    ):Call<RefreshModel>

   /* @POST(REFRESH_ENDPOINT)
    fun refresh(
        refresh_token: String
    ):Call<RefreshModel*/
}


