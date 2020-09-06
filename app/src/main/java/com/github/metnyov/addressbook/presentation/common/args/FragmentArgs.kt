package com.github.metnyov.addressbook.presentation.common.args

import android.os.Parcelable
import com.github.metnyov.addressbook.presentation.common.base.BaseFragmentWithArgs

@Suppress("unused")
abstract class FragmentArgs<TFragment : BaseFragmentWithArgs<*, *>> : Parcelable