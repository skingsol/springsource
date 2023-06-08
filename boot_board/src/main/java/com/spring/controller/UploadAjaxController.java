package com.spring.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.multipart.MultipartFile;

import com.spring.domain.AttachFileDTO;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@Controller
@Slf4j
public class UploadAjaxController {
	
	//uploadAjax 컨트롤러 생성
	@GetMapping("/uploadAjax")
	public void uploadAjaxGet() {
		log.info("uploadAjax 폼 요청");
	}
	
	
//	@PostMapping("/uploadAjax")
//	public ResponseEntity<String> uploadAjaxPost(MultipartFile uploadFile) {
//		log.info("upload 요청");
//		
//		log.info("file name "+uploadFile.getOriginalFilename());
//		log.info("file size "+uploadFile.getSize());
//		
//		String uploadPath = "c:\\upload";
//		UUID uuid = UUID.randomUUID();
//		String fileName = uuid.toString() + "_" + uploadFile.getOriginalFilename();
//		
//		try {
//			uploadFile.transferTo(new File(uploadPath, fileName));
//		} catch (IllegalStateException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		//상태코드 + 메세지
//		return new ResponseEntity<>(fileName, HttpStatus.OK);
//	}
	
//	@PostMapping("/uploadAjax")
//	public ResponseEntity<List<String>> uploadAjaxPost(MultipartFile[] uploadFile) {
//		log.info("upload 요청");
	
//		List<String> fileList = new ArrayList<String>();
//		
//		for (MultipartFile multipartFile : uploadFile) {
//			
//			log.info("file name "+multipartFile.getOriginalFilename());
//			log.info("file size "+multipartFile.getSize());
//
//		String uploadPath = "c:\\upload";
//		UUID uuid = UUID.randomUUID();
//		String fileName = uuid.toString() + "_" + multipartFile.getOriginalFilename();
//		
//		fileList.add(multipartFile.getOriginalFilename());
//		
//		
//		try {
//			multipartFile.transferTo(new File(uploadPath, fileName));
//		} catch (IllegalStateException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//		//상태코드 + 메세지
//		return new ResponseEntity<>(fileList, HttpStatus.OK);
//	}
	
