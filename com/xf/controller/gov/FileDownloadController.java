package com.xf.controller.gov;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xf.controller.ResultObj;
import com.xf.security.LoginManage;
import com.xf.service.ConfigService;
import com.xf.service.gov.FileDownloadService;

@Scope("prototype")
@Controller
@RequestMapping(value = "/exceldown")
public class FileDownloadController {

	@Autowired
	private FileDownloadService theService;
	@Autowired
	private ConfigService configService;
	@Autowired
	private LoginManage loginManage;
	@Autowired
	private ResultObj result;

	@RequestMapping(value = "/download")
	@ResponseBody
	private ResponseEntity<byte[]> download1(String tabletype, int accountid,int fillyear) {
		
		try {
			String fileName = theService.findByid(tabletype, accountid,fillyear);
			String path = configService.get("fileupload_dir");
			if (path == null || path.isEmpty()) {
				path = "./";
			}
			path += "/checked/" +fillyear+"_"+ accountid + "_" + fileName;
			
			File file = new File(path);
			HttpHeaders headers = new HttpHeaders();
			String dfileName = new String(fileName.getBytes("UTF-8"),
					"iso-8859-1");
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentDispositionFormData("attachment", dfileName);
			return new ResponseEntity(FileUtils.readFileToByteArray(file),
					headers, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
