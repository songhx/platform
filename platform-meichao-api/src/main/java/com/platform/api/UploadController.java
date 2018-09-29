package com.platform.api;

import com.platform.util.ApiBaseAction;
import com.platform.utils.FtpUpload;
import com.platform.utils.PropertiesUtil;
import com.platform.utils.upload.UploadVo;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 文件上传
 *
 * @author bjsonghongxu
 * @create 2017-09-08 17:10
 **/
@RestController
@RequestMapping(value = "/api/upload")
public class UploadController extends ApiBaseAction {

    private static final Logger logger = Logger.getLogger(UploadController.class);

    /**
     *
     * @Title: upLoadVedios
     * @Description: ajax 上传文件
     *@author (shx@sxw100.com)
     * @param @param files
     * @param @param request
     * @param @param response
     * @param @throws UnsupportedEncodingException
     * @return void
     * @throws
     */

    @RequestMapping("/upLoad")
    public Object upLoad( HttpServletRequest request, HttpServletResponse response)
            throws UnsupportedEncodingException {
        logger.debug("进入upLoad方法");

        //设置字符编码防止乱码
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        List<MultipartFile> fileList = multipartRequest.getFiles("file");
        String platformCode = multipartRequest.getParameter("platformCode");
        String dirFolderName = multipartRequest.getParameter("dirFolderName");
        List<UploadVo> uploadFileInfos  = null;
        Map<String, Object> returnMap = new HashMap<String, Object>();
        try {
            uploadFileInfos  = FtpUpload.upload(fileList,"",dirFolderName,PropertiesUtil.getInstance("/upload.properties"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        returnMap.put("uploadRst", uploadFileInfos);
        return toResponsSuccess(returnMap);
    }


}
