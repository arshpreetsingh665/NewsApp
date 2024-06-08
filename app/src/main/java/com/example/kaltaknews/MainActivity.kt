package com.example.kaltaknews.MainActivity

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.kaltaknews.Entertainment.EntertainmentFragment
import com.example.kaltaknews.FragmentAdapter.Adapter
import com.example.kaltaknews.Health.HealthFragment
import com.example.kaltaknews.Home.HomeFragment
import com.example.kaltaknews.R
import com.example.kaltaknews.Science.ScienceFragment
import com.example.kaltaknews.Sports.SportsFragment
import com.example.kaltaknews.Technology.TechnologyFragment
import com.example.kaltaknews.databinding.ActivityMainBinding
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayoutMediator
import java.io.File

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var imageView: ImageView
    private lateinit var sharedPreferences: SharedPreferences
    private var imagePath: String? = null

    companion object {
        private const val PREFS_NAME = "MyPrefs"
        private const val IMAGE_PATH_KEY = "imagePath"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(mBinding.toolbar)

        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)

        toggle = ActionBarDrawerToggle(this, mBinding.drawer, R.string.open, R.string.close)
        mBinding.drawer.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        mBinding.toolbar.setNavigationOnClickListener {
            if (mBinding.drawer.isDrawerOpen(mBinding.navigationView)) {
                mBinding.drawer.closeDrawer(mBinding.navigationView)
            } else {
                mBinding.drawer.openDrawer(mBinding.navigationView)
            }
        }

        val headerView: View = mBinding.navigationView.getHeaderView(0)
        val fab: FloatingActionButton = headerView.findViewById(R.id.floatingActionButton)
        fab.setOnClickListener {
            ImagePicker.with(this)
                .crop()
                .compress(1024)
                .maxResultSize(1080, 1080)
                .start()
        }
        imageView = headerView.findViewById(R.id.coverPhoto)

        // Load the image path from SharedPreferences
        imagePath = sharedPreferences.getString(IMAGE_PATH_KEY, null)
        if (!imagePath.isNullOrEmpty()) {
            loadImageFromPath(imagePath!!)
        }

        val fragmentAdapter = Adapter(supportFragmentManager, lifecycle)
        fragmentAdapter.addFragment(HomeFragment(), "Home")
        fragmentAdapter.addFragment(ScienceFragment(), "Science")
        fragmentAdapter.addFragment(SportsFragment(), "Sports")
        fragmentAdapter.addFragment(HealthFragment(), "Health")
        fragmentAdapter.addFragment(EntertainmentFragment(), "Entertainment")
        fragmentAdapter.addFragment(TechnologyFragment(), "Technology")
        mBinding.fragmentContainers.adapter = fragmentAdapter

        TabLayoutMediator(mBinding.tablayout, mBinding.fragmentContainers) { tab, position ->
            tab.text = fragmentAdapter.fragmentTitleList[position]
        }.attach()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == ImagePicker.REQUEST_CODE) {
            val uri: Uri? = data?.data
            uri?.let {
                imagePath = it.path
                saveImageToSharedPreferences(imagePath)
                loadImageFromPath(imagePath!!)
            }
        }
    }

    private fun saveImageToSharedPreferences(imagePath: String?) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(IMAGE_PATH_KEY, imagePath)
        editor.apply()
    }

    private fun loadImageFromPath(imagePath: String) {
        val imgFile = File(imagePath)
        if (imgFile.exists()) {
            imageView.setImageURI(Uri.fromFile(imgFile))
        } else {
            Log.e("MainActivity", "Image file not found")
        }
    }
}
