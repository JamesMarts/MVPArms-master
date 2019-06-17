package com.yiqi.news

/**
 * @author ChayChan
 * @description: 记录常量的类
 * @date 2017/6/16  20:22
 */

object Constant {
    /**已选中频道的json */
    val SELECTED_CHANNEL_JSON = "selectedChannelJson"
    /**w未选频道的json */
    val UNSELECTED_CHANNEL_JSON = "unselectChannelJson"

    /**频道对应的请求参数 */
    val CHANNEL_CODE = "channelCode"
    val IS_VIDEO_LIST = "isVideoList"

    val ARTICLE_GENRE_VIDEO = "video"
    val ARTICLE_GENRE_AD = "ad"

    val TAG_MOVIE = "video_movie"

    val URL_VIDEO = "/video/urls/v/1/toutiao/mp4/%s?r=%s"

    /**获取评论列表每页的数目 */
    val COMMENT_PAGE_SIZE = 20

    val DATA_SELECTED = "dataSelected"
    val DATA_UNSELECTED = "dataUnselected"



    const val QQ = 1//qq
    const val QQ_ZONE = 2//qq空间
    const val WE_CHAT = 3//微信
    const val FRIEND = 4//朋友圈
    const val WEIBO = 5//微博


}
