package xh.life.box.ui

import kotlinx.android.synthetic.main.activity_main.*
import wai.gr.cla.base.BaseActivity
import xh.life.box.R
import xh.life.box.method.common.MainAdapter

class MainActivity : BaseActivity() {
    /**
     * 前期准备
     * */
    override fun initViews() {
        setContentView(R.layout.activity_main)
        main = this
        var mAdapter = MainAdapter(supportFragmentManager)
        main_vp.adapter = mAdapter
        main_vp.offscreenPageLimit = 4
        alphaIndicator.setViewPager(main_vp)
    }

    /**
     * 初始化数据
     * */
    override fun initEvents() {

    }

    companion object {
        var main: MainActivity? = null
    }
}
