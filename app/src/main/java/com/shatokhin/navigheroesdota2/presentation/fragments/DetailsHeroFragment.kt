package com.shatokhin.navigheroesdota2.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import coil.load
import com.shatokhin.navigheroesdota2.R
import com.shatokhin.navigheroesdota2.databinding.FragmentDetailsHeroBinding
import com.shatokhin.navigheroesdota2.domain.models.HeroParcelable
import com.shatokhin.navigheroesdota2.utilites.*

class DetailsHeroFragment: Fragment() {
    companion object{
        const val TAG = "DetailsHeroFragment"

        fun newInstance(hero: HeroParcelable): DetailsHeroFragment {
            val arg = Bundle()
            arg.putParcelable(EXTRA_HERO_PARCELABLE, hero)

            val fragment = DetailsHeroFragment()
            fragment.arguments = arg
            return fragment
        }
    }

    private lateinit var binding: FragmentDetailsHeroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDetailsHeroBinding.inflate(layoutInflater)

        val hero = arguments?.getParcelable<HeroParcelable>(EXTRA_HERO_PARCELABLE)

        if (hero != null){
            setDataHero(hero)
        }
        else {
            Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG).show()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setDataHero(hero: HeroParcelable) {
        binding.hero = hero

        val urlImage = BASE_URL + hero.img
        binding.imageView.load(urlImage)

        when (hero.primaryAttr){
            PRIMARY_ATTR_AGILITY -> binding.iconPrimaryAttr.setImageResource(R.drawable.hero_agility)
            PRIMARY_ATTR_STRENGTH -> binding.iconPrimaryAttr.setImageResource(R.drawable.hero_strength)
            PRIMARY_ATTR_INTELLECT -> binding.iconPrimaryAttr.setImageResource(R.drawable.hero_intelligence)
        }

    }
}