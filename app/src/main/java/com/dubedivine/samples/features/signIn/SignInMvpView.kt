package com.dubedivine.samples.features.signIn

import com.dubedivine.samples.data.model.User
import com.dubedivine.samples.features.base.MvpView
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

/**
 * Created by divine on 3/11/18.
 */
interface SignInMvpView : MvpView {

    fun signedIn(user: User)

    //should be implemented world wide
    fun showProgressWithMessage(show: Boolean, title: String = "", msg: String = "")

}