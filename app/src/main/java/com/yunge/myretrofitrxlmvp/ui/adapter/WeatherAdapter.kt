package com.yunge.myretrofitrxlmvp.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yunge.myretrofitrxlmvp.R
import com.yunge.myretrofitrxlmvp.weamvvm.model.CastsBean
import android.text.Spanned
import android.text.SpannableStringBuilder
import android.text.style.ImageSpan
import android.graphics.Bitmap
import android.content.Context
import android.graphics.Color
import android.widget.LinearLayout
import android.view.Gravity
import android.text.TextUtils
import com.yunge.myretrofitrxlmvp.ui.tool.ScreenUtils


class WeatherAdapter(private val castsBeanList: List<CastsBean>,private val context: Context): RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.weather_item,parent,false))
    }

    override fun getItemCount(): Int {
        return castsBeanList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val bean = castsBeanList[position]

        holder.date_text.text = bean.date

        val dayInfo = "白天天气：${bean.daytemp}℃ ${bean.dayweather}"
        holder.nightInfo_text.text = "晚上天气： ${bean.nighttemp}℃ ${bean.nightweather}"

        addTagToTextView(holder.dayInfo_text,dayInfo,"白天天气")
    }

    class WeatherViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        var date_text: TextView = itemView.findViewById(R.id.weather_item_date)
        var dayInfo_text: TextView = itemView.findViewById(R.id.weather_item_dayInfo)
        var nightInfo_text: TextView = itemView.findViewById(R.id.weather_item_nightInfo)
    }


    /**
     * 设置文本标签
     */
    private fun addTagToTextView(target: TextView, title: String, tag: String) {
        var title = title
        if (TextUtils.isEmpty(title)) {
            title = ""
        }

        val content = title + tag


        /**
         * 创建TextView对象，设置drawable背景，设置字体样式，设置间距，设置文本等
         * 这里我们为了给TextView设置margin，给其添加了一个父容器LinearLayout。不过他俩都只是new出来的，不会添加进任何布局
         */
        val layout = LinearLayout(context)
        val textView = TextView(context)
        textView.text = tag
        textView.background = context.resources.getDrawable(R.drawable.room_member_role_bg)
        textView.textSize = 15f
        textView.setTextColor(Color.parseColor("#FDA413"))
        textView.includeFontPadding = false
        textView.setPadding(
            ScreenUtils.dip2px(context, 6), 0,
            ScreenUtils.dip2px(context, 6), 0
        )
        textView.height = ScreenUtils.dip2px(context, 25)
        textView.gravity = Gravity.CENTER_VERTICAL

        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        // 设置左间距
        layoutParams.leftMargin = ScreenUtils.dip2px(context, 6)
        // 设置下间距，简单解决ImageSpan和文本竖直方向对齐的问题
        layoutParams.bottomMargin = ScreenUtils.dip2px(context, 3)
        layout.addView(textView, layoutParams)

        /**
         * 第二步，测量，绘制layout，生成对应的bitmap对象
         */
        layout.isDrawingCacheEnabled = true
        layout.measure(
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        )
        // 给上方设置的margin留出空间
        layout.layout(
            0,
            0,
            textView.measuredWidth + ScreenUtils.dip2px(context, 6 + 3),
            textView.measuredHeight
        )
        // 获取bitmap对象
        val bitmap = Bitmap.createBitmap(layout.drawingCache)
        //千万别忘最后一步
        layout.destroyDrawingCache()

        /**
         * 第三步，通过bitmap生成我们需要的ImageSpan对象
         */
        val imageSpan = ImageSpan(context, bitmap)


        /**
         * 第四步将ImageSpan对象设置到SpannableStringBuilder的对应位置
         */
        val ssb = SpannableStringBuilder(content)
        //将尾部tag字符用ImageSpan替换
        ssb.setSpan(imageSpan, title.length, content.length, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
        target.text = ssb
    }
}