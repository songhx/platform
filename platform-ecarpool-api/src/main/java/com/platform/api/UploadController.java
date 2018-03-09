package com.platform.api;

import com.alibaba.fastjson.JSON;
import com.platform.util.ApiBaseAction;
import com.platform.utils.PropertiesUtil;
import com.platform.utils.upload.Upload;
import com.platform.utils.upload.UploadVo;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;


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

        Map<String, Object> returnMap = new HashMap<String, Object>();
        List<UploadVo> uploadFileInfos  = new ArrayList<UploadVo>();
        try {
            //Map<String, Object> attributes = getRequestAttributes(request);
            String platformCode = multipartRequest.getParameter("platformCode");
            platformCode = "";
            String dirFolderName = multipartRequest.getParameter("dirFolderName"); //(String)attributes.get("dirFolderName");
            logger.info("upLoad 参数 -- platformCode : " + platformCode + "--dirFolderName : " + dirFolderName);
            Map<String, String>  properties = PropertiesUtil.getInstance("/upload.properties");
            String dir = properties.get("dir");
            String uploadFileTypes = properties.get("uploadFileType");
            String remotePath = properties.get("remotePath");
            String dirPath = properties.get("dirPath");
           // List<FileItem> files = (List<FileItem>)attributes.get("files");
            for (MultipartFile file : fileList) {
                if (file != null && file.getOriginalFilename() != null &&  !"".equals(file.getOriginalFilename())) {
                    UploadVo uploadVo = new UploadVo();
                    String fileName = file.getOriginalFilename().trim();
                    uploadVo.setOldName(fileName);
                    uploadVo.setFileSize(file.getSize());
                    logger.debug("fileName:" +fileName);
                    //任意文件上传  当文件是符合要求的文件，则上传
                    if (checkFileType(fileName , uploadFileTypes)) {
                        if (StringUtils.isNotEmpty(fileName)) {
                            String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
                            uploadVo.setFileType(prefix);
                            String uuid = UUID.randomUUID().toString().replace("-", ""); //获取到唯一的名称
                            fileName = uuid + "." + prefix;  //上传服务器名
                        }
                        String dirFile = remotePath+ platformCode +"/" + dirFolderName  +"/" ;
                        uploadVo.setFileServerPath(dirPath+platformCode +"/" + dirFolderName  +"/"+fileName);
                        try {
                            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(dir+ fileName));// 复制临时文件到指定目录下
                            Upload.ftpFile(dir + fileName, dirFile);//上传到服务器
                        } catch (IOException e) {
                            uploadVo.setReturnStatus(Integer.valueOf(1));
                            e.printStackTrace();
                        }
                    }
                    uploadFileInfos.add(uploadVo);
                }
            }
            returnMap.put("uploadRst", uploadFileInfos);
            return toResponsSuccess(returnMap);
            //printJSON(response, returnMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取微信小程序上传文件所携带的参数
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     * @throws FileUploadException
     */
    private Map<String,Object> getRequestAttributes(HttpServletRequest request) throws UnsupportedEncodingException, FileUploadException {
        Map<String,Object> map =  new HashMap<String, Object>();
        //获取文件需要上传到的路径
        String path = request.getRealPath("/upload") + "/";
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdir();
        }
        logger.debug("path=" + path);

        request.setCharacterEncoding("utf-8");  //设置编码

        //获得磁盘文件条目工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();

        //如果没以下两行设置的话,上传大的文件会占用很多内存，
        //设置暂时存放的存储室,这个存储室可以和最终存储文件的目录不同
        /**
         * 原理: 它是先存到暂时存储室，然后再真正写到对应目录的硬盘上，
         * 按理来说当上传一个文件时，其实是上传了两份，第一个是以 .tem 格式的
         * 然后再将其真正写到对应目录的硬盘上
         */
        factory.setRepository(dir);
        //设置缓存的大小，当上传文件的容量超过该缓存时，直接放到暂时存储室
        factory.setSizeThreshold(1024 * 1024);
        //高水平的API文件上传处理
        ServletFileUpload upload = new ServletFileUpload(factory);

        List<FileItem> list = upload.parseRequest(request);
        List<FileItem> files = new ArrayList<>();
        for (FileItem item : list) {
            //属性名字
            String name = item.getFieldName();
            //获取formData传递的参数
            if (item.isFormField()) {
                logger.debug("name=" + name + ",value=" + item.getString());
                map.put(name, item.getString());
            } else {
                files.add(item); // 获取上传文件信息
            }
        }

        map.put("files",files);

        return map;
    }

    //检查文件类型是否符合规范
    private boolean checkFileType(String fileName , String uploadFileTypes) throws Exception{
        boolean flag = false;
        String[] uploadFileType = uploadFileTypes.split("[|]");
        //检查上传的文件类型是否符合要求的文件
        for (int i = 0; i < uploadFileType.length; i++) {
            if (fileName.toLowerCase().contains(uploadFileType[i])) {
                flag = true;
                break;
            }
        }
        return flag;
    }


    @RequestMapping("/picture")
    public void uploadPicture(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取文件需要上传到的路径
        String path = request.getRealPath("/upload") + "/";
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdir();
        }
        logger.debug("path=" + path);

        request.setCharacterEncoding("utf-8");  //设置编码
        //获得磁盘文件条目工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();

        //如果没以下两行设置的话,上传大的文件会占用很多内存，
        //设置暂时存放的存储室,这个存储室可以和最终存储文件的目录不同
        /**
         * 原理: 它是先存到暂时存储室，然后再真正写到对应目录的硬盘上，
         * 按理来说当上传一个文件时，其实是上传了两份，第一个是以 .tem 格式的
         * 然后再将其真正写到对应目录的硬盘上
         */
        factory.setRepository(dir);
        //设置缓存的大小，当上传文件的容量超过该缓存时，直接放到暂时存储室
        factory.setSizeThreshold(1024 * 1024);
        //高水平的API文件上传处理
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List<FileItem> list = upload.parseRequest(request);
            FileItem picture = null;
            for (FileItem item : list) {
                //获取表单的属性名字
                String name = item.getFieldName();
                //如果获取的表单信息是普通的 文本 信息
                if (item.isFormField()) {
                    //获取用户具体输入的字符串
                    String value = item.getString();
                    request.setAttribute(name, value);
                    logger.debug("name=" + name + ",value=" + value);
                } else {
                    picture = item;
                }
            }

            picture.getFieldName();
            //自定义上传图片的名字为userId.jpg
            String fileName = request.getAttribute("userId") + ".jpg";
            String destPath = path + fileName;
            logger.debug("destPath=" + destPath);

            //真正写到磁盘上
            File file = new File(destPath);
            OutputStream out = new FileOutputStream(file);
            InputStream in = picture.getInputStream();
            int length = 0;
            byte[] buf = new byte[1024];
            // in.read(buf) 每次读到的数据存放在buf 数组中
            while ((length = in.read(buf)) != -1) {
                //在buf数组中取出数据写到（输出流）磁盘上
                out.write(buf, 0, length);
            }
            in.close();
            out.close();
        } catch (FileUploadException e1) {
            logger.error("", e1);
        } catch (Exception e) {
            logger.error("", e);
        }


        PrintWriter printWriter = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        HashMap<String, Object> res = new HashMap<String, Object>();
        res.put("success", true);
        printWriter.write(JSON.toJSONString(res));
        printWriter.flush();
    }


}
