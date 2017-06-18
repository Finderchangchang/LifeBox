package xh.life.box.base

import android.app.Application
import android.content.Context
import com.lzy.okgo.OkGo


/**
 * Created by Finder丶畅畅 on 2017/1/14 21:25
 * QQ群481606175
 */

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        OkGo.init(this)
    }

    companion object {
        var context: Context? = null
        var time: Long = 10//刷新间隔
    }
}
