package com.sshex.sunrise.base.activity

import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.sshex.sunrise.R
import com.sshex.sunrise.constant.LOAD_COORDS_FAILED
import com.sshex.sunrise.constant.LOAD_INFO_BY_COORDS_FAILED

abstract class BaseLocationActivity : MvpAppCompatActivity(), BaseLocationView {

    override fun showLocationErrorMessage(messageCode: Int) {
        showMessageByToast(chooseMessage(messageCode)
        )
    }

    private fun chooseMessage(messageCode: Int): Int {
        return when (messageCode) {
            LOAD_COORDS_FAILED -> R.string.error_fail_loading_coords
            LOAD_INFO_BY_COORDS_FAILED -> R.string.error_fail_loading_info
            else -> throw IllegalArgumentException("Invalid message code")
        }
    }

    private fun showMessageByToast(messageResCode: Int) {
        Toast.makeText(this, messageResCode, Toast.LENGTH_SHORT).show()
    }
}