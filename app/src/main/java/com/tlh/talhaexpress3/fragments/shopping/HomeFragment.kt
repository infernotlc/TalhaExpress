package com.tlh.talhaexpress3.fragments.shopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.tlh.talhaexpress3.R
import com.tlh.talhaexpress3.adapters.HomeViewpagerAdapter
import com.tlh.talhaexpress3.databinding.ActivityShoppingBinding
import com.tlh.talhaexpress3.databinding.FragmentAccountOptionsBinding
import com.tlh.talhaexpress3.databinding.FragmentHomeBinding
import com.tlh.talhaexpress3.fragments.categories.AccessoryFragment
import com.tlh.talhaexpress3.fragments.categories.ChairFragment
import com.tlh.talhaexpress3.fragments.categories.CupboardFragment
import com.tlh.talhaexpress3.fragments.categories.FurnitureFragment
import com.tlh.talhaexpress3.fragments.categories.MainCategoryFragment
import com.tlh.talhaexpress3.fragments.categories.TableFragment

class HomeFragment:Fragment()  {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
     binding = FragmentHomeBinding.inflate(inflater)
    return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoriesFragments = arrayListOf<Fragment>(
            MainCategoryFragment(),
            ChairFragment(),
            CupboardFragment(),
            TableFragment(),
            AccessoryFragment(),
            FurnitureFragment()
        )

        val viewPager2Adapter = HomeViewpagerAdapter(categoriesFragments,childFragmentManager, lifecycle)
        binding.viewpagerHome.adapter = viewPager2Adapter
        TabLayoutMediator(binding.tabLayout, binding.viewpagerHome){ tab, position ->
            when(position){
                0 -> tab.text = "Main"
                1 -> tab.text = "Chair"
                2 -> tab.text = "Cupboard"
                3 -> tab.text = "Table"
                4 -> tab.text = "Accessory"
                5 -> tab.text = "Furniture"
            }
        }.attach()


    }
}