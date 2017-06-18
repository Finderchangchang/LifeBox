package wai.gr.cla.model

import xh.life.box.model.MZModel
import java.io.Serializable

/**
 * Created by Finder丶畅畅 on 2017/5/13 00:04
 * QQ群481606175
 */
class LzyResponse<T> : Serializable {

    var code: Int = 0
    var msg: String = ""
    var data: T? = null
    var category: String = ""
    var tag: String = ""
    var startIndex: Int = 0
    var maxEnd: Int = 0
    var items: Any? = null
    var newsResult: Any? = null
    var itemsOnPage: Int = 0
    var fromItem: Any? = null
    var groupList: Any? = null
    var resolution: Any? = null
    var all_items: List<MZModel>? = null

    companion object {
        const val serialVersionUID = 5213230387175987834L
    }
    //    public T data;

}
