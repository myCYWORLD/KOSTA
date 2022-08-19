package com.my.control;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/upload")
@MultipartConfig //설정이 되어있어야 part메서드 사용 가능
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String saveDirectoryName = "c:\\files";   //특정 디렉토리 설정
		File saveDirectory = new File(saveDirectoryName);
		try {   			
			if(!saveDirectory.exists()) { //디렉토리 있는지 확인
				System.out.println("업로드 실제경로("+ saveDirectoryName +")생성"); 
				saveDirectory.mkdirs();//디렉토리 생성
			}
			System.out.println("saveDirectory.getAbsolutePath()=" + saveDirectory.getAbsolutePath());
			Collection<Part> parts = request.getParts(); //요청된 form data 전달 part는 request.getparts()메서드로 얻어옴
			
			for(Part part: parts) {
				String paramName = part.getName();
				System.out.println("part.getName()=" + paramName +", part.getSubmittedFileName()="+ part.getSubmittedFileName()+", part.getSize()=" + part.getSize());
				if("foodFile".equals(paramName) || "drinkFiles".equals(paramName)||"recipe".equals(paramName)) {
					String originFileName = part.getSubmittedFileName();
					String fileName =  originFileName;
//					Universal Unique Identifier
					//String fileName = UUID.randomUUID() + "_" + originFileName; -> uuid.randomuuid -> 난수값 발생시킴
//					String fileName = UUID.randomUUID() + "_" + originFileName;
					//String fileName = temp 
					part.write( saveDirectory.getAbsolutePath()+"\\"+fileName); 
					//파일 api를 더 쓰지 않고도 part api를 사용하여 특정 디레토리에 쓰기
				}
			}
				System.out.println("-----request.getParameter(\"greeting\")----");
				System.out.println(request.getParameter("greeting"));
				System.out.println("-----request.getParameter(\"recipes\")----");
				System.out.println(request.getParameter("recipes"));		      
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}