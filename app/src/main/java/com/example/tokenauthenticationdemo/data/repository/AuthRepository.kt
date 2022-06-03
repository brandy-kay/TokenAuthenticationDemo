package com.example.tokenauthenticationdemo.data.repository

import com.example.tokenauthenticationdemo.data.remote.ApiService
import com.example.tokenauthenticationdemo.data.remote.request.ForgotPasswordRequest
import com.example.tokenauthenticationdemo.data.remote.request.LoginRequest
import com.example.tokenauthenticationdemo.data.remote.request.RegisterRequest
import com.example.tokenauthenticationdemo.data.remote.response.forgotpassword.ForgotPassworResponse
import com.example.tokenauthenticationdemo.data.remote.response.loginres.LoginResponse
import com.example.tokenauthenticationdemo.data.remote.response.registerres.RegisterResponse
import com.example.tokenauthenticationdemo.ui.screens.login.SessionManager
import com.example.tokenauthenticationdemo.utils.Resource
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber
import java.io.IOException
import java.lang.Exception

class AuthRepository (private val apiService: ApiService){

    //register
    suspend fun registerUser(registerRequest: RegisterRequest): Resource<RegisterResponse> {
        return try {
            val response = apiService.registerUser(registerRequest)
            Timber.d(response.message)

            if (response.message == "user created successfully") {
                Resource.Success(data = response)
            } else {
                Resource.Failure(response.errors.errr)
            }
        } catch (e: IOException) {
            return Resource.Failure("Oops! couldn't reach server, check your internet connection.")
        } catch (e: HttpException) {
            return Resource.Failure("Oops! something went wrong. Please try again")
        }
    }

    //login
    suspend fun loginUser(loginRequest: LoginRequest): Resource<LoginResponse>{
       // lateinit var sessionManager: SessionManager
        return try {
            val response = apiService.loginUser(loginRequest)
            //val loginResponse = response.copy()
            Resource.Success(response)
          /*  run {
               // sessionManager = SessionManager(this)
                sessionManager.saveAuthToken(loginResponse.accessToken)
                Resource.Success(response)
            }*/
        }catch (e: IOException) {
            return Resource.Failure("Oops! couldn't reach server, check your internet connection.")
        } catch (e: HttpException) {
            return Resource.Failure("Oops! something went wrong. Please try again")
        }
    }

    //forgot password
    suspend fun forgotPassword(email:String) :Resource<ForgotPassworResponse>{
        return try {
            val response = apiService.forgotPassword(email)
            Resource.Success(response)
        } catch (e:Exception){
            return Resource.Failure("Not Registered")
        }
    }


}

