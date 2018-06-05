package com.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
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
public class KindEditorController {
	
	/**
	 * 在 kindEditor 副文本编辑框中实现图片上传到服务器
	 * @param file
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/kindEditorUpload")
	@ResponseBody
	public String test(MultipartFile file) throws IOException{  
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
	        } catch (CosServiceException e) {
	            e.printStackTrace();
	        } catch (CosClientException e) {
	            e.printStackTrace();
	        }
	        
	        // 关闭客户端        
	        cosclient.shutdown(); 
	        JSONObject obj = new JSONObject();
			obj.put("error", 0);
			obj.put("url", "https://hstc-image-1256231252.cos.ap-guangzhou.myqcloud.com" + key);
			return obj.toJSONString();
			//response.getWriter().write(obj.toJSONString());
		} else {
			return getError("上传失败");
			//response.getWriter().write(getError("上传失败"));
		}
    }  
	
	private String getError(String message) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toJSONString();
	}
}
