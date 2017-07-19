package com.example.iu.myapplication.config;

/**
 * Created by dell on 2017/7/12.
 */

public class UrlUtils {

    //基本接口
    private static final String BaseURL = "http://www.ipanda.com/kehuduan/";
//http://www.ipanda.com/kehuduan/
    //首页接口
    public static final String HOMEURL =BaseURL+"PAGE14501749764071042/index.json";

    //熊猫直播接口
    public static final String LIVEURL = BaseURL+"PAGE14501769230331752/index.json";

    //熊猫直播多视角直播
    public static final String MULTIPLE = BaseURL+"PAGE14501769230331752/PAGE14501787896813312/index.json";

    public static final String Wonderful="http://api.cntv.cn/video/videolistById";

    //熊猫文化接口
    public static final String CULTUREURL = BaseURL+"xmwh/index.json";

    //熊猫播报、观察
    public static final String BROADCASTURL = BaseURL+"PAGE14503485387528442/index.json";

    //熊猫播报,LIST
    public static final String BROADCASTURLLIST = "http://api.cntv.cn/apicommon/index?path=iphoneInterface/general/getArticleAndVideoListInfo.json&primary_id=PAGE1449807494852603,PAGE1451473625420136,PAGE1449807502866458,PAGE1451473627439140,PAGE1451473547108278,PAGE1451473628934144&serviceId=panda";


    //直播中国
    public static final String CHINAURL = BaseURL+"PAGE14501775094142282/index.json";

    //原创互动接口
    public static final String INTERACTIVE = BaseURL+"PAGE14501767715521482/index.json";

}
