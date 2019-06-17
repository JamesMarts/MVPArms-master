package com.yiqi.news.mvp.adapter

import android.text.TextUtils
import android.view.View.GONE
import android.view.View.VISIBLE
import cn.jzvd.JzvdStd
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.socks.library.KLog
import com.yiqi.huize.loadImage
import com.yiqi.news.R
import com.yiqi.news.app.utils.TimeUtils
import com.yiqi.news.app.utils.UIUtils
import com.yiqi.news.app.video.VideoStateListener
import com.yiqi.news.app.video.VideoStateListenerAdapter
import com.yiqi.news.entity.News
import kotlinx.android.synthetic.main.item_video.view.*

class VideoAdapter : BaseQuickAdapter<News, BaseViewHolder>(R.layout.item_video) {

    override fun convert(helper: BaseViewHolder, item: News) {
        val itemView = helper.itemView
        val videoPlayer = itemView.videoPlayer

        if (item.video_detail_info != null) {
            val format = UIUtils.getString(R.string.video_play_count)
            var watchCount = item.video_detail_info.video_watch_count
            var countUnit = ""
            if (watchCount > 10000) {
                watchCount /= 10000
                countUnit = "万"
            }
            itemView.tv_video_title.text = item.title
            itemView.tv_video_count.text = String.format(format, watchCount.toString() + countUnit)//播放次数
            itemView.videoPlayer.thumbImageView.loadImage(item.video_detail_info.detail_video_large_image.url)
            itemView.tv_video_time.text = TimeUtils.getShortTime(item.behot_time * 1000)
            itemView.tv_video_long.text = TimeUtils.secToTime(item.video_duration)
        }

        videoPlayer.setAllControlsVisiblity(GONE, GONE, VISIBLE, GONE, VISIBLE, GONE, GONE)
        videoPlayer.tinyBackImageView.visibility = GONE

        videoPlayer.titleTextView.text = ""//清除标题,防止复用的时候出现

        videoPlayer.setVideoStateListener(object : VideoStateListenerAdapter() {

            internal var isVideoParsing = false //视频是否在解析的标识

            override fun onStartClick() {
                KLog.e("onStartClick")
                var videoUrl = ""
                if (item.video_detail_info != null) {
                    //取出解析后的网址
                    videoUrl = item.video_detail_info.parse_video_url
                }

                if (!TextUtils.isEmpty(videoUrl)) {
                    //如果已经解析过
                    KLog.e("取出对应的视频地址: $videoUrl")
                    videoPlayer.setUp(videoUrl, item.title, JzvdStd.SCREEN_WINDOW_LIST)
                    videoPlayer.startVideo()
                    return
                }

                //解析视频
                parseVideo()
            }

            private fun parseVideo() {
                KLog.e("title: " + item.title)

                if (isVideoParsing) {
                    KLog.e("视频正在解析，不重复调用...")
                    return
                } else {
                    isVideoParsing = true
                }

                //隐藏开始按钮 显示加载中
                videoPlayer.setAllControlsVisiblity(GONE, GONE, GONE, VISIBLE, VISIBLE, GONE, GONE)
                helper.setVisible(R.id.tv_video_long, false)//隐藏时长


//                val decoder = object : VideoPathDecoder() {
//                    fun onSuccess(url: String) {
//                        KLog.i("Video url:$url")
//                        UIUtils.postTaskSafely(Runnable {
//                            //更改视频是否在解析的标识
//                            isVideoParsing = false
//
//                            //准备播放
//                            videoPlayer.setUp(url, news.title, JzvdStd.SCREEN_WINDOW_LIST)
//
//                            if (news.video_detail_info != null) {
//                                news.video_detail_info.parse_video_url = url //赋值解析后的视频地址
//                                videoPlayer.seekToInAdvance = news.video_detail_info.progress //设置播放进度
//                            }
//
//                            //开始播放
//                            videoPlayer.startVideo()
//                        })
//                    }
//
//                    fun onDecodeError(errorMsg: String) {
//                        isVideoParsing = false//更改视频是否在解析的标识
//                        //隐藏加载中 显示开始按钮
//                        videoPlayer.setAllControlsVisiblity(GONE, GONE, VISIBLE, GONE, VISIBLE, GONE, GONE)
//                        UIUtils.showToast(errorMsg)
//                    }
//                }
//                decoder.decodePath(news.url)
            }

        })

    }


}
