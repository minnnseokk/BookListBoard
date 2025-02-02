package bookBoard.common;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import bookBoard.dto.BookDetailImgDto;

@Component
public class FileUtils {
	// multipart 데이터값 받아옴
	@Value("${spring.servlet.multipart.location}")
	private String uploadDir;
	
	// 요청을 통해서 전달받은 파일을 저장하고, 파일 정보를 반환하는 메서드 
    public List<BookDetailImgDto> parseFileInfo(long bookId, MultipartHttpServletRequest request) throws Exception {
	    if (ObjectUtils.isEmpty(request)) {
	        return null;
	    }
	    // 이미지 정보 담는 파일
	    List<BookDetailImgDto> ImageInfoList = new ArrayList<>();
		// 파일을 저장할 디렉토리를 설정
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
		ZonedDateTime now = ZonedDateTime.now();
		// images -> 생성된날짜 폴더가 생성되어 저장될 것이다.
		String storedDir = uploadDir + "images\\" + now.format(dtf);
		File fileDir = new File(storedDir);
		if(!fileDir.exists()) {
			fileDir.mkdirs();
		}
		// 업로드 파일 데이터를 디렉터리에 저장하고 정보를 리스트에 저장 
	    Iterator<String> fileTagNames = request.getFileNames();
	    while(fileTagNames.hasNext()) {
	        String fileTagName = fileTagNames.next();
	        List<MultipartFile> files = request.getFiles(fileTagName);
	        for (MultipartFile file : files) {
	            String originalFileExtension = "";
	            
	            // 이미지 관련 파일들 확장자 맞춤
	            // 파일 확장자를 ContentType에 맞춰서 지정
	            if (!file.isEmpty()) {
	                String contentType = file.getContentType();
	                if (ObjectUtils.isEmpty(contentType)) {
	                    break;
	                } else {
	                    if (contentType.contains("image/jpeg")) {
	                        originalFileExtension = ".jpg";
	                    } else if (contentType.contains("image/png")) {
	                        originalFileExtension = ".png";
	                    } else if (contentType.contains("image/gif")) {
	                        originalFileExtension = ".gif";
	                    } else if (contentType.contains("image/jfif")) {
	                    	originalFileExtension = ".jfif";
	                	} else if (contentType.contains("image/webp")) {
	                    	originalFileExtension = ".webp";
	                	}else {
	                        break;
	                    }
	                }
	                // 저장 파일 이름 조합
	                String storedFileName = Long.toString(System.nanoTime()) + originalFileExtension;
	                String storedFilePath = storedDir + "\\" + storedFileName;
	
	                BookDetailImgDto dto = new BookDetailImgDto();
	                dto.setBookId(bookId);
                    dto.setFileSize(Long.toString(file.getSize()));
                    dto.setOriginalFileName(file.getOriginalFilename()); // 인코딩된 파일 이름 사용
                    // storedFilePath를 정적 리소스 경로로 설정
                    dto.setStoredFilePath("/images/" + now.format(dtf) + "/" + storedFileName);
                    ImageInfoList.add(dto);
                    
                    // 파일 저장
                    fileDir = new File(storedFilePath);
                    file.transferTo(fileDir);
                    
                    // 파일을 static 경로로 복사
                    String staticDir = "src/main/resources/static/images/" + now.format(dtf);
                    File staticFileDir = new File(staticDir);
                    if (!staticFileDir.exists()) {
                        staticFileDir.mkdirs(); // static 디렉토리 생성
                    }

                    // 복사할 파일 경로
                    File copiedFile = new File(staticDir + "/" + storedFileName);
                    // 기존 파일에 계속 덮어 씌우는 방식임
                    Files.copy(fileDir.toPath(), copiedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
	            }
	        }
	    }
	    return ImageInfoList;
    }	
}
