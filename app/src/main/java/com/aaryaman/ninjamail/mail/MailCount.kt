package com.aaryaman.ninjamail.mail

import com.chibatching.kotpref.KotprefModel

object MailCount : KotprefModel() {
    var count by longPref(0)
}