	@PostMapping("/uploadAjax")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<List<AttachFileDTO>> uploadAjaxPost(MultipartFile[] uploadFile) {
		log.info("upload 요청");
		
		List<AttachFileDTO> fileList = new ArrayList<AttachFileDTO>();
		
		String uploadPath = "c:\\upload";
		//폴더 생성 => c:\\upload\\2023\\05\\26
		String uploadFolderPath = getFolder();
		log.info("uploadFolderPath ",uploadFolderPath);
		File uploadFullPath = new File(uploadPath, uploadFolderPath);
		
		if(!uploadFullPath.exists()) {
			uploadFullPath.mkdirs();
		}
		
		
		
		for (MultipartFile multipartFile : uploadFile) {
			
			log.info("file name "+multipartFile.getOriginalFilename());
			log.info("file size "+multipartFile.getSize());

		UUID uuid = UUID.randomUUID();
		String fileName = uuid.toString() + "_" + multipartFile.getOriginalFilename();
		
		File saveFile = new File(uploadFullPath, fileName);
		
		// 파일 한 개당 attachFileDTO 생성
		
		AttachFileDTO attach = new AttachFileDTO();
		attach.setFileName(multipartFile.getOriginalFilename());
		attach.setUploadPath(uploadFolderPath);
		attach.setUuid(uuid.toString());

		try {
			//원본 파일 저장
			multipartFile.transferTo(saveFile);
			
			//업로드된 파일 타입 체크
			if (checkImageType(saveFile)) {
				attach.setFileType(true);
				
				// 이미지 파일이라면 썸네일 이미지로 저장하기
				// 원본 이미지 읽어오기
				BufferedImage origin = ImageIO.read(saveFile);
				// 썸네일 파일명 ( 원본파일 앞에 S_부착 )
				File thumbnail = new File(uploadFullPath, "s_"+fileName);
				
				double ratio = 2; // 축소비율				
				int width = (int)(origin.getWidth() / ratio);
				int height = (int)(origin.getHeight() / ratio);
				
				Thumbnails.of(origin).size(width, height).toFile(thumbnail);

			}
		
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		fileList.add(attach);		
	}
		//상태코드 + 메세지
		return new ResponseEntity<>(fileList, HttpStatus.OK);
	}
	
	
	//파일 요청 시 파일 보내주기
	@GetMapping("/display")
	public ResponseEntity<byte[]> getFile(String fileName){
		log.info("파일 요청" + fileName);
		
		File file = new File("c:\\upload\\"+fileName);
		
		//스프링프레임워크 headers
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<byte[]> result = null;
		try {
			// 서버가 보내는 파일에 대한 타입 지정
			headers.add("content-type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file),HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
		}return result;
	}	
	
	// HttpServletRequest 객체 : 클라이언트 정보를 가져올 수 있음
	@GetMapping(value="/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Resource> downloadFile(String fileName,@RequestHeader("User-agent") String userAgent){
		log.info("파일다운로드 요청" + fileName);
		
		// c:\\upload\\2023\05\30\4be2993f-a046-34ke-bv4k-aa4cd02c046e_존윅.jpg
		Resource resource = new FileSystemResource("c:\\upload\\"+fileName);
		
		// UUID를 포함한 파일명
		String oriFileName = resource.getFilename();
		// 경로와 uuid를 제거한 파일명
		String splitUuid = oriFileName.substring(oriFileName.indexOf("_")+1);
		
		if(!resource.exists()) {
			return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
		}
		
		HttpHeaders headers = new HttpHeaders();
		
		String downloadName = null;
		try {
			// ms 계열 : Trident (IE 11버전)
			if(userAgent.contains("Trident") || userAgent.contains("Edge")) {
				// UUID 를 포함한 파일명
				//downloadName = URLEncoder.encode(resource.getFilename(), "utf-8").replaceAll("\\", " ");
				downloadName = URLEncoder.encode(splitUuid, "utf-8").replaceAll("\\", " ");
			}else {
				//downloadName = new String(resource.getFilename().getBytes("utf-8"), "ISO-8859-1");
				downloadName = new String(splitUuid.getBytes("utf-8"), "ISO-8859-1");
			}
			
			// 파일 헤더에 붙이기 
			headers.add("Content-Disposition", "attachment;filename="+downloadName);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		return new ResponseEntity<Resource>(resource,headers,HttpStatus.OK);		
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/deleteFile")
	public ResponseEntity<String> deleteFile(String fileName, String type){
		log.info("파일제거 요청 "+fileName+", type "+type);
		// 특수문자 인코딩이 일어남 ==> 경로에 있는 \ 가 %5C 로 인코딩 됨
		// 2023%5C05%5C30%5Cs_eb0ec979-cd43-4bfd-bb77-f901f0bf49ab_%EC%A1%B4%EC%9C%854.jpg
		// %5C를 \로 디코딩 하는 작업임		
		try {
			File file = new File("c:\\upload\\", URLDecoder.decode(fileName, "utf-8"));
			
			// 이미지일 경우 : 원본, 썸네일 이미지 제거
			// txt일 경우 : 파일 제거
			file.delete();   // 파일 삭제
			
			//원본 이미지 제거
			if(type.equals("image")) {
				// 위에서 작성한 file 객체에서fileName 추룰 후 s_를 제거한 상태의 이름을 파일 객체로 생성
				String largeName = file.getAbsolutePath().replace("s_", "");
				file = new File(largeName);
				// delete 작업				
				file.delete();
			}
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	
	// 일반 메소드(파일 타입 확인)
	private boolean checkImageType(File file) {
		String contentType;
		try {
			contentType = Files.probeContentType(file.toPath());
			return contentType.startsWith("image");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// 일반 메소드(폴더 생성)
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = new Date(); // 날짜 정보가 길게
		String str = sdf.format(date); // 2023-05-26
		return str.replace("-", File.separator); // separator: 현재 사용운영체제에 따른/를 표시해줘라는 뜻 c:/1.jpg
	}
	
}

