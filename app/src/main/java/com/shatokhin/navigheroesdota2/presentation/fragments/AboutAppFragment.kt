package com.shatokhin.navigheroesdota2.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shatokhin.navigheroesdota2.databinding.FragmentAboutAppBinding

class AboutAppFragment: Fragment() {
    companion object{
        const val TAG = "AboutAppFragment"
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentAboutAppBinding.inflate(layoutInflater).root
    }
}