package com.example.takeauction

import androidx.fragment.app.Fragment

interface IFragmentNavigation {
    fun navigateFrag(fragment: Fragment, addToStack: Boolean)
}