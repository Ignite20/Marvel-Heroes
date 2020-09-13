package com.example.marvelheroes_amaris.common

import com.example.marvelheroes_amaris.R

open class ErrorMessage(val resourceIdTitle: Int, val resourceIdMessage: Int)

object ErrorSomethingWentWrong : ErrorMessage(
    R.string.dialog_title_oops,
    R.string.dialog_message_something_went_wrong
)