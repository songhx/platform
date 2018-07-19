/**
 * 时间处理工具
 * 
 * @type
 * @author :songhx3@asiainfo.com
 */
var DateUtils = { 

		 /**
		 * 日期对象转换为毫秒数
		 */

			date2Long : function(date){
		     return date.getTime();
		 },
		 /**
			 * 毫秒转换为日期对象
			 * @param dateVal number 日期的毫秒数
			 */
			long2Date:function(dateVal){
			     return new Date(dateVal);
			 },
			 
			 /**
				 * 将制定格式的时间字符串转换成long
				 * <li>0-yyyyMMdd</li>
				 * <li>1-yyyy-MM-dd</li>
				 * <li>2-HHmmss</li>
				 * <li>3-HH:mm:ss</li>
				 * <li>4-HHmmssSSS</li>
				 * <li>5-HH:mm:ss.SSS</li>
				 * <li>6-yyyyMMddHHmmss</li>
				 * <li>7-yyyy-MM-dd HH:mm:ss</li>
				 * <li>8-yyyyMMddHHmmssSSS</li>
				 * <li>9-yyyy-MM-dd HH:mm:ss.SSS</li>
	 *            <li>10-yyyy/MM/dd HH:mm</li>
	 *            <li>11-yyyy/MM/dd HH:mm:ss</li>
				 * </ul>
				 */
			 string2Long:function(str,format){
				 if(str==null||str==""){
					 return "";
				 }
				 return this.date2Long(this.string2Date(str,format));
			 },
			 
			 
			 /**
				 * 毫秒转换为日期对象
				 * 
				 * @param dateVal
				 * 
 
				 */
			 
			 long2String:function(dateVal,formatType){
				     return this.date2String(new Date(dateVal),formatType);
			 },


	/**
	 * 将时间转化为相应字符串
	 * 
	 * @param {}
	 *            date 待格式化的Date对象
	 * @param {}
	 *            formatType 指定格式:
	 *            <ul>
	 *            <li>0-yyyyMMdd</li>
	 *            <li>1-yyyy-MM-dd</li>
	 *            <li>2-HHmmss</li>
	 *            <li>3-HH:mm:ss</li>
	 *            <li>4-HHmmssSSS</li>
	 *            <li>5-HH:mm:ss.SSS</li>
	 *            <li>6-yyyyMMddHHmmss</li>
	 *            <li>7-yyyy-MM-dd HH:mm:ss</li>
	 *            <li>8-yyyyMMddHHmmssSSS</li>
	 *            <li>9-yyyy-MM-dd HH:mm:ss.SSS</li>
	 *            <li>10-yyyy/MM/dd HH:mm</li>
	 *            <li>11-yyyy-MM-dd HH:mm</li>
	 *            </ul>
	 * @return 格式化后的字符串
	 */
	date2String : function(date, formatType) {
		// 格式化字符串数组
		var patterns = ["yyyyMMdd", "yyyy-MM-dd", "HHmmss", "HH:mm:ss", "HHmmssSSS", "HH:mm:ss.SSS", "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmssSSS", "yyyy-MM-dd HH:mm:ss.SSS","yyyy/MM/dd HH:mm","yyyy/MM/dd HH:mm:ss","yyyy-MM-dd HH:mm"];
		return this.formatDate(date, patterns[formatType]);
	},

	/**
	 * 将字符串转化为相应时间
	 * 
	 * @param {}
	 *            dateStr 时间字符串
	 * @param {}
	 *            formatType 指定格式:
	 *            <ul>
	 *            <li>0-yyyyMMdd</li>
	 *            <li>1-yyyy-MM-dd</li>
	 *            <li>2-HHmmss</li>
	 *            <li>3-HH:mm:ss</li>
	 *            <li>4-HHmmssSSS</li>
	 *            <li>5-HH:mm:ss.SSS</li>
	 *            <li>6-yyyyMMddHHmmss</li>
	 *            <li>7-yyyy-MM-dd HH:mm:ss</li>
	 *            <li>8-yyyyMMddHHmmssSSS</li>
	 *            <li>9-yyyy-MM-dd HH:mm:ss.SSS</li>
	 *            <li>10-yyyy/MM/dd HH:mm</li>
	 *            <li>11-yyyy/MM/dd HH:mm:ss</li>

	 *            </ul>
	 * @return 解析后的Date对象
	 */
	string2Date : function(dateStr, formatType) {
		var date = new Date(0);
		var year = 1970;
		var month = 1;
		var day = 1;
		var hour = 0;
		var minute = 0;
		var second = 0;
		var millisecond = 0;
		switch (formatType) {
			case 0 :
				year = parseInt(dateStr.substr(0, 4),10);
				month = parseInt(dateStr.substr(4, 2),10);
				day = parseInt(dateStr.substr(6, 2),10);
				break;
			case 1 :
				year = parseInt(dateStr.substr(0, 4),10);
				month = parseInt(dateStr.substr(5, 2),10);
				day = parseInt(dateStr.substr(8, 2),10);
				break;
			case 2 :
				hour = parseInt(dateStr.substr(0, 2),10);
				minute = parseInt(dateStr.substr(2, 2),10);
				second = parseInt(dateStr.substr(4, 2),10);
				break;
			case 3 :
				hour = parseInt(dateStr.substr(0, 2),10);
				minute = parseInt(dateStr.substr(3, 2),10);
				second = parseInt(dateStr.substr(6, 2),10);
				break;
			case 4 :
				hour = parseInt(dateStr.substr(0, 2),10);
				minute = parseInt(dateStr.substr(2, 2),10);
				second = parseInt(dateStr.substr(4, 2),10);
				millisecond = parseInt(dateStr.substr(6, 3),10);
				break;
			case 5 :
				hour = parseInt(dateStr.substr(0, 2),10);
				minute = parseInt(dateStr.substr(3, 2),10);
				second = parseInt(dateStr.substr(6, 2),10);
				millisecond = parseInt(dateStr.substr(9, 3),10);
				break;
			case 6 :
				year = parseInt(dateStr.substr(0, 4),10);
				month = parseInt(dateStr.substr(4, 2),10);
				day = parseInt(dateStr.substr(6, 2),10);
				hour = parseInt(dateStr.substr(8, 2),10);
				minute = parseInt(dateStr.substr(10, 2),10);
				second = parseInt(dateStr.substr(12, 2),10);
				break;
			case 7 :
				year = parseInt(dateStr.substr(0, 4),10);
				month = parseInt(dateStr.substr(5, 2),10);
				day = parseInt(dateStr.substr(8, 2),10);
				hour = parseInt(dateStr.substr(11, 2),10);
				minute = parseInt(dateStr.substr(14, 2),10);
				second = parseInt(dateStr.substr(17, 2),10);
				break;
			case 8 :
				year = parseInt(dateStr.substr(0, 4),10);
				month = parseInt(dateStr.substr(4, 2),10);
				day = parseInt(dateStr.substr(6, 2),10);
				hour = parseInt(dateStr.substr(8, 2),10);
				minute = parseInt(dateStr.substr(10, 2),10);
				second = parseInt(dateStr.substr(12, 2),10);
				millisecond = parseInt(dateStr.substr(14, 3),10);
				break;
			case 9 :
				year = parseInt(dateStr.substr(0, 4),10);
				month = parseInt(dateStr.substr(5, 2),10);
				day = parseInt(dateStr.substr(8, 2),10);
				hour = parseInt(dateStr.substr(11, 2),10);
				minute = parseInt(dateStr.substr(14, 2),10);
				second = parseInt(dateStr.substr(17, 2),10);
				millisecond = parseInt(dateStr.substr(19, 3),10);
				break;
			case 10 ://yyyy/MM/dd HH:mm
				year = parseInt(dateStr.substr(0, 4),10);
				month = parseInt(dateStr.substr(5, 2),10);
				day = parseInt(dateStr.substr(8, 2),10);
				hour = parseInt(dateStr.substr(11, 2),10);
				minute = parseInt(dateStr.substr(14, 2),10);
				break;
			case 11 ://yyyy/MM/dd HH:mm:ss
				year = parseInt(dateStr.substr(0, 4),10);
				month = parseInt(dateStr.substr(5, 2),10);
				day = parseInt(dateStr.substr(8, 2),10);
				hour = parseInt(dateStr.substr(11, 2),10);
				minute = parseInt(dateStr.substr(14, 2),10);
				second = parseInt(dateStr.substr(17, 2),10);
				break;
			case 12 ://yyyy-MM-dd HH:mm
				year = parseInt(dateStr.substr(0, 4),10);
				month = parseInt(dateStr.substr(5, 2),10);
				day = parseInt(dateStr.substr(8, 2),10);
				hour = parseInt(dateStr.substr(11, 2),10);
				minute = parseInt(dateStr.substr(14, 2),10);
				break;
		}
		date.setFullYear(year);
		date.setMonth(month - 1);
		date.setDate(day);
		date.setHours(hour);
		date.setMinutes(minute);
		date.setSeconds(second);
		date.setMilliseconds(millisecond);
		return date;
	},

	/**
	 * 格式化时间
	 * 
	 * @param {}
	 *            date 待格式化的Date对象
	 * @param {}
	 *            pattern 格式化模式，可能包含下列字母
	 *            <ul>
	 *            <li> G 公元前/后 "G":"BC"/"AD" </li>
	 *            <li> y 年份 "yyyy":"1996";"yy":"96" </li>
	 *            <li> M 月份 "MMMM":July;"MMM":Jul;"MM":07 </li>
	 *            <li> d 在某月中的日期 "dd":"10" </li>
	 *            <li> a Am/pm "a":"AM"/"PM" </li>
	 *            <li> H 在某天中的小时(0-23) "HH":"13" </li>
	 *            <li> K 在上午或下午中的小时(0-11) "KK":"03" </li>
	 *            <li> m 分钟 "mm":"10" </li>
	 *            <li> s 秒钟 "ss":"09" </li>
	 *            <li> S 毫秒 "SSS":"978" </li>
	 *            </ul>
	 * @return 格式化后的字符串
	 */
	formatDate : function(date, pattern) {
		var dateStr = new String(pattern);
		// 格式化公元信息
		dateStr = dateStr.replace("G",date.getFullYear()>=0?"AD":"BC");
		// 格式化年份信息
		var year = new String(date.getFullYear());
		dateStr = dateStr.replace("yyyy", year);
		dateStr = dateStr.replace("yy", year.substring(year.length-2,year.length));
		// 格式化月份信息
		var month = date.getMonth();
		var monthFullNames = ["January","February","March","April","May","June","July","August","September","October","November","December"];
		var monthShortNames = ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
		dateStr = dateStr.replace("MMMM",monthFullNames[month]);
		dateStr = dateStr.replace("MMM",monthShortNames[month]);
		dateStr = dateStr.replace("MM",month<9?"0"+(month+1):month+1);
		// 格式化月份中的日期
		var day = date.getDate();
		dateStr = dateStr.replace("dd",day<10?"0"+day:day);
		// 格式化上下午
		dateStr = dateStr.replace("a",date.getHours()<12?"AM":"PM");
		// 格式化小时
		var hour = date.getHours();
		dateStr = dateStr.replace("HH",hour<10?"0"+hour:hour);
		var khour = hour>11?hour-12:hour;
		dateStr = dateStr.replace("KK",khour<10?"0"+khour:khour);
		// 格式化分钟
		var minute = date.getMinutes();
		dateStr = dateStr.replace("mm",minute<10?"0"+minute:minute);
		// 格式化秒钟
		var second = date.getSeconds();
		dateStr = dateStr.replace("ss",second<10?"0"+second:second);
		// 格式化毫秒
		var millisecond = date.getMilliseconds();
		dateStr = dateStr.replace("SSS",millisecond<10?"00"+millisecond:(millisecond<100?"0"+millisecond:millisecond));
		return dateStr;
	},

	/**
	 * 取得date在域field上偏移amount后的值
	 * 
	 * @param {}
	 *            date 原始时间
	 * @param {}
	 *            field 偏移域，可能的取值:
	 *            <ul>
	 *            <li>"year"/"y":年</li>
	 *            <li>"month"/"M":月</li>
	 *            <li>"day"/"d":日</li>
	 *            <li>"hour"/"h":时</li>
	 *            <li>"minute"/"m":分</li>
	 *            <li>"second"/"s":秒</li>
	 *            <li>"millisecond"/"ms"/"S":毫秒</li>
	 *            </ul>
	 * @param {}
	 *            amount 偏移量
	 * @return 偏移后的时间
	 */
	dateOffset : function(date, field, amount) {
		var newDate = new Date(date.getTime());
		switch(field){
			// 以年为单位位移
			case "year","y":
				newDate.setFullYear(date.getFullYear()+amount);
				break;
			// 以月份为单位位移
			case "month","M":
				newDate.setMonth(date.getMonth()+amount);
				break;
			// 以天为单位位移
			case "day","d":
				newDate.setDate(date.getDate()+amount);
				break;
			// 以小时为单位位移
			case "hour":
				newDate.setHours(date.getHours()+amount);
				break;
			// 以分钟为单位位移
			case "minute":
				newDate.setMinutes(date.getMinutes()+amount);
				break;
			// 以秒为单位位移
			case "second","s":
				newDate.setSeconds(date.getSeconds+amount);
				break;
			// 以毫秒为单位位移
			case "millisecond","ms","S":
				newDate.setMilliseconds(date.getMilliseconds+amount);
				break;
		}
		return newDate;
	},

	/**
	 * 求两个日期间相差的毫秒数
	 * 
	 * @param {}
	 *            date1 时间1
	 * @param {}
	 *            date2 时间2
	 * @return 两个时间相差的毫秒数
	 */
	getDiffMillis : function(date1, date2) {
		return date1.getTime() - date2.getTime();
	},

	/**
	 * 求两个日期间相差的秒数
	 * 
	 * @param {}
	 *            date1 时间1
	 * @param {}
	 *            date2 时间2
	 * @return 两个时间相差的秒数
	 */
	getDiffSeconds : function(date1, date2) {
		return Math.floor(this.getDiffMillis(date1, date2) / 1000);
	},

	/**
	 * 求两个日期间相差的分钟数目
	 * 
	 * @param {}
	 *            date1 时间1
	 * @param {}
	 *            date2 时间2
	 * @return 两个时间相差的分钟数
	 */
	getDiffMinutes : function(date1, date2) {
		return Math.floor(this.getDiffSeconds(date1, date2) / 60);
	},

	/**
	 * 求两个日期间相差的小时数目
	 * 
	 * @param {}
	 *            date1 时间1
	 * @param {}
	 *            date2 时间2
	 * @return 两个时间相差的小时数
	 */
	getDiffHours : function(date1, date2) {
		return Math.floor(this.getDiffMinutes(date1, date2) / 60);
	},

	/**
	 * 求两个日期间相差的天数
	 * 
	 * @param {}
	 *            date1 时间1
	 * @param {}
	 *            date2 时间2
	 * @return 两个时间相差的天数
	 */
	getDiffDays : function(date1, date2) {
		return Math.floor(this.getDiffHours(date1, date2) / 24);
	},

	/**
	 * 求两个日期间相差的月数目。认为每个月均为30天。
	 * 
	 * @param {}
	 *            date1 时间1
	 * @param {}
	 *            date2 时间2
	 * @return 两个时间相差的月树
	 */
	getDiffMonths : function(date1, date2) {
		return Math.floor(this.getDiffDays(date1, date2) / 30);
	},

	/**
	 * 求两个日期间相差的自然月数目
	 * 
	 * @param {}
	 *            date1 时间1
	 * @param {}
	 *            date2 时间2
	 * @return 两个时间相差的自然月数
	 */
	getDiffNaturalMonth : function(date1, date2) {
		var month1 = date1.getMonth();
		var month2 = date2.getMonth();
		return this.getDiffYears(date1, date2) * 12 - (month2 - month1);
	},

	/**
	 * 求两个日期间相差的年数目
	 * 
	 * @param {}
	 *            date1 时间1
	 * @param {}
	 *            date2 时间2
	 * @return 两个时间相差的年数
	 */
	getDiffYears : function(date1, date2) {
		return date1.getFullYear() - date2.getFullYear();
	},

	/**
	 * 求日期为其所在月份的第几天
	 * 
	 * @param {}
	 *            day 目标日
	 * @return 目标日是第几天。天数从1开始
	 */
	getOrinalOfDayInYear : function(day) {
		// 得到一年中的第一天
		var firstDay = new Date(day.getFullYear, 0, 1);
		// 计算与第一天的差值
		return this.getDiffDays(day, firstDay) + 1;
	},

	/**
	 * 求日期为其所在月份的第几天
	 * 
	 * @param {}
	 *            day 目标日
	 * @return 目标日是第几天。天数从1开始
	 */
	getOrinalOfDayInMonth : function(day) {
		// 得到一个月中的第一天
		var firstDay = new Date(day.getFullYear, day.getMonth(), 1);
		// 计算与第一天的差值
		return this.getDiffDays(day, firstDay) + 1;
	},

	/**
	 * 求日期为其所在周的第几天
	 * 
	 * @param {}
	 *            day 目标日
	 * @return 目标日是第几天。天数从1开始
	 */
	getOrinalOfDayInWeek : function(day) {
		return day.getDay() + 1;
	},

	/**
	 * 求某年共有多少天
	 * 
	 * @param {}
	 *            year 年份
	 * @return 该年份共有多少天
	 */
	getNumberOfDaysInYear : function(year) {
		// 若该年份为闰年，返回366
		if ((year % 100 != 0 && year % 4 == 0) || (year % 400 == 0)) {
			return 366;
		}
		// 普通年份返回365
		return 365;
	},

	/**
	 * 求某月共有多少天
	 * 
	 * @param {}
	 *            year 年份
	 * @param {}
	 *            month 月份
	 * @return 该月内共有多少天
	 */
	getNumberOfDaysInMonth : function(year, month) {
		// 得到该月份的第一天
		var date1 = new Date(year, month - 1, 1);
		// 得到后一个月份的第一天
		var date2 = "";
		if (month != 11) {
			date2 = new Date(year, month, 1);
		} else {
			date2 = new Date(year - 1, 0, 1);
		}
		return this.getDiffDays(date1, date2);
	},

	/**
	 * 得到当前年份一共有多少天
	 * 
	 * @return 当前年份共有多少天
	 */
	getNumberOfDaysInCurrentYear : function() {
		var date = new Date();
		return this.getNumberOfDaysInYear(date.getYear());
	},

	/**
	 * 得到当前月份一共有多少天
	 * 
	 * @return 当前月份共有多少天
	 */
	getNumberOfDaysInCurrentMonth : function() {
		var date = new Date();
		return this.getNumberOfDaysInMonth(date.getYear(), date.getMonth() + 1);
	}
}