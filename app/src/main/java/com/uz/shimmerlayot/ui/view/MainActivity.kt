package com.uz.shimmerlayot.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.uz.shimmerlayot.R
import com.uz.shimmerlayot.data.db.AppDatabase
import com.uz.shimmerlayot.data.entity.Person
import com.uz.shimmerlayot.databinding.ActivityMainBinding
import com.uz.shimmerlayot.databinding.DialogAddPersonBinding
import com.uz.shimmerlayot.ui.view.fragment.BoyFragment
import com.uz.shimmerlayot.ui.view.fragment.GirlFragment
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val fragments = listOf(GirlFragment(), BoyFragment())
    private val tabIcons = listOf(R.drawable.woman, R.drawable.man)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount() = fragments.size
            override fun createFragment(position: Int) = fragments[position]
        }

        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.setIcon(tabIcons[position])
        }.attach()


        binding.fab.setOnClickListener {
            showAddDialog()
        }
    }

    private fun showAddDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_person, null)
        val dialogBinding = DialogAddPersonBinding.bind(dialogView)

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()

        val db = AppDatabase.getDatabase(this)
        val personDao = db.personDao()

        dialogBinding.spinnerGender.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            listOf("Erkak", "Ayol")
        )

        dialogBinding.btnSave.setOnClickListener {
            val name = dialogBinding.etName.text.toString().trim()
            val gender = dialogBinding.spinnerGender.selectedItem.toString()

            if (name.isNotEmpty()) {
                lifecycleScope.launch {
                    personDao.insert(Person(name = name, gender = gender))
                    dialog.dismiss()
                }
            } else {
                Toast.makeText(this, "Ism kiriting", Toast.LENGTH_SHORT).show()
            }
        }
        dialog.show()
    }
}
