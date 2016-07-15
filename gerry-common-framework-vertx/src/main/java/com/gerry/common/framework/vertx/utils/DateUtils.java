package com.gerry.common.framework.vertx.utils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateUtils {
	/** yyyy-MM-dd */
	public static final String DATESHOWFORMAT = "yyyy-MM-dd";
	/** yyyy-MM-dd HH:mm:ss */
	public static final String DATETIMESHOWFORMAT = "yyyy-MM-dd HH:mm:ss";
	/** yyyy-MM-dd HH:mm */
	public static final String DATETIMESHOWFORMAT1 = "yyyy-MM-dd HH:mm";
	/** yyyyMMddHHmmssSSS */
	public static final String DATETIMESHOWFORMAT2 = "yyyyMMddHHmmssSSS";
	/** yyMMddHHmmssSSS */
	public static final String DATETIMESHOWFORMAT3 = "yyMMddHHmmssSSS";

	/**
	 * 计算两个日期的间隔天数
	 * 
	 * @param startDate
	 *            开始时间，如：2008-12-03 11:00:00
	 * @param endDate
	 *            结束时间，如：2009-12-31 11:00:00
	 * @return long 间隔天数(long)
	 */
	public static long getBetweenDays(String startDate, String endDate) {
		if (endDate == null || startDate == null) {
			return -1;
		}
		Date dateStart = isDate(startDate);
		if (null == dateStart) {
			return -1;
		}
		Date dateEnd = isDate(endDate);
		if (null == dateEnd) {
			return -1;
		}
		return getBetweenDays(dateStart, dateEnd);
	}

	/**
	 * 计算两个日期的间隔天数
	 * 
	 * @param startDate
	 *            开始时间，如：2008-12-03 11:00:00
	 * @param endDate
	 *            结束时间，如：2009-12-31 11:00:00
	 * @return long 间隔天数(long)
	 */
	public static long getBetweenDays(Date startDate, Date endDate) {
		if (endDate == null || startDate == null) {
			return -1;
		}
		Long days = endDate.getTime() - startDate.getTime();
		days = days / (1000 * 60 * 60 * 24);
		return days;
	}

	/**
	 * 获取两个日期间隔月数
	 * 
	 * @param startDate
	 *            开始时间，如：2008-12-03 11:00:00
	 * @param endDate
	 *            结束时间，如：2009-12-31 11:00:00
	 * @return 间隔月数
	 */
	public static int getBetweenMonths(Date startDate, Date endDate) {
		if (endDate == null || startDate == null) {
			return -1;
		}
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		start.setTime(startDate);
		end.setTime(endDate);
		int n = 0;
		while (!start.after(end)) {
			n++;
			start.add(Calendar.MONTH, 1);
		}
		return n - 1;
	}

	/**
	 * 获取与指定日期相差指定 天数 的日期
	 * 
	 * @param baseDate
	 *            时间字符串，如：2008-12-03 11:00:00
	 * @param dayCount
	 *            向前或向后的天数，向后为正数，向前为负数
	 * @param patternString
	 *            处理结果日期的显示格式，如："YYYY-MM-DD"
	 * @return String 处理后的日期字符
	 */
	public static String getAfterDate(String baseDate, int dayCount, String patternString) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Integer.parseInt(baseDate.substring(0, 4)), Integer.parseInt(baseDate.substring(5, 7)) - 1, Integer.parseInt(baseDate.substring(8, 10)));
		calendar.add(Calendar.DATE, dayCount);
		return new SimpleDateFormat(patternString).format(calendar.getTime());
	}

	/**
	 * 获取给定时间的后几分钟时间
	 * 
	 * @param baseDate
	 * @param minute
	 * @return
	 * @see
	 */
	public static Date getAfterDateByMINUTE(Date baseDate, int minute) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(baseDate);
		cal.add(Calendar.MINUTE, minute);
		return cal.getTime();
	}

	/**
	 * 获取给定时间的后几分钟时间
	 * 
	 * @param date
	 * @param minute
	 * @return
	 * @see
	 */
	public static String getAfterDateByMINUTE(String date, int minute) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 24小时制
		Date newDate = null;
		try {
			newDate = format.parse(date);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (newDate == null) {
			return "";
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(newDate);
		cal.add(Calendar.MINUTE, minute);// 24小时制
		newDate = cal.getTime();
		cal = null;
		return format.format(newDate);
	}

	/**
	 * 获取与指定日期相差指定 天数 的日期
	 * 
	 * @param baseDate
	 *            时间
	 * @param dayCount
	 *            向前或向后的天数，向后为正数，向前为负数
	 * @param patternString
	 *            处理结果日期的显示格式，如："YYYY-MM-DD"
	 * @return String 处理后的日期字符
	 */
	public static String getAfterDate(Date baseDate, int dayCount, String patternString) {
		return getAfterDate(getDateString(baseDate, DATETIMESHOWFORMAT), dayCount, patternString);
	}

	/**
	 * 获取与指定日期相差指定 天数 的日期
	 * 
	 * @param baseDate
	 *            日期
	 * @param dayCount
	 *            向前或向后的天数，向后为正数，向前为负数
	 * @return 日期
	 */
	public static Date getAfterDateAsDate(Date baseDate, int dayCount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(baseDate);
		calendar.add(Calendar.DATE, dayCount);
		return calendar.getTime();
	}

	/**
	 * 获取与指定日期相差指定 月数 的日期
	 * 
	 * @param baseDate
	 *            时间字符串，如：2008-12-03 11:00:00
	 * @param monthCount
	 *            向前或向后的月数，向后为正数，向前为负数
	 * @param patternString
	 *            处理结果日期的显示格式，如："YYYY-MM-DD"
	 * @return String 处理后的日期字符
	 */
	public static String getAfterMonth(String baseDate, int monthCount, String patternString) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Integer.parseInt(baseDate.substring(0, 4)), Integer.parseInt(baseDate.substring(5, 7)) - 1, Integer.parseInt(baseDate.substring(8, 10)));
		calendar.add(Calendar.MONTH, monthCount);
		return new SimpleDateFormat(patternString).format(calendar.getTime());
	}

	/**
	 * 获取与指定日期相差指定 月数 的日期
	 * 
	 * @param baseDate
	 *            时间字符串，如：2008-12-03 11:00:00
	 * @param monthCount
	 *            向前或向后的月数，向后为正数，向前为负数
	 * @param patternString
	 *            处理结果日期的显示格式，如："YYYY-MM-DD"
	 * @return String 处理后的日期字符
	 */
	public static String getAfterMonth(Date baseDate, int monthCount, String patternString) {
		return getAfterMonth(getDateString(baseDate, DATETIMESHOWFORMAT), monthCount, patternString);
	}

	/**
	 * 获取与指定日期相差指定 月数 的日期
	 * 
	 * @param baseDate
	 *            时间字符串，如：2008-12-03 11:00:00
	 * @param monthCount
	 *            向前或向后的月数，向后为正数，向前为负数
	 * @return 日期
	 */
	public static Date getAfterMonthAsDate(Date baseDate, int monthCount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(baseDate);
		calendar.add(Calendar.MONTH, monthCount);
		return calendar.getTime();
	}

	/**
	 * 获取与指定日期相差指定 月数 并减去天数的日期
	 * 
	 * @param baseDate
	 *            时间字符串，如：2008-12-03 11:00:00
	 * @param monthCount
	 *            向前或向后的月数，向后为正数，向前为负
	 * @param dateCount
	 *            加或减去的天数，向后为正数，向前为负
	 * @param patternString
	 *            处理结果日期的显示格式，如："YYYY-MM-DD"
	 * @return
	 */
	public static String getEndDate(String baseDate, int monthCount, int dateCount, String patternString) {
		int day = Integer.parseInt(baseDate.substring(8, 10));
		String endDate = getAfterMonth(baseDate, monthCount, patternString);
		int endDay = Integer.parseInt(endDate.substring(8, 10));
		// 说明日期没变
		if (endDay == day) {
			// 月数为正则为减一
			if (monthCount > 0) {
				endDate = getAfterDate(endDate, dateCount, patternString);
			} else {
				endDate = getAfterDate(endDate, dateCount, patternString);
			}
		} else { // 日期已变
			if (monthCount < 0) {
				endDate = getAfterDate(endDate, dateCount, patternString);
			}
		}
		return endDate;
	}

	/**
	 * 获取与指定日期相差指定 月数 并减去天数的日期
	 * 
	 * @param baseDate
	 *            时间字符串，如：2008-12-03 11:00:00
	 * @param monthCount
	 *            向前或向后的月数，向后为正数，向前为负
	 * @param dateCount
	 *            加或减去的天数，向后为正数，向前为负
	 * @param patternString
	 *            处理结果日期的显示格式，如："YYYY-MM-DD"
	 * @return
	 */
	public static String getEndDate(Date baseDate, int monthCount, int dateCount, String patternString) {
		String _baseDate = getDateString(baseDate, DATETIMESHOWFORMAT);
		return getEndDate(_baseDate, monthCount, dateCount, patternString);
	}

	/**
	 * 当前日期转换为指定月数后 的日期
	 * 
	 * @param monthCount
	 *            向前或向后的月数，向后为正数，向前为负
	 * @param patternString
	 *            处理结果日期的显示格式，如："YYYY-MM-DD"
	 * @return String 转换后的日期
	 */
	public static String getBeforeMonth(int monthCount, String patternString) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, monthCount);
		Date _date = calendar.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat(patternString);
		return formatter.format(_date);
	}

	/**
	 * 日期格式化(String转换为Date)
	 * 
	 * @param dateStr
	 *            日期字符串
	 * @param patten
	 *            处理结果日期的显示格式，如："YYYY-MM-DD"
	 * @return
	 */
	public static Date getDateToString(String dateStr, String patten) {
		if (VertxEmptyUtils.isEmpty(dateStr)) {
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat(patten);
		try {
			return formatter.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 日期格式化(String转换为String)
	 * 
	 * @param date
	 *            日期字符串
	 * @param patternString
	 *            处理结果日期的显示格式，如："YYYY-MM-DD"
	 * @return
	 */
	public static String getDateString(String date, String patternString) {
		if (date == null)
			return "";
		if (date.length() < 10)
			return "";
		SimpleDateFormat formatter = new SimpleDateFormat(patternString, Locale.ENGLISH);
		int len = patternString.length();
		if (len > date.length()) {
			patternString = patternString.substring(0, date.length());
		}
		return formatter.format(getDateToString(date, patternString));
	}

	/**
	 * 日期格式化(Date转换为String)
	 * 
	 * @param _date
	 *            日期
	 * @param patternString
	 *            处理结果日期的显示格式，如："YYYY-MM-dd"
	 * @return
	 */
	public static String getDateString(Date _date, String patternString) {
		String dateString = "";
		if (_date != null) {
			SimpleDateFormat formatter = new SimpleDateFormat(patternString);
			dateString = formatter.format(_date);
		}
		return dateString;
	}

	/**
	 * 日期格式转换 DATE to DATE
	 * 
	 * @param _date
	 *            日期
	 * @param patten
	 *            处理结果日期的显示格式，如："YYYY-MM-DD"
	 * @return
	 */
	public static Date dateToDate(Date _date, String patten) {
		String dateStr = "";
		SimpleDateFormat formatter = new SimpleDateFormat(patten);
		try {
			if (_date != null) {
				dateStr = formatter.format(_date);
			}
			return formatter.parse(dateStr);
		} catch (ParseException e) {
		}
		return null;
	}

	/**
	 * 获得格式化日期之后的 String数据
	 * 
	 * @param dateLong
	 * @param patten
	 * @return
	 */
	public static String getDateOfString(Long dateLong, String patten) {
		if (dateLong != null) {
			return (new SimpleDateFormat(patten).format(new Date(dateLong.longValue()))).toString();
		}
		return "";
	}

	/**
	 * 文本时间转换为时间对象
	 * 
	 * @param baseDate
	 *            日期字符串
	 * @return
	 */
	public static java.sql.Date getSqlDate(String baseDate) {
		if (baseDate == null || baseDate.length() == 0)
			return null;
		Date date = getDateToString(baseDate, DATESHOWFORMAT);
		return new java.sql.Date(date.getTime());
	}

	/**
	 * java.util.Date对象转换为java.sql.Date对象
	 * 
	 * @param date
	 *            java.util.Date对象
	 * @return Date java.sql.Date对象
	 */
	public static java.sql.Date UtilDateToSQLDate(Date date) {
		return new java.sql.Date(date.getTime());
	}

	/**
	 * 获取到指定样式的年月日(年月日参数为int型)
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param date
	 *            日
	 * @param patternString
	 *            日期格式，如：yyyy-MM-dd HH:mm:ss EEE
	 * @return 格式化后的字符串
	 */
	public static String getDateString(int year, int month, int date, String patternString) {
		String dateString = "";
		SimpleDateFormat formatter = new SimpleDateFormat(patternString);
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, date);
		Date showDate = calendar.getTime();
		dateString = formatter.format(showDate);
		return dateString;
	}

	/**
	 * 获取到指定样式的年月日(年月日参数为String型)
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param date
	 *            日
	 * @param patternString
	 *            日期格式，如：yyyy-MM-dd HH:mm:ss EEE
	 * @return 格式化后的字符串
	 */
	public static String getDateString(String year, String month, String date, String patternString) {
		String dateString = "";
		try {
			int y = Integer.parseInt(year);
			int m = Integer.parseInt(month);
			int d = Integer.parseInt(date);
			dateString = getDateString(y, m, d, patternString);
		} catch (Exception e) {
		}
		return dateString;
	}

	/**
	 * 获取当前日期
	 * 
	 * @param patten
	 *            日期格式，如：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getDateStr(String patternString) {
		SimpleDateFormat formatter = new SimpleDateFormat(patternString);
		String date = formatter.format(new Date(System.currentTimeMillis()));
		return date;
	}

	/**
	 * 验证输入的文本信息日期是否合
	 * 
	 * @param inputDate
	 * @return
	 */
	public static Date isDate(String dateStr) {
		String date_format_1 = "yyyy/MM/dd";
		String date_format_2 = "yyyy-MM-dd";
		String date_format_3 = "yyyyMMdd";
		String date_format_4 = "yyyy.MM.dd";
		String[] date_format = { date_format_1, date_format_2, date_format_3, date_format_4 };
		for (int i = 0; i < date_format.length; i++) {
			Date tempDate = isDate(dateStr, date_format[i]);
			if (null != tempDate) {
				return tempDate;
			}
		}
		return null;
	}

	/**
	 * 验证输入的文本信息日期是否合
	 * 
	 * @param inputDate
	 * @return
	 */
	public static Date isDate(String dateStr, String patternString) {
		if (VertxEmptyUtils.isEmpty(patternString)) {
			patternString = DATESHOWFORMAT;
		}
		try {
			SimpleDateFormat formatDate = new SimpleDateFormat(patternString);
			formatDate.setLenient(false);
			ParsePosition pos = new java.text.ParsePosition(0);
			Date tempDate = formatDate.parse(dateStr, pos);
			tempDate.getTime();
			return tempDate;
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 把Date转换为Calendar对象
	 * 
	 * @param d
	 *            Date对象
	 * @return Calendar对象
	 */
	public static Calendar getCalendar(Date d) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		return cal;
	}

	/**
	 * 将时间对象转换成指定的格式有小时
	 * 
	 * @param date
	 * @return
	 */
	public static String parseDateTime(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat bartDateFormat = new SimpleDateFormat(DATETIMESHOWFORMAT);
		return bartDateFormat.format(date);
	}

	/**
	 * 将时间对象转换成指定的格式无小时
	 * 
	 * @param date
	 * @return
	 */
	public static String parseDate(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat bartDateFormat = new SimpleDateFormat(DATESHOWFORMAT);
		return bartDateFormat.format(date);
	}

	/**
	 * 获取当前月第一天
	 * 
	 * @return
	 */
	public static String firstDate() {
		Calendar ca = Calendar.getInstance();
		ca.setTime(new Date());
		ca.set(Calendar.DAY_OF_MONTH, 1);
		Date firstDate = ca.getTime();
		return getDateString(firstDate, DATESHOWFORMAT);
	}

	/**
	 * 获取当前月最后一天
	 * 
	 * @return
	 */
	public static String lastDate() {
		Calendar ca = Calendar.getInstance();
		ca.setTime(new Date());
		ca.set(Calendar.DAY_OF_MONTH, 1);
		ca.add(Calendar.MONTH, 1);
		ca.add(Calendar.DAY_OF_MONTH, -1);
		Date lastDate = ca.getTime();
		return getDateString(lastDate, DATESHOWFORMAT);
	}

	/**
	 * 获取指定日期所在月的第一天
	 * 
	 * @param date
	 *            指定的日期
	 * @param force
	 *            是否将时分秒清零
	 * @return 定日期所在月的第一天
	 */
	public static Date firstDate(Date date, boolean force) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.set(Calendar.DAY_OF_MONTH, 1);
		if (force) {
			ca.set(Calendar.HOUR, 0);
			ca.set(Calendar.MINUTE, 0);
			ca.set(Calendar.SECOND, 0);
			ca.set(Calendar.MILLISECOND, 0);
		}
		return ca.getTime();
	}

	/**
	 * 获取指定日期所在月的下一个月的第一天
	 * 
	 * @param date
	 *            指定的日期
	 * @param force
	 *            是否将时分秒清零
	 * @return 指定日期所在月的下一个月的第一天
	 */
	public static Date firstDateOfNextMonth(Date date, boolean force) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(Calendar.MONTH, 1);
		ca.set(Calendar.DAY_OF_MONTH, 1);
		if (force) {
			ca.set(Calendar.HOUR, 0);
			ca.set(Calendar.MINUTE, 0);
			ca.set(Calendar.SECOND, 0);
			ca.set(Calendar.MILLISECOND, 0);
		}
		return ca.getTime();
	}

	/**
	 * 获取当前数据里的时间参数
	 * 
	 * @return
	 */
	public static String getDateStr() {
		return "sysdate";
	}

	/**
	 * 获取上一个月的日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date getUpMouth(Date date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(Calendar.MONTH, -1);
		return ca.getTime();
	}

	/**
	 * 获取上周一
	 * 
	 * @return
	 * @see
	 */
	public static String getPreviousMonday() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.WEEK_OF_YEAR, -1);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(calendar.getTime());
	}

	/**
	 * 获取上周日
	 * 
	 * @return
	 * @see
	 */
	public static String getPreviousSunday() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.WEEK_OF_YEAR, -1);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(calendar.getTime());
	}

	/**
	 * 获取上个月的第一天
	 * 
	 * @return String
	 */
	public static String getPreviousMonthFirst() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1 号
		lastDate.add(Calendar.MONTH, -1);// 减一个月，变为下月的1 号
		// lastDate.add(Calendar.DATE,-1);//减去一天，变为当月最后一天
		str = sdf.format(lastDate.getTime());
		return str;
	}

	/**
	 * 获得上月最后一天的日期
	 * 
	 * @return
	 */
	public static String getPreviousMonthEnd() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.MONTH, -1);// 减一个月
		lastDate.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		lastDate.roll(Calendar.DATE, -1);// 日期回滚一天，也就是本月最后一天
		str = sdf.format(lastDate.getTime());
		return str;
	}

	/**
	 * 获取日期的年
	 * 
	 * @param date
	 * @return
	 */
	public static int getYear(Date date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		return ca.get(Calendar.YEAR);
	}

	/**
	 * 获取日期的月
	 * 
	 * @param date
	 * @return
	 */
	public static int getMonth(Date date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		return ca.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取日期的日
	 * 
	 * @param date
	 * @return
	 */
	public static int getDay(Date date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		return ca.get(Calendar.DATE);
	}

	/**
	 * 获取日期事第几周
	 * 
	 * @param date
	 * @return
	 */
	public static int getWeek(Date date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		return ca.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 获取上一个月的日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date getUpMouth(String date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(DateUtils.getDateToString(date, DATESHOWFORMAT));
		ca.add(Calendar.MONTH, -1);
		return ca.getTime();
	}

	/**
	 * 获取日期的年
	 * 
	 * @param date
	 * @return
	 */
	public static int getYear(String date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(DateUtils.getDateToString(date, DATESHOWFORMAT));
		return ca.get(Calendar.YEAR);
	}

	/**
	 * 获取日期的月
	 * 
	 * @param date
	 * @return
	 */
	public static int getMonth(String date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(DateUtils.getDateToString(date, DATESHOWFORMAT));
		return ca.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取日期的日
	 * 
	 * @param date
	 * @return
	 */
	public static int getDay(String date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(DateUtils.getDateToString(date, DATESHOWFORMAT));
		return ca.get(Calendar.DATE);
	}

	/**
	 * 获取当前时间戳
	 * 
	 * @return 返回1970-1-1至今的时间戳，秒记
	 */
	public static String getTime() {
		return String.valueOf((long) (System.currentTimeMillis() / 1000));
	}

	/**
	 * 获取当前时间戳Long类型
	 * 
	 * @return 返回1970-1-1至今的时间戳，秒记
	 */
	public static Long getLongTime() {
		return System.currentTimeMillis() / 1000;
	}

	/**
	 * 获取前几天或后几天的时间
	 * 
	 * @param data
	 * @param format
	 *            :"yyyy-MM-dd HH:mm:ss"
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static String getNextDay(int days, String format) {
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE, days);// 把日期往后增加一天.整数往后推,负数往前移动
		date = calendar.getTime();
		return new SimpleDateFormat(format).format(date);
	}

	/**
	 * 根据字符串时间获取Long类型的时间戳
	 * 
	 * @param strDate
	 * @return
	 * @throws Exception
	 * @see
	 */
	public static Long getLongTimeByStringValue(String strDate) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat(DATETIMESHOWFORMAT);
		Date date = format.parse(strDate);
		return date.getTime() / 1000;
	}

	/**
	 * 根据Long类型时间获取字符串格式时间(yyyy-MM-dd HH:mm:ss)
	 * 
	 * @param date
	 * @return
	 * @see
	 */
	public static String getStringDateByLongDate(Long date) {
		SimpleDateFormat format = new SimpleDateFormat(DATETIMESHOWFORMAT);
		return format.format(new Date(date * 1000));
	}

	/**
	 * 根据Long类型时间获取字符串格式时间(yyyy-MM-dd)
	 * 
	 * @param date
	 * @return
	 * @see
	 */
	public static String getStringSortDateByLongDate(Long date) {
		SimpleDateFormat format = new SimpleDateFormat(DATESHOWFORMAT);
		return format.format(new Date(date * 1000));
	}

	/**
	 * 根据Long（13位数字时间戳）类型时间获取Date类型时间(yyyy-MM-dd HH:mm:ss)
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 * @see
	 */
	public static Date getDateByLongDate(Long date) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat(DATETIMESHOWFORMAT);
		return format.parse(format.format(new Date(date)));
	}

	/**
	 * 获取前一个月或者下一个月的时间戳
	 * 
	 */
	public static String getMonthTime(int month) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, month);
		String timeStamp = String.valueOf(cal.getTimeInMillis() / 1000);
		return timeStamp;
	}

	/**
	 * 获取前几天或者后几天的时间戳
	 * 
	 */
	public static String getDayTime(int days) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, days);
		String timeStamp = String.valueOf(cal.getTimeInMillis() / 1000);
		return timeStamp;
	}

	/**
	 * 获取日期的第几周
	 * 
	 * @param date
	 * @return
	 */
	public static int getWeek(String date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(DateUtils.getDateToString(date, DATESHOWFORMAT));
		return ca.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 检测d1 是否大于等于d2
	 * 
	 * @param d1
	 * @param d2
	 * @return true d1 是否大于等于d2
	 */
	public static boolean checkMax(Date d1, Date d2) {
		boolean flag = false;
		if (null != d1) {
			if (null != d2) {
				String d1s = getDateString(d1, "yyyyMMdd");
				String d12s = getDateString(d2, "yyyyMMdd");
				if (Double.valueOf(d1s) >= Double.valueOf(d12s)) {
					flag = true;
				}
			} else {
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 检测d1 是否小于d2 只比较日期
	 * 
	 * @param d1
	 * @param d2
	 * @return true d1 是否小于d2
	 */
	public static boolean checkLess(Date d1, Date d2) {
		boolean flag = false;
		if (null != d1) {
			if (null != d2) {
				String d1s = getDateString(d1, "yyyyMMdd");
				String d12s = getDateString(d2, "yyyyMMdd");
				if (Double.valueOf(d1s) < Double.valueOf(d12s)) {
					flag = true;
				}
			}
		}
		return flag;
	}

	/**
	 * 检测d1 是否小于d2 比较时间
	 * 
	 * @author LiuYiJun
	 * @param dateStr1
	 * @param dateStr2
	 * @return
	 */
	public static boolean checkLessTime(String dateStr1, String dateStr2) {
		java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Calendar c1 = java.util.Calendar.getInstance();
		java.util.Calendar c2 = java.util.Calendar.getInstance();
		try {
			c1.setTime(df.parse(dateStr1));
			c2.setTime(df.parse(dateStr2));
		} catch (java.text.ParseException e) {
			System.err.println("格式不正确");
		}
		int result = c1.compareTo(c2);
		if (result < 0) {
			return true;
		}
		return false;
	}

	/**
	 * 检测d1 是否小于d2 比较时间
	 * 
	 * @author LiuYiJun
	 * @date 2015年7月17日
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean checkLessTime(Date date1, Date date2) {
		java.util.Calendar c1 = java.util.Calendar.getInstance();
		java.util.Calendar c2 = java.util.Calendar.getInstance();
		c1.setTime(date1);
		c2.setTime(date2);
		int result = c1.compareTo(c2);
		if (result < 0) {
			return true;
		}
		return false;
	}

	/**
	 * 根据Long类型时间获取字符串格式时间(yyyy-MM-dd HH:mm:ss)
	 * 
	 * @param date
	 * @param sdf
	 * @return
	 * @see
	 */
	public static String getStringDateByLongDate(Long date, String sdf) {
		SimpleDateFormat format = new SimpleDateFormat(sdf);
		return format.format(new Date(date * 1000));
	}

	/**
	 * 正数为后n天；负数为前n天
	 * 
	 * @param date
	 * @param days
	 * @return
	 * @see
	 */
	public static Date getNextDayTime(Date date, Integer days) {
		Calendar ca = new GregorianCalendar();
		ca.setTime(date);
		ca.add(Calendar.DATE, days);
		date = ca.getTime();
		return date;
	}
}
