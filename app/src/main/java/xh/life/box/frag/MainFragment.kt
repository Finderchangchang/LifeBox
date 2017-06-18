package xh.life.box.frag

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import com.youth.banner.Banner
import xh.life.box.R
import xh.life.box.base.BaseFragment
import xh.life.box.method.common.CommonAdapter
import xh.life.box.method.common.CommonViewHolder
import xh.life.box.method.common.GlideImageLoader
import xh.life.box.method.common.MainAdapter
import xh.life.box.method.main.D_Main
import xh.life.box.method.main.i_main
import xh.life.box.ui.MainActivity
import java.util.*


/**
 * Created by Finder丶畅畅 on 2017/6/18 09:12
 * QQ群481606175
 */
class MainFragment : BaseFragment(), i_main {
    var load: D_Main = D_Main(this)
    var data = ArrayList<String>()
    override fun data_result(result: ArrayList<String>) {
        adapter!!.refresh(result)
        if (main_srl!!.isRefreshing) {
            main_srl!!.isRefreshing = false//停止刷新
        }
    }

    var main_title_ban: Banner? = null
    var main_gv: GridView? = null
    var main_srl: SwipeRefreshLayout? = null
    var adapter: CommonAdapter<String>? = null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.frag_main, container, false)
        main_title_ban = view.findViewById(R.id.main_title_ban) as Banner
        main_gv = view.findViewById(R.id.main_gv) as GridView
        main_srl = view.findViewById(R.id.main_srl) as SwipeRefreshLayout
        return view
    }

    override fun onFragmentFirstVisible() {
        super.onFragmentFirstVisible()
        var images = ArrayList<String>()
        images.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=659133392,2446997191&fm=26&gp=0.jpg")
        main_title_ban!!.setImages(images)
        main_title_ban!!.start()
        adapter = object : CommonAdapter<String>(MainActivity.main, data, R.layout.item_main) {
            override fun convert(holder: CommonViewHolder, t: String, position: Int) {
                holder.setText(R.id.val_tv, t)
            }
        }
        main_gv!!.adapter = adapter
        main_srl!!.setOnRefreshListener {
            load.initData()//处理刷新数据
        }
        main_title_ban!!.setImageLoader(GlideImageLoader())
        load.initData()//处理刷新数据
    }
}