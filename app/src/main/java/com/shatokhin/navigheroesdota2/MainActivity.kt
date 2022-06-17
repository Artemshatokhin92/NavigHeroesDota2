package com.shatokhin.navigheroesdota2

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.shatokhin.navigheroesdota2.databinding.ActivityMainBinding
import com.shatokhin.navigheroesdota2.domain.models.HeroParcelable
import com.shatokhin.navigheroesdota2.presentation.fragments.AboutAppFragment
import com.shatokhin.navigheroesdota2.presentation.fragments.AllHeroesFragment
import com.shatokhin.navigheroesdota2.presentation.fragments.DetailsHeroFragment
import com.shatokhin.navigheroesdota2.presentation.viewmodels.ViewModelListAllHeroes
import com.shatokhin.navigheroesdota2.presentation.viewmodels.ViewModelListAllHeroesFactory

class MainActivity : AppCompatActivity(), SwitchFragment {
    private val viewModelListAllHeroes: ViewModelListAllHeroes by viewModels { ViewModelListAllHeroesFactory(applicationContext) }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // создание макета из XML с помощью binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null){ // если это первый запуск
            supportFragmentManager
                .beginTransaction()
                .add(binding.fragmentContainer.id, AllHeroesFragment())
                .commit()
        }
    }

    override fun showDetailsHero(hero: HeroParcelable) {
        addNewFragment(DetailsHeroFragment.newInstance(hero), DetailsHeroFragment.TAG)
    }

    override fun showAboutApp() {
        addNewFragment(AboutAppFragment(), AboutAppFragment.TAG)
    }

    private fun addNewFragment(fragment: Fragment, tag: String){
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(tag)
            .replace(binding.fragmentContainer.id, fragment)
            .commit()
    }

}

interface SwitchFragment {
    fun showDetailsHero(hero: HeroParcelable)
    fun showAboutApp()
}

