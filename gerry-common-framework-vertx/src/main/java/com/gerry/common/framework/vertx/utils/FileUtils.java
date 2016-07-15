package com.gerry.common.framework.vertx.utils;

import java.util.Date;

/**
 * 文件工具类
 * 
 *
 * @author gerry
 * @version 1.0.1, 2016年6月26日
 * @since com.gerry 1.0.1
 */
public class FileUtils {

//	private static final String IMAGE_PATH = "image";
	
	private static final String FILE_PATH = "file";

	 /**
     * 生成保存文件url
     * 
     * @param directory
     * @param suffixName
     * @return
     * @see
     */
    public static String generationFileDirectory(String directory) {
        return directory + "/" + FILE_PATH + "/" + DateUtils.parseDate(new Date());
    }
    
}
