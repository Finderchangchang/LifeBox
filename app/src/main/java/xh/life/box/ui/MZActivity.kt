package xh.life.box.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.lzy.okgo.OkGo
import wai.gr.cla.base.BaseActivity

import xh.life.box.R
import xh.life.box.model.MZModel
import java.util.*
import android.support.v7.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_mz.*
import xh.life.box.callback.DemoAdapter
import xh.life.box.method.main.D_MZ
import xh.life.box.method.main.i_mz
import android.support.design.widget.Snackbar
import xh.life.box.base.App


class MZActivity : BaseActivity(), i_mz {
    var data = ArrayList<MZModel>()
    var adapter: DemoAdapter? = null
    var main: D_MZ = D_MZ(this)
    var index = 1
    override fun initViews() {
        setContentView(R.layout.activity_mz)
        main_gv.setHasFixedSize(true)
        main_gv.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        adapter = DemoAdapter()
        main_gv.adapter = adapter
        main_srl.setOnRefreshListener {
            if (thread!!.djs_time > 0) {
                main_srl.isRefreshing = false
                isRefresh = false
                Snackbar.make(main_srl, "别太心急了，再等" + thread!!.djs_time + " 秒", Snackbar.LENGTH_SHORT).show()
            } else {
                isRefresh = true
                main_srl.isRefreshing = true
                main.do_mz(index++, true, "搞笑", "")
                thread!!.djs_time = App.time
                thread!!.start()
            }
        }
    }

    override fun initEvents() {
        main.do_mz(index, false, "美女", "")
        if (thread == null) {
            thread = MyThread()
            thread!!.djs_time = App.time
            thread!!.start()
        }
    }

    var isRefresh = false//用来监听是刷新还是加载更多
    var thread: MyThread? = null

    /**
     * 定时器功能
     * */
    class MyThread : Thread() {
        var djs_time: Long = App.time
        override fun run() {
            while (djs_time > 0) {
                try {
                    Thread.sleep(1000)
                    djs_time--
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
    }


    override fun data_result(result: ArrayList<MZModel>) {
        if (isRefresh) {//刷新在顶部添加
            data.addAll(0, result)
        } else {
            data.addAll(result)
        }
        adapter!!.replaceAll(data)
        main_srl.isRefreshing = false
    }
}
