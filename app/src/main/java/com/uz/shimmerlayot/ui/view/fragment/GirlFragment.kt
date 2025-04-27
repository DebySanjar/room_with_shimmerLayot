package com.uz.shimmerlayot.ui.view.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.uz.shimmerlayot.R
import com.uz.shimmerlayot.data.dao.PersonDao
import com.uz.shimmerlayot.data.db.AppDatabase
import com.uz.shimmerlayot.databinding.FragmentListBinding
import com.uz.shimmerlayot.ui.adapter.PersonAdapter
import kotlinx.coroutines.launch

class GirlFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: PersonAdapter
    private lateinit var personDao: PersonDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val db = AppDatabase.getDatabase(requireContext())
        personDao = db.personDao()

        adapter = PersonAdapter { person ->
            lifecycleScope.launch {
                personDao.delete(person)
                Toast.makeText(context, "${person.name} - o'chirildi!", Toast.LENGTH_SHORT).show()
            }
        }


        binding.recyclerView.adapter = adapter

        // Shimmer ON
        binding.shimmerLayout.startShimmer()
        binding.recyclerView.visibility = View.GONE

        personDao.getCountGender("Ayol").observe(viewLifecycleOwner) { count ->
            binding.username.text = "Ayollar: ${count.toString()}"
        }

        _binding?.profileImage!!.setImageResource(R.drawable.img)

        // LiveData orqali kuzatamiz
        personDao.getPersonsByGender("Ayol").observe(viewLifecycleOwner) { list ->
            Handler(Looper.getMainLooper()).postDelayed({
                binding.shimmerLayout.stopShimmer()
                binding.shimmerLayout.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE
                adapter.submitList(list)
            }, 2000)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
