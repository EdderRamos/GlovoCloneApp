package com.edlabcode.glovocloneapp.core.ext

import android.content.Context
import android.content.Intent
import android.net.Uri

fun Context.navigateToBrowser(url: String) = runCatching {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK
    }
    startActivity(intent)
}
