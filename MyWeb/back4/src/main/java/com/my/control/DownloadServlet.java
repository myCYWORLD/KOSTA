package com.my.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파일이름 요청전달데이터 얻기
		String fileName = request.getParameter("filename");
		//다운로드할 파일의 실제 경로 얻기
		String saveDirectoryName = "c:\\files";
		
		Path path = Paths.get(saveDirectoryName, fileName);
		String contentType = Files.probeContentType(path);
		System.out.println(fileName+"파일의 contentType:" + contentType);
		

		File f = path.toFile();
		if(contentType.contains("image/")) {
			response.setContentType(contentType);
			response.setHeader("Content-Length", String.valueOf(f.length()));
			response.setHeader("Content-Disposition", "inline; filename="+URLEncoder.encode(fileName, "UTF-8"));
		}else {
			//응답형식 : application/octet-stream(8비트 단위의 binary data)
			response.setContentType("application/octet-stream;charset=UTF-8");

			//다운로드시 파일이름 결정
			//response.setHeader("Content-Disposition", "attachment;filename=" + name);
			response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
		}

		//응답출력스트림
		//PrintWriter out = response.getWriter(); (X) --문자형태로 응답출력
		ServletOutputStream sos = response.getOutputStream(); //--바이트형태로 파일을 출력

		FileInputStream fis = null;
		fis = new FileInputStream(f);
		IOUtils.copy(fis, sos); //파일복사붙여넣기
		fis.close();
		sos.close();
	}
}
