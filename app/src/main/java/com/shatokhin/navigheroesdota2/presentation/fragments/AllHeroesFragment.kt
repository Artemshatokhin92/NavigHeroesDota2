package com.shatokhin.navigheroesdota2.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.shatokhin.navigheroesdota2.SwitchFragment
import com.shatokhin.navigheroesdota2.data.models.HeroJson
import com.shatokhin.navigheroesdota2.databinding.FragmentAllHeroesBinding
import com.shatokhin.navigheroesdota2.domain.models.HeroParcelable
import com.shatokhin.navigheroesdota2.presentation.adapters.AdapterRvHeroes
import com.shatokhin.navigheroesdota2.presentation.viewmodels.ViewModelListAllHeroes
import com.shatokhin.navigheroesdota2.presentation.viewmodels.ViewModelListAllHeroesFactory

class AllHeroesFragment: Fragment() {
    companion object{
        const val TAG = "AllHeroesFragment"
    }

    private lateinit var binding: FragmentAllHeroesBinding
    private lateinit var adapterRvHeroes: AdapterRvHeroes
    private lateinit var switcherFragment: SwitchFragment
    private val viewModelListAllHeroes: ViewModelListAllHeroes by activityViewModels { ViewModelListAllHeroesFactory(requireContext()) }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        switcherFragment = activity as SwitchFragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllHeroesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecycleView()

        binding.btnLoadHeroes.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            viewModelListAllHeroes.loadListAllHeroes()
        }

        binding.btnAboutApp.setOnClickListener {
            switcherFragment.showAboutApp()
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun initRecycleView() {
        val clickListener = object : AdapterRvHeroes.ClickListenerHero {
            override fun clickHero(hero: HeroJson) {
//                val switcherFragment = activity as SwitchFragment

                val heroParcelable = HeroParcelable(hero.name, hero.primaryAttr, hero.attackType, hero.roles, hero.img,
                    hero.baseHealth, hero.baseMana, hero.baseArmor, hero.baseStrength, hero.baseAgility, hero.baseIntellect)

                switcherFragment.showDetailsHero(heroParcelable)

            }
        }

        adapterRvHeroes = AdapterRvHeroes(clickListener)

        binding.rvHero.adapter = adapterRvHeroes

        val lmForRvHeroes = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        binding.rvHero.layoutManager = lmForRvHeroes

        viewModelListAllHeroes.listHeroJson.observe(viewLifecycleOwner) { listHeroJson ->
            binding.progressBar.visibility = View.GONE
            if (listHeroJson.isEmpty()) Toast.makeText(requireContext(), "Download error, please repeat later", Toast.LENGTH_LONG).show()
            adapterRvHeroes.submitList(listHeroJson)
        }
    }
}