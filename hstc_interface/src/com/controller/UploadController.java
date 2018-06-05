package com.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.StorageClass;
import com.qcloud.cos.region.Region;
import com.utils.COSConfig;
import com.utils.DateConvert;

@Controller
public class UploadController {
	/**
	 * 上传图片到腾讯云的对象存储空间
	 * @param file
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/uploadImage")
	public String test(MultipartFile file,HttpServletRequest request,String url) throws IOException{  
		String imgName = upload(file);		
		HttpSession session = request.getSession();
        session.setAttribute("imgName", imgName);
		return "forward:/"+url;
    } 
	
	@ResponseBody
	public String upload(MultipartFile file) throws IOException {
		if(file != null) {
			String contentType = file.getContentType();
			InputStream inputStream = file.getInputStream();
			long size = file.getSize();
			if(size != 0) {
				 // 初始化用户身份信息(secretId, secretKey)
		        COSCredentials cred = new BasicCOSCredentials(COSConfig.SECRETID, COSConfig.SECRETKEY);
		        // 设置bucket的区域, COS地域的简称请参照 https://www.qcloud.com/document/product/436/6224
		        ClientConfig clientConfig = new ClientConfig(new Region(COSConfig.REGION));
		        // 生成cos客户端
		        COSClient cosclient = new COSClient(cred, clientConfig);
		        // bucket名需包含appid
		        String bucketName = COSConfig.BUCKETNAME;
		        
		        //获取后缀名
		        String[] split = contentType.split("/");
		        //文件名
		        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		        
		        //当前日期
		        Date date = new Date();
		        DateConvert util = new DateConvert();
		        String dd = util.toString(date);
		        
		        //目标文件名
		        String key = "/" + dd + "/" + uuid + "." + split[1];
		        String imgName = key.substring(1);
		     
		        
		        ObjectMetadata objectMetadata = new ObjectMetadata();
		        // 从输入流上传必须制定content length, 否则http客户端可能会缓存所有数据，存在内存OOM的情况
		        objectMetadata.setContentLength(size);
		        // 默认下载时根据cos路径key的后缀返回响应的contenttype, 上传时设置contenttype会覆盖默认值
		        objectMetadata.setContentType(contentType);
		        
		        PutObjectRequest putObjectRequest =
		                new PutObjectRequest(bucketName, key, inputStream, objectMetadata);
		        // 设置存储类型, 默认是标准(Standard), 低频(standard_ia), 近线(nearline) 
		        putObjectRequest.setStorageClass(StorageClass.Standard);
		        try {
		            cosclient.putObject(putObjectRequest);
		            //===========将文件名key存入数据库==================
		            //
		            //==============================            
		            //=============将文件名存入 session 中，在增加信息的时候，把路径存到数据库========== 
		        } catch (CosServiceException e) {
		            e.printStackTrace();
		        } catch (CosClientException e) {
		            e.printStackTrace();
		        }
		        
		        // 关闭客户端        
		        cosclient.shutdown();
		        return imgName;
			}
		}
		return "";
	}
}
