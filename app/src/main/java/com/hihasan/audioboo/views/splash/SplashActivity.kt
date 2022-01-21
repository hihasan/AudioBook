package com.hihasan.audioboo.views.splash

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.hihasan.audioboo.R
import com.hihasan.audioboo.constants.ApplicationConstants
import com.hihasan.audioboo.databinding.ActivitySplashBinding
import com.hihasan.audioboo.utils.base.BaseActivity
import com.hihasan.audioboo.views.intro.IntroActivity
import com.hihasan.audioboo.views.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : BaseActivity() {
    private lateinit var binding : ActivitySplashBinding
    private lateinit var viewModel : SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)

        waitScreen()
    }

    private fun waitScreen(){
        CoroutineScope(Dispatchers.Main).launch {
            delay(ApplicationConstants.APP_LOAD_TIME)
            val intent: Intent
            if (database!!.permissionDao.getStatus().isNotEmpty()){
                intent = Intent(this@SplashActivity, MainActivity::class.java)
            } else {
                intent = Intent(this@SplashActivity, IntroActivity::class.java)
            }

            startActivity(intent)
        }
    }
}