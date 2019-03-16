package jj.sns.service;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import jj.sns.dao.BoardDao;
import jj.sns.dto.BoardDto;
import jj.sns.dto.BoardPager;

@Service
public class BoardService {

	@Autowired
	BoardDao boardDao;
	
	public void write(int uid, MultipartFile multiFile, BoardDto board) throws IOException{
		
		 // 파일 정보
	      String originFilename = multiFile.getOriginalFilename();
	      String extName = originFilename.substring(originFilename.lastIndexOf("."), originFilename.length());
	      Long size = multiFile.getSize();

	   // 서버에서 저장 할 파일 이름
	      StringBuilder saveFileName = new StringBuilder();
	      SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	      saveFileName.append(uid)
	      .append("_")
	      .append(sf.format(new Date(System.currentTimeMillis())))
	      .append(extName);
	      
	      

	      System.out.println("originFilename : " + originFilename);
	      System.out.println("extensionName : " + extName);
	      System.out.println("size : " + size);
	      System.out.println("saveFileName : " + saveFileName);
	      
	      writeFile(multiFile, saveFileName.toString());
	      
	      board.setImg(saveFileName.toString());
	      boardDao.write(board);
	      
	}
	
	public void resizeImg(File srcFile, String destPath, String type, String hashfileName,
		      int widthdist, int heightdist) throws IOException {
		 
		    File destFolder = new File(destPath);
		    if (!destFolder.exists()) { // 폴더 없으면 생성
		      destFolder.mkdirs();
		    }
		 
		    Image src = ImageIO.read(srcFile);
		 
		    BufferedImage resizeImage =
		        new BufferedImage((int) widthdist, (int) heightdist, BufferedImage.TYPE_INT_RGB);
		 
		    resizeImage.getGraphics().drawImage(src.getScaledInstance(widthdist, heightdist, Image.SCALE_SMOOTH), 0, 0, null);
		    
		    FileOutputStream out = new FileOutputStream(destPath + hashfileName + type);
		    ImageIO.write(resizeImage, type.substring(1), out);
		    out.close();
	  }

	
	// 파일을 실제로 write 하는 메서드
	private void writeFile(MultipartFile multipartFile, String saveFileName) throws IOException{
	 
	    byte[] data = multipartFile.getBytes();
	    FileOutputStream fos = new FileOutputStream("C:\\web-workspace\\JJSns\\src\\main\\webapp\\upload\\" + saveFileName);
	    fos.write(data);
	    fos.close();
	     
	}

	
	public int getBoardTotalCount() {
		return boardDao.getBoardTotalCount();
	}
	
	public List<BoardDto> getBoardList(BoardPager boardPager) {
		return boardDao.getBoardList(boardPager);
	}
	
	@Transactional
	public BoardDto getBoard(long seq) {
		int update = boardDao.addViewCount(seq);
		if(update != 1) {
//			throw new UpdateFailException();
		}
		return boardDao.getBoard(seq);
	}
	
	public Map<String,Object> getUserBoardList(int curPage, int uid) {
		
		int totalCount = boardDao.getUserBoardTotalCount(uid);
		
		BoardPager boardPager = new BoardPager(curPage, totalCount);
		Map map = new HashMap();
		map.put("uid", uid);
		map.put("boardPager", boardPager);
		
		List<BoardDto> boardList = boardDao.getUserBoardList(map);
		
		map.put("boardList", boardList);
		
		return map;
	}
	
	
}
