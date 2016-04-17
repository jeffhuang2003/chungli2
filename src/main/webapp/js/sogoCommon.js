/**
 *修改歷程:
 *2012/06/06 Bryan 增加自訂 plugin
 * 							1. myDatepicker
 * 							2. myDatetimepicker
 */


$(document).ready(function(){
	/**
	 * 自訂jQuery Plugin
	 */
	(function($) {
		
		/**
		 * 日期選擇器
		 * 
		 * 修改歷程
		 * 2012/06/06 Bryan 初版 (轉call datepicker 並預設參數)
		 */
	    $.fn.myDatepicker = function() {
	        return this.each(function(settings) {
	            var _defaultSettings = {
	            		dateFormat: 'yy/mm/dd'
	                };
	        	var _settings = $.extend(_defaultSettings, settings);
	        	$(this).datepicker(_settings);
	        });
	    };  
	    
	    /**
	     * 日期+時間-選擇器
	     * 
		 * 修改歷程
		 * 2012/06/06 Bryan 初版 (轉call datetimepicker 並預設參數)
	     */
	    $.fn.myDatetimepicker = function() {
	        return this.each(function(settings) {
	            var _defaultSettings = {
	            		dateFormat: 'yy/mm/dd',
	            		ampm: true,
	            		hourMin: 0,
	            		hourMax: 23, 
	            		minuteMin: 0,
	            		minuteMax: 59, 
	            		secondMin: 0,
	            		secondMax: 59, 
	            		showSecond: true,
	            		showMillisec: false,
	            		timeFormat: 'HH:mm:ss', 
	            		clearText:'Clear',
	                };
	        	var _settings = $.extend(_defaultSettings, settings);        	
	            $(this).datetimepicker(_settings);
	        });
	    };

	})(jQuery);
	 
});