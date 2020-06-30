package com.example.demo

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.demo.ui.main.ApiUserAdapter
import com.example.demo.ui.main.MainViewModel
import com.example.demo.utils.Status
import com.example.demo.utils.ViewModelFactory
import com.example.sdk.api.ApiHelper
import com.example.sdk.model.ApiUser
import kotlinx.android.synthetic.main.activity_recycler_view.*


class MainActivity : AppCompatActivity() {

    private val SECRET_SHARED_PREFS_FILE = "secret_shared_prefs_file"
    private val ENV_SETTINGS_FEATURE_ON_KEY = "env_settings_feature_key"

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: ApiUserAdapter
    private lateinit var api: ApiHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        api = ApiHelper()
        setContentView(R.layout.activity_recycler_view)
        setupUI()
        setupViewModel()
        setupObserver()
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter =
            ApiUserAdapter(
                arrayListOf()
            )
        recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        viewModel.getUsers().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data?.let { users -> renderList(users) }
                    recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    progressBar.visibility = View.GONE
                }
            }
        })
    }

    private fun renderList(users: List<ApiUser>) {
        adapter.updateData(users)
        adapter.notifyDataSetChanged()
    }

    private fun setupViewModel() {
        api.init(isEnvFeatureOn())
        viewModel = ViewModelFactory(api).create(MainViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_env, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        val miMap = menu.findItem(R.id.action_env_settings)
        miMap.title = if (isEnvFeatureOn()) {
            getString(R.string.test_feature_on)
        }
        else {
            getString(R.string.test_feature_off)
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.action_env_settings) {
            val feature = !isEnvFeatureOn()
            setEnvFeature(feature)
            api.init(feature)
            viewModel.fetchUsers()
            invalidateOptionsMenu()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    private fun isEnvFeatureOn() : Boolean {
        val masterKey = MasterKey.Builder(this, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build();

        return EncryptedSharedPreferences.create (
            this,
            SECRET_SHARED_PREFS_FILE,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM)
            .getBoolean(ENV_SETTINGS_FEATURE_ON_KEY, false)
    }

    private fun setEnvFeature(on: Boolean) {
        val masterKey = MasterKey.Builder(this, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build();

        EncryptedSharedPreferences.create (
                this,
                SECRET_SHARED_PREFS_FILE,
                masterKey,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM)
            .edit()
            .putBoolean(ENV_SETTINGS_FEATURE_ON_KEY, on)
            .apply()
    }
}