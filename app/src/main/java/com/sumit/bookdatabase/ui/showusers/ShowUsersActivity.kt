package com.sumit.bookdatabase.ui.showusers

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sumit.bookdatabase.data.User
import com.sumit.bookdatabase.databinding.ActivityShowUsersBinding
import com.sumit.bookdatabase.ui.showusers.adapter.OnClickListener
import com.sumit.bookdatabase.ui.showusers.adapter.UserAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ShowUsersActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityShowUsersBinding
    private val showUserViewModel: ShowUserViewModel by viewModels()
    private lateinit var adapter: UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpUi()
        setUpObserver()
    }

    private fun setUpUi() {
        adapter = UserAdapter(this, this)
        binding.rvUser.layoutManager = LinearLayoutManager(this)
        binding.rvUser.adapter = adapter
    }

    private fun setUpObserver() {
        showUserViewModel.cartLiveData.observe(this, Observer {
            adapter.submitList(it.reversed())
        })
    }

    override fun deleteUser(user: User, position: Int) {
        showUserViewModel.deleteUser(user)
        adapter.notifyItemRemoved(position)
        showUserViewModel.getUsers()

    }

}