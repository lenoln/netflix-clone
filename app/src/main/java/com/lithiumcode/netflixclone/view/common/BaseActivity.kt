package com.lithiumcode.netflixclone.view.common

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.lithiumcode.netflixclone.R

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {

    lateinit var mViewDataBinding: T
        private set
    protected abstract fun contentLayoutId(): Int
    protected abstract fun initViewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewDataBinding = DataBindingUtil.setContentView(this, contentLayoutId())

        initViewBinding()
    }

    protected open fun getBinding(): T {
        return mViewDataBinding
    }

    open fun setupToolbarAction() {}

    open fun setupToolbarRemoveIcon(@StringRes title: Int) {
        setupToolbar(title)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    open fun setupToolbar(@DrawableRes icon: Int, @StringRes title: Int) {
        setupToolbar(title)
        val drawable = ResourcesCompat.getDrawable(getResources(), icon, null)?.mutate()
        supportActionBar?.setHomeAsUpIndicator(drawable)
    }

    open fun setupToolbar(@StringRes title: Int) {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(title)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.getItemId()) {
            android.R.id.home -> {
                setupToolbarAction()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


}