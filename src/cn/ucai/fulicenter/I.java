package cn.ucai.fulicenter;

import static cn.ucai.fulicenter.D.CategoryGroup.IMAGE_URL;

public interface I {

    /** 下拉刷新*/
    int ACTION_DOWNLOAD=0;
    /** 第一次下载*/
    int ACTION_PULL_DOWN=1;
    /** 上拉刷新*/
    int ACTION_PULL_UP=2;

    /** 每行显示的数量columNum*/
    int COLUM_NUM = 2;


    /** 表示列表项布局的两种类型*/
    int TYPE_ITEM=0;
    int TYPE_FOOTER=1;


    public static class Boutique{
        public static final String TABLE_NAME="tb_boutique";
        public static final String ID="id";
        public static final String CAT_ID="catId";
        public static final String TITLE="title";
        public static final String DESCRIPTION="description";
        public static final String NAME="name";
        public static final String IMAGE_URL="imageurl";
    }
    
    class NewAndBoutiqueGood{
        public static final String CAT_ID="cat_id";
        /** 颜色id*/
        public static final String COLOR_ID="color_id";
        /** 颜色名*/
        public static final String COLOR_NAME="color_name";
        /** 颜色代码*/
        public static final String COLOR_CODE="color_code";
        /** 导购链接*/
        public static final String COLOR_URL="color_url";
    }

    public static class CategoryGroup{
        public static final String ID="id";
        public static final String NAME="name";
        public static final String IMAGE_URL="imageurl";
    }

    public static class CategoryChild extends CategoryGroup{
        public static final String PARENT_ID="parent_id";
        public static final String CAT_ID="catId";
    }

    public static class CategoryGood{
        public static final String TABLE_NAME="tb_category_good";
        public static final String ID="id";
        /** 商品id*/
        public static final String GOODS_ID="goods_id";
        /** 所属类别的id*/
        public static final String CAT_ID="cat_id";
        /** 商品的中文名称*/
        public static final String GOODS_NAME="goods_name";
        /** 商品的英文名称*/
        public static final String GOODS_ENGLISH_NAME="goods_english_name";
        /** 商品简介*/
        public static final String GOODS_BRIEF="goods_brief";
        /** 商品原始价格*/
        public static final String SHOP_PRICE="shop_price";
        /** 商品的RMB价格 */
        public static final String CURRENT_PRICE="currency_price";
        /** 商品折扣价格 */
        public static final String PROMOTE_PRICE="promote_price";
        /** 人民币折扣价格*/
        public static final String RANK_PRICE="rank_price";
        /**是否折扣*/
        public static final String IS_PROMOTE="is_promote";
        /** 商品缩略图地址*/
        public static final String GOODS_THUMB="goods_thumb";
        /** 商品图片地址*/
        public static final String GOODS_IMG="goods_img";
        /** 分享地址*/
        public static final String ADD_TIME="add_time";
        /** 分享地址*/
        public static final String SHARE_URL="share_url";
    }

    public static class Property{
        public static final String ID="id";
        public static final String goodsId="goods_id";
        public static final String COLOR_ID="colorid";
        public static final String COLOR_NAME="colorname";
        public static final String COLOR_CODE="colorcode";
        public static final String COLOR_IMG="colorimg";
        public static final String COLOR_URL="colorurl";
    }

    public static class Album{
        public static final String TABLE_NAME="tb_album";
        public static final String ID="id";
        public static final String PID="pid";
        public static final String IMG_ID="img_id";
        public static final String IMG_URL="img_url";
        public static final String THUMB_URL="thumb_url";
        public static final String IMG_DESC="img_desc";
    }

    String SERVER_ROOT                              =       "http://101.251.196.90:8000/FuLiCenterServer/Server";


    public final int CAT_ID=0;

    /**
     * 商品排序方式
     */
    public final int SORT_BY_PRICE_ASC=1;
    public final int SORT_BY_PRICE_DESC=2;
    public final int SORT_BY_ADDTIME_ASC=3;
    public final int SORT_BY_ADDTIME_DESC=4;
	String ISON8859_1 								= 		"iso8859-1";
	String UTF_8 									= 		"utf-8";
	String PAGE_ID 									= 		"page_id";						//分页的起始下标
	String PAGE_SIZE 								= 		"page_size";					//分页的每页数量
	int PAGE_ID_DEFAULT 							= 		0;								//分页的起始下标默认值
	int PAGE_SIZE_DEFAULT 							= 		10;								//分页的每页数量默认值
	
    String QUESTION                                 =       "?";                            //问号
    String EQUAL                                    =       "=";                            //等号
	String MSG_PREFIX_MSG                           =       "msg_";                         //消息码前缀

	String KEY_REQUEST 								= 		"request";

    /** 从服务端查询精选首页的数据*/
    String REQUEST_FIND_BOUTIQUES="find_boutiques";
    /** 从服务端查询新品或精选的商品*/
    String REQUEST_FIND_NEW_BOUTIQUE_GOODS="find_new_boutique_goods";

    /** 从服务端下载tb_category_parent表的数据*/
    String REQUEST_FIND_CATEGORY_GROUP="find_category_group";
    
    /** 从服务端下载tb_category_child表的数据*/
    String REQUEST_FIND_CATEGORY_CHILDREN="find_category_children";
    
    /** 从服务端下载tb_category_good表的数据*/
    String REQUEST_FIND_GOOD_DETAILS="find_good_details";

    /** 从服务端下载一组商品详情的数据*/
    String REQUEST_FIND_GOODS_DETAILS="find_goods_details";


    /** 下载商品相册图像的请求*/
    String REQUEST_DOWNLOAD_ALBUM_IMG="download_album_img_url";
    /** 下载商品相册图像的接口*/
    String DOWNLOAD_ALBUM_IMG_URL= I.SERVER_ROOT+
        "?request="+REQUEST_DOWNLOAD_ALBUM_IMG+"&img_url=";
    
    /** 下载精选首页图像的请求*/
    String REQUEST_DOWNLOAD_BOUTIQUE_IMG="download_boutique_img";
    /** 下载精选首页图像的接口*/
    String DOWNLOAD_BOUTIQUE_IMG_URL= I.SERVER_ROOT+
        "?request="+REQUEST_DOWNLOAD_BOUTIQUE_IMG+"&"+ IMAGE_URL+"=";
    
    /** 下载分类商品大类图像的请求*/
    String REQUEST_DOWNLOAD_CATEGORY_GROUP_IMAGE="download_category_group_image";
    /** 下载分类商品大类图像的接口*/
    String REQUEST_DOWNLOAD_CATEGORY_GROUP_IMAGE_URL = I.SERVER_ROOT+
        "?request="+REQUEST_DOWNLOAD_CATEGORY_GROUP_IMAGE
        +"&"+ IMAGE_URL+"=";

    /** 下载分类商品小类图像的请求*/
    String REQUEST_DOWNLOAD_CATEGORY_CHILD_IMAGE="download_category_child_image";
    /** 下载分类商品小类图像的接口*/
    String REQUEST_DOWNLOAD_CATEGORY_CHILD_IMAGE_URL = I.SERVER_ROOT+
        "?request="+REQUEST_DOWNLOAD_CATEGORY_CHILD_IMAGE
        +"&"+ IMAGE_URL+"=";
    
}
