package xh.life.box.method.main

import com.lzy.okgo.OkGo
import wai.gr.cla.callback.JsonCallback
import wai.gr.cla.model.LzyResponse
import xh.life.box.model.MZModel
import java.util.*

/**
 * Created by Finder丶畅畅 on 2017/6/18 08:15
 * QQ群481606175
 */
interface i_main {
    fun data_result(result: ArrayList<String>)
}

class D_Main(main: i_main) {
    var main = main
    fun initData() {
        val data = (0..10).map { "i:" + (it + 1) } as ArrayList
        main.data_result(data)
    }
}

/**
 * 处理妹子返回的数据
 * */
interface i_mz {
    fun data_result(result: ArrayList<MZModel>)
}

/**
 * 操作妹子数据
 * */
class D_MZ(main: i_mz) {
    var main = main
    /**
     * 加载妹子的数据 index-当前页数
     * */
    fun do_mz(index: Int, isRefresh: Boolean, type: String, tag: String) {
        var index = index * 10
        OkGo.post("https://pic.sogou.com/pics/channel/getAllRecomPicByTag.jsp?category=$type&tag=$tag&start=$index&len=10")
                .execute(object : JsonCallback<LzyResponse<MZModel>>() {
                    override fun onSuccess(t: LzyResponse<MZModel>, call: okhttp3.Call?, response: okhttp3.Response?) {
                        main.data_result(t.all_items as ArrayList<MZModel>)
                    }
                })
    }
}