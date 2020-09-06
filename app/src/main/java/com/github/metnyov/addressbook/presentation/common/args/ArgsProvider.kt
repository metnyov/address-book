package com.github.metnyov.addressbook.presentation.common.args

import android.os.Parcelable

interface ArgsProvider<TArgs : Parcelable> {

    var args: TArgs
}