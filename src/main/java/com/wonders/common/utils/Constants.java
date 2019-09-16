package com.wonders.common.utils;

/**
 * @功能描述：常量
 */
public class Constants {

	/**
	 * 日志等级状态
	 */
	final public static Integer rzdjLogin=0;
	final public static Integer rzdjInsert=1;
	final public static Integer rzdjUpdate=2;
	final public static Integer rzdjDelete=3;

	/**
	 * 逻辑删除状态值
	 */
	final public static int LOGIC_DEL_STATUS = 0;
	/**
	 * 逻辑恢复状态值(正常值)
	 */
	final public static int LOGIC_RESTORE_STATUS = 1;
	/**
	 * 结果处理成功
	 */
	final public static int SUCCESS_CODE = 200;

	/**
	 * 数据库访问等结果处理成功
	 */
	final public static int FAILING_CODE_500 = 500;
	/**
	 * 结果处理失败
	 */
	final public static int FAILING_CODE = -100;
	
	/**
	 * 结果处理失败
	 */
	final public static int JSON_FAILING_CODE = -150;
	/**
	 * 没有权限处理
	 */
	final public static int NO_AUTHORITY_CODE = -300;
	/**
	 * 登录超时
	 */
	final public static int LOGIN_OUT_TIME= -400;

	/**
	 * 有效处方不再续方时间范围
	 */
	final public static int INVALID_RECORD_CODE = -400;
	/**
	 * 无有效处方记录code
	 */
	final public static int NO_VALID_RECORD_CODE = -500;
	/**
	 * 默认的格式化时间
	 */
	final public static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

	/**
	 * 菜单最开始级别
	 */
	final public static int MENU_START_LEVEL = 1;
	/**
	 * 每次批量处理的条数
	 */
	final public static int BATCH_SIZE = 50;
	
	/**
	 * 插件执行重复的最大缓存多少条数
	 */
	final public static int MAX_GOTO_CACHE_SIZE = 150;
	/**
	 * 超级用户
	 */
	final public static String SUPER_USER="wdtest";
	
	
	/**
	 * 账号默认密码
	 */
	final public static String DEFAULT_USER_PSW="123456";
	/**
	 * 用户最开始等级
	 */
	final public static int USER_START_LEVEL=1;
	/**
	 * 最大的上传数据记录的条数
	 */
	final public static int MAX_UPLOAD_ROW=100;


	final public static String EMPTY_HB_VALUE = "/";

	/**
	 * 排序
	 */
	final public static String PXLX_CATEGORY = "1080";
	/**
	 * 排序-顺排
	 */
	final public static String PXLX_SP_CATEGORY = "1080-1001";
	/**
	 * 排序-逆排
	 */
	final public static String PXLX_NP_CATEGORY = "1080-1002";
	
	/**
	 * 系统使用的编码
	 */
	final public  static String SYS_CHARSET_NAME="UTF-8";
	
	/**
	 * 字典表默认3列
	 */
	final public  static String T_DICT_MANAGE_KZ_DEFAULT="bd3b59178ae64ebf873c37de6f61c339";



}